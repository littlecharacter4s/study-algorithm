import com.alibaba.fastjson.JSON;
import com.lc.structure.graph.Graph;
import com.lc.structure.tree.TreeNode;
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
        Graph<Integer> graph = Graph.buildGraph(matrix);
        System.out.println(JSON.toJSONString(graph.nodeMap.get(1)));
    }
}
