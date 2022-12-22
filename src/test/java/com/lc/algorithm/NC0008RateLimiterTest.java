package com.lc.algorithm;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NC0008RateLimiterTest {
    @Test
    public void testSlidingTimeWindow() throws Exception {
        NC0008RateLimiter limiter = new NC0008RateLimiter();
        Executors.newScheduledThreadPool(5).scheduleWithFixedDelay(() -> {
            try {
                if (!limiter.slidingTimeWindow()) {
                    System.out.println("限流了...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 5000, 1000, TimeUnit.MILLISECONDS);
        TimeUnit.SECONDS.sleep(3600);
    }

    @Test
    public void testTokenBucket() throws Exception {
    }

    @Test
    public void testLeakageDrum() throws Exception {
    }
}