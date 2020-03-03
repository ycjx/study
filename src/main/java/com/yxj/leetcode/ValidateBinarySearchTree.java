package com.yxj.leetcode;

/**
 * @author:yuxj
 * @descriptio 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @create:2019-04-05 12:54
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(10);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(20);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        ValidateBinarySearchTree validateBinarySearchTree = new ValidateBinarySearchTree();
        System.out.println(validateBinarySearchTree.isValidBST(treeNode));


    }

    private long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        //用中序排序懒得写，看了答案看到这一行给爷整蒙了
        //屌啊，也算是用了中序排序的思想
        return (root == null) || (isValidBST(root.left) && prev < (prev = root.val) && isValidBST(root.right));

    }




}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
