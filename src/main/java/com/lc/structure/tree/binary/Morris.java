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
        System.out.print("先序遍历：");
        morris.morrisBefore(root);
        System.out.print("\n中序遍历：");
        morris.morrisMiddle(root);
        System.out.print("\n后序遍历：");
        morris.morrisAfter(root);
    }

    public void morrisBefore(TreeNode<Integer> node) {

    }

    public void morrisMiddle(TreeNode<Integer> node) {

    }

    public void morrisAfter(TreeNode<Integer> node) {

    }
}
