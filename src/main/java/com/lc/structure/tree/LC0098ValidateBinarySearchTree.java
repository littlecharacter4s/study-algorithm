package com.lc.structure.tree;

/**
 * @author gujixian
 * @since 2022/12/5
 */
public class LC0098ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        System.out.println(new LC0098ValidateBinarySearchTree().isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }
        Info lInfo = this.getChildrenInfo(root.left);
        Info rInfo = this.getChildrenInfo(root.right);
        return this.isBST(root, lInfo, rInfo);
    }

    private Info getChildrenInfo(TreeNode node) {
        if (node == null) {
            return new Info(true);
        }
        Info lInfo = this.getChildrenInfo(node.left);
        Info rInfo = this.getChildrenInfo(node.right);
        // 先判断左右子树是否是二叉搜索树
        boolean isBST = this.isBST(node, lInfo, rInfo);
        if (!isBST) {
            return new Info(false);
        }
        // 构建当前节点的信息，返回给上层
        int min = lInfo.min == null ? node.val : lInfo.min;
        int max = rInfo.max == null ? node.val : rInfo.max;
        return new Info(true, min, max);
    }

    private boolean isBST(TreeNode node, Info lInfo, Info rInfo) {
        if (!lInfo.isBST || !rInfo.isBST) {
            return false;
        }
        return (lInfo.max == null || lInfo.max < node.val) && (rInfo.min == null || rInfo.min > node.val);
    }

    private static class Info {
        public boolean isBST;
        public Integer min;
        public Integer max;

        public Info(boolean isBST) {
            this.isBST = isBST;
        }

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
