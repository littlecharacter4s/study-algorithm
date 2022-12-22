package com.lc.algorithm;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class NC0008RateLimiter {
    private int limit = 15;
    private long lastTime = 0;

    // 滑动时间窗口计数器
    private LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder()
                    // 过期清理10秒之前的数据
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });


    /**
     * 滑动时间窗口算法：每5s为一个时间窗口，每1s为一个时间片段
     *
     * @return
     */
    public boolean slidingTimeWindow() throws Exception {
        long time = System.currentTimeMillis() / 1000;
        System.out.println(time);
        // 每秒发送随机数量的请求
        int reqs = (int) (Math.random() * 5) + 1;
        counter.get(time).addAndGet(reqs);
        long nums = 0;
        // 累加时间窗口内的请求量
        for (int i = 0; i < 5; i++) {
            nums += counter.get(time - i).get();
        }
        return nums <= limit;
    }

    /**
     * 令牌桶算法
     *
     * @return
     */
    public boolean tokenBucket() {
        return false;
    }

    /**
     * 漏桶算法
     *
     * @return
     */
    public boolean leakageDrum() {
        return false;
    }
}
