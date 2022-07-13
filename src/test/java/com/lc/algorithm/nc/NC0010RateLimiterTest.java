package com.lc.algorithm.nc;

import com.lc.algorithm.nc.NC0010RateLimiter;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NC0010RateLimiterTest {
    @Test
    public void testSlidingTimeWindow() throws Exception {
        NC0010RateLimiter limiter = new NC0010RateLimiter();
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