import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class BaseTest {
    @Test
    public void test() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(12);
        Stack stack = new Stack();
        LinkedList linkedList = new LinkedList();
        Assert.assertTrue("这只是一个测试", LocalDate.now().getYear() < Integer.MAX_VALUE);
    }
}
