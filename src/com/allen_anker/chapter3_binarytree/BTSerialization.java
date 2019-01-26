package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BTSerialization {
    public static TreeNode deserializeBTByLevel(String serializedTree) {
        if (serializedTree == null) {
            return null;
        }

        String[] values = serializedTree.split("#");
        Queue<TreeNode> buildingQueue = new LinkedList<>();
        Queue<String> valueQueue = new LinkedList<>();
        for (int i = 1; i < values.length; i++) {
            valueQueue.offer(values[i]);
        }
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        buildingQueue.offer(root);
        TreeNode curr;
        while (!buildingQueue.isEmpty()) {
            curr = buildingQueue.poll();
            String leftValue = valueQueue.poll();
            String rightValue = valueQueue.poll();
            if (!leftValue.equals("$")) {
                TreeNode leftNode = new TreeNode(Integer.valueOf(leftValue));
                buildingQueue.offer(leftNode);
                curr.left = leftNode;
            }
            if (!rightValue.equals("$")) {
                TreeNode rightNode = new TreeNode(Integer.valueOf(rightValue));
                buildingQueue.offer(rightNode);
                curr.right = rightNode;
            }
        }

        return root;
    }

    public static String serializeBTByLevel(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        TreeNode tagNode = new TreeNode(0);
        String postfix = "#";
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == tagNode) {
                sb.append("$").append(postfix);
                continue;
            }
            if (curr.left == null) {
                queue.offer(tagNode);
            } else {
                queue.offer(curr.left);
            }
            if (curr.right == null) {
                queue.offer(tagNode);
            } else {
                queue.offer(curr.right);
            }
            sb.append(curr.value).append(postfix);
        }

        return sb.toString();
    }

    public static TreeNode preorderDeserialization(String serializedStr) {
        if (serializedStr == null) {
            return null;
        }

        String[] values = serializedStr.split("#");
        Queue<String> valueQueue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            valueQueue.offer(values[i]);
        }

        return preorderDeserializationCore(valueQueue);
    }

    private static TreeNode preorderDeserializationCore(Queue<String> valueQueue) {
        String value = valueQueue.poll();
        if (value.equals("$")) {
            return null;
        }

        TreeNode head = new TreeNode(Integer.parseInt(value));
        head.left = preorderDeserializationCore(valueQueue);
        head.right = preorderDeserializationCore(valueQueue);

        return head;
    }

    public static String preorderSerialization(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        preorderSerializationCore(root, sb, "#");
        return sb.toString();
    }

    private static void preorderSerializationCore(TreeNode root, StringBuilder sb, String postfix) {
        if (root == null) {
            sb.append("$").append(postfix);
            return;
        }

        sb.append(root.value).append(postfix);
        preorderSerializationCore(root.left, sb, postfix);
        preorderSerializationCore(root.right, sb, postfix);
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.getSimpleTree();
        String str = preorderSerialization(root);
        System.out.println(str);
        root = preorderDeserialization(str);
        TreeUtils.printTreeBeautifully(root);
        System.out.println("======================");
        System.out.println(serializeBTByLevel(root));
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        String str2 = serializeBTByLevel(root);
        System.out.println(str2);
        TreeUtils.printTreeBeautifully(deserializeBTByLevel(str2));
        TreeUtils.printTreeBeautifully(deserializeBTByLevel(serializeBTByLevel(root2)));
    }
}
