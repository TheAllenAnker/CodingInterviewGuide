package com.allen_anker.chapter3_binarytree;

import com.allen_anker.chapter1_stackandqueue.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GetPossibleBTHeads {
    public static List<TreeNode> getPossibleHeads(int n) {
        return generateHeads(1, n);
    }

    private static List<TreeNode> generateHeads(int start, int end) {
        List<TreeNode> heads = new ArrayList<>();
        if (start > end) {
            heads.add(null);
        }
        TreeNode head;
        for (int i = start; i < end + 1; i++) {
            head = new TreeNode(i);
            List<TreeNode> lSubs = generateHeads(start, i - 1);
            List<TreeNode> rSubs = generateHeads(i + 1, end);
            for (TreeNode l : lSubs) {
                for (TreeNode r : rSubs) {
                    head.left = l;
                    head.right = r;
                    heads.add(cloneTreeNode(head));
                }
            }
        }

        return heads;
    }

    private static TreeNode cloneTreeNode(TreeNode head) {
        if (head == null) {
            return null;
        }

        TreeNode res = new TreeNode(head.value);
        res.left = cloneTreeNode(head.left);
        res.right = cloneTreeNode(head.right);

        return res;
    }

    public static void main(String[] args) {
        for (TreeNode node : getPossibleHeads(2)) {
            TreeUtils.printTreeBeautifully(node);
        }
    }
}
