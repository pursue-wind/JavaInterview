package io.github.mirrormingzz.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树中序遍历 - 非递归
 *
 * @author Mireal
 */
public class InOrderTraversal {
    class Command {
        String s;
        TreeNode node;

        Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.empty()) {
            Command command = stack.pop();

            if ("print".equals(command.s))
                res.add(command.node.val);

            else {
                if ("go".equals(command.s)) {
                    if (null != command.node.right)
                        stack.push(new Command("go", command.node.right));

                    stack.push(new Command("print", command.node));

                    if (null != command.node.left)
                        stack.push(new Command("go", command.node.left));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<Integer> list = new InOrderTraversal().inOrderTraversal(root);
        System.out.println(list);
    }
}
