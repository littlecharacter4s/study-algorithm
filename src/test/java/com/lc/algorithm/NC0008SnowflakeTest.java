package com.lc.algorithm;

import com.lc.algorithm.NC0008Snowflake;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class NC0008SnowflakeTest {
    @Test
    public void testGetId() throws Exception {
        NC0008Snowflake snowflake = new NC0008Snowflake();
        long baseTimestamp = LocalDateTime.of(2019, 1, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long timestamp = System.currentTimeMillis();
        long difference = timestamp - baseTimestamp;
        System.out.println("timestamp(" + timestamp + ")-baseTimestamp("+ baseTimestamp + ")=" + difference);
        System.out.println("-----------------------------------------------------------------");
        while (timestamp - baseTimestamp < difference + 5) {
            System.out.println("id(" + timestamp + "):" + snowflake.getId(1, 1));
            System.out.println("-----------------------------------------------------------------");
            timestamp = System.currentTimeMillis();
        }
    }
}