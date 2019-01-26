package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BTSerialization {
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
    }
}
