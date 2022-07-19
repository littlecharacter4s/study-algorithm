import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BaseTest {
    @Test
    public void test() {
        Assert.assertTrue("这只是一个测试", LocalDate.now().getYear() < Integer.MAX_VALUE);
    }

    @Test
    public void testBase() {
        LinkedList linkedList = new LinkedList();
        Stack stack = new Stack();
        ArrayBlockingQueue queue = new ArrayBlockingQueue(12);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
    }
}
