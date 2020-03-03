package com.yxj.leetcode;

/**
 * @author:ycjx
 * @descriptio 恢复二叉搜索树
 * <p>
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * @create:2020-03-03 13:47
 */
public class RecoverBinarySearchTree {


    public void recoverTree(TreeNode root) {
        if (null == root) {
            return;
        }
        TreeNode node1 = null;
        TreeNode node2 = null;

        TreeNode mostRight = null;
        TreeNode cur = root;

        TreeNode pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                //第一次遍历是找到中序遍历中 cur节点和 他的left节点与上一节点并且关联起来
                //第二次到这说明遍历到了，就要取消关联
                //第一次遍历的时候 ，如果发现没有关联过，说明没有遍历过
                //第二次说明遍历完了就取消关联从right开始
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    //找到了cur的上一个节点，下一步开始找cur.left的上一个节点
                    cur = cur.left;
                    continue;
                } else {  // 第二次遍历到了
                    mostRight.right = null;
                }
            }

            if (pre != null && pre.val > cur.val) {
                node1 = node1 == null ? pre : node1;
                node2 = cur;
            }

            //从上面的操作，当root的 left的节点，都会与他上一个节点做了关联
            //所以直接从最左的节点 直接right 就ok了
            pre = cur;
            cur = cur.right;
        }

        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}


