package io.github.mirrormingzz.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中序遍历 - 递归
 *
 * @author Mireal
 */
public class InOrderTraversalRecursion {
    /**
     * 递归写法
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<Integer> list = new InOrderTraversalRecursion().inorderTraversal(root);
        System.out.println(list);
    }
}
