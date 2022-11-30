import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lc.structure.graph.Graph;
import com.lc.structure.tree.TreeNode;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BaseTest {
    @Test
    public void test() {
        this.testStack();
        // this.testGraph();
    }

    private void testStack() {
        // 这个test的目的是，切记：递归的过程中是可以记录一些东西的！！！
        Deque<Integer> stack = new LinkedList<>(Arrays.asList(1, 2, 3));
        System.out.println(stack);
        this.reverse(stack);
        System.out.println(stack);
    }

    private void reverse(Deque<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }
        int bottom = this.getStackBottomElement(stack);
        this.reverse(stack);
        stack.offerLast(bottom);
    }

    private Integer getStackBottomElement(Deque<Integer> stack) {
        Integer result = stack.pollLast();
        if (stack.isEmpty()) {
            return result;
        }
        int bottom = this.getStackBottomElement(stack);
        stack.offerLast(result);
        return bottom;
    }

    private void testGraph() {
        Integer[][] matrix = new Integer[3][3];
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 3;
        matrix[1][0] = 0;
        matrix[1][1] = 2;
        matrix[1][2] = 3;
        matrix[2][0] = 1;
        matrix[2][1] = 2;
        matrix[2][2] = 5;
        Graph graph = Graph.buildGraph(matrix);
        System.out.println(JSON.toJSONString(graph.nodeMap.get(1)));
        System.out.println(JSON.toJSONString(graph.graph.get(1)));

        graph.graph = new HashMap<>();
        graph.graph.put(0, new HashSet<>(Arrays.asList(new Pair<>(1, 1), new Pair<>(3, 1), new Pair<>(4, 1))));
        graph.graph.put(1, new HashSet<>(Arrays.asList(new Pair<>(0, 1), new Pair<>(2, 1))));
        graph.graph.put(2, new HashSet<>(Arrays.asList(new Pair<>(1, 1), new Pair<>(3, 1))));
        graph.graph.put(3, new HashSet<>(Arrays.asList(new Pair<>(0, 1), new Pair<>(2, 1), new Pair<>(4, 1))));
        graph.graph.put(4, new HashSet<>(Arrays.asList(new Pair<>(0, 1), new Pair<>(3, 1))));
        this.print(graph);
    }

    private void print(Graph graph) {
        Set<Integer> visitedNode = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(0);
        visitedNode.add(0);
        while (!stack.isEmpty()) {
            int node = stack.pollLast();
            System.out.println(node);
            for (Pair<Integer, Integer> edge : graph.graph.get(node)) {
                if (!visitedNode.contains(edge.getKey())) {
                    visitedNode.add(edge.getKey());
                    stack.offerLast(edge.getKey());
                }
            }
        }
    }
}
