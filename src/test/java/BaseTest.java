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
        TreeNode<Integer> root = new TreeNode<>(10);
        TreeNode<Integer> left = new TreeNode<>(5);
        TreeNode<Integer> right = new TreeNode<>(15);
        root.left = left;
        root.right = right;
        left.left = new TreeNode<>(4);
        left.right = new TreeNode<>(6);
        right.left = new TreeNode<>(11);
        right.right = new TreeNode<>(16);

        System.out.println(this.isBST(root));
    }

    int preValue = Integer.MIN_VALUE;
    public boolean isBST(TreeNode<Integer> node) {
        if (node == null) {
            return true;
        }
        if (this.isBST(node.left) && preValue < node.value) {
            preValue = node.value;
        } else {
            return false;
        }
        return this.isBST(node.right);
    }
}
