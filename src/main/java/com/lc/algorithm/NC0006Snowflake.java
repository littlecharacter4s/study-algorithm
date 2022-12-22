package com.lc.algorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 1bit(不用) - 41bit(时间戳，毫秒) - 10bit(工作机器id，5bit-datecenterId + 5bit-workerId) - 12bit(序列号)
 */
public class NC0006Snowflake {
    private final long baseTimestamp = LocalDateTime.of(2019, 1, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    private final long datecenterIdBits = 5L;
    private final long workerIdBits = 5L;
    private final long sequenceBits = 12L;
    private final long relativeWorkerIdBits = sequenceBits;
    private final long relativeDatecenterIdBits = workerIdBits + relativeWorkerIdBits;
    private final long relativeTimestampBits = datecenterIdBits + relativeDatecenterIdBits;
    private final long sequenceBase = -1L ^ (-1L << sequenceBits);

    private long sequence = 0L;
    private long lastTimestamp = -1L;

    /**
     * 获取id
     *
     * @param datecenterId {5bit,32}
     * @param workerId     {5bit,32}
     * @return
     * 示例：0 + relativeTimestamp(6300852737) + datecenterId(1) + workerId(1) + sequence(6)
     *      0 + 00000000101110111100011110110001000000001 + 00001 + 00001 + 000000000110 = 26427691838345222
     */
    public synchronized long getId(int datecenterId, int workerId) {
        int limit = (1 << datecenterIdBits + 1) - 1;
        if (datecenterId > limit || workerId > limit) {
            throw new RuntimeException("参数输入错误");
        }
        long timestamp = System.currentTimeMillis();
        while (timestamp < lastTimestamp) {
            try {
                TimeUnit.MILLISECONDS.sleep(lastTimestamp - timestamp);
                timestamp = System.currentTimeMillis();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceBase;
            if (sequence == 0) {
                // sequence为0的时候,表示本毫秒内，序列号已经超过4096（12bit），轮询到下一毫秒
                sequence = new Random().nextInt(100);
                timestamp = this.untilNextMillis(lastTimestamp);
            }
        } else {
            sequence = new Random().nextInt(100);
        }
        lastTimestamp = timestamp;
        System.out.println("timestamp:" + (timestamp - baseTimestamp));
        System.out.println("datecenterId:" + datecenterId);
        System.out.println("workerId:" + workerId);
        System.out.println("sequence:" + sequence);
        return ((timestamp - baseTimestamp) << relativeTimestampBits)
                | (datecenterId << relativeDatecenterIdBits)
                | (workerId << relativeWorkerIdBits)
                | sequence;
    }

    private long untilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
