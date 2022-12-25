package com.lc.structure.tree.binary;

import com.lc.structure.tree.NC0001BinaryTreeDeepFirstSearch;
import com.lc.structure.tree.TreeNode;

/**
 * Morris 遍历（DFS）
 *
 * @author gujixian
 * @since 2022/12/25
 */
public class Morris {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);

        Morris morris = new Morris();
        System.out.print("Morris的递归序：");
        morris.morris(root);
        System.out.print("\nMorris先序遍历：");
        morris.morrisBefore(root);
        System.out.print("\nMorris中序遍历：");
        morris.morrisMiddle(root);
        System.out.print("\nMorris后序遍历：");
        morris.morrisAfter(root);
    }

    public void morris(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        TreeNode<Integer> curr = node;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.value + " ");
                curr = curr.right;
                continue;
            }
            TreeNode<Integer> mostRight = curr.left;
            while (mostRight.right != null && mostRight.right != curr) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == null) {
                System.out.print(curr.value + " ");
                mostRight.right = curr;
                curr = curr.left;
            } else { // 第二次来到 curr 节点
                System.out.print(curr.value + " ");
                mostRight.right = null;
                curr = curr.right;
            }
        }
    }

    public void morrisBefore(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        TreeNode<Integer> curr = node;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.value + " ");
                curr = curr.right;
                continue;
            }
            TreeNode<Integer> mostRight = curr.left;
            while (mostRight.right != null && mostRight.right != curr) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == null) {
                System.out.print(curr.value + " ");
                mostRight.right = curr;
                curr = curr.left;
            } else {
                // System.out.print(curr.value + " ");
                mostRight.right = null;
                curr = curr.right;
            }
        }
    }

    public void morrisMiddle(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        TreeNode<Integer> curr = node;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.value + " ");
                curr = curr.right;
                continue;
            }
            TreeNode<Integer> mostRight = curr.left;
            while (mostRight.right != null && mostRight.right != curr) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == null) {
                // System.out.print(curr.value + " ");
                mostRight.right = curr;
                curr = curr.left;
            } else {
                System.out.print(curr.value + " ");
                mostRight.right = null;
                curr = curr.right;
            }
        }
    }

    /**
     * 分析：
     * 1、Morris序中第二次来到某个节点的时候，逆序打印其左子树的右边界
     * 2、最后逆序打印整个树的右边界
     * @param node
     */
    public void morrisAfter(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        TreeNode<Integer> curr = node;
        while (curr != null) {
            if (curr.left == null) {
                curr = curr.right;
                continue;
            }
            TreeNode<Integer> mostRight = curr.left;
            while (mostRight.right != null && mostRight.right != curr) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == null) {
                mostRight.right = curr;
                curr = curr.left;
            } else { // 第二次来到 curr 节点
                mostRight.right = null;
                // 还原 right 指针后再去打印
                printRightEdge(curr.left);
                curr = curr.right;
            }
        }
        printRightEdge(node);
    }

    private void printRightEdge(TreeNode<Integer> head) {
        TreeNode<Integer> tail = reverseRightEdge(head);
        TreeNode<Integer> curr = tail;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.right;
        }
        reverseRightEdge(tail);
    }

    private TreeNode<Integer> reverseRightEdge(TreeNode<Integer> node) {
        TreeNode<Integer> curr = node;
        TreeNode<Integer> prev = null;
        TreeNode<Integer> next = null;
        while (curr != null) {
            next = curr.right;
            curr.right = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
