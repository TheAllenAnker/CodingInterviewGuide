package com.allen_anker.chapter3_binarytree;

import java.util.HashMap;

public class DisjointSets {
    public HashMap<TreeNode, TreeNode> fatherMap;
    public HashMap<TreeNode, Integer> rankMap;

    public DisjointSets() {
        fatherMap = new HashMap<>();
        rankMap = new HashMap<>();
    }

    public void makeSets(TreeNode head) {
        fatherMap.clear();
        rankMap.clear();

    }

    /**
     * Time complexity has to be O(M+N), M is the number of node in the tree, N is the size of arr queries.
     *
     * @param root
     * @param queries
     * @return
     */
    public static TreeNode[] getNearestCommonAncestors(TreeNode root, Query[] queries) {
        if (root == null) {
            throw new IllegalArgumentException("Invalid parameter: tree root cannot be null");
        }
        if (queries == null || queries.length == 0) {
            return null;
        }

        TreeNode[] ancestors = new Tarjan().query(root, queries);

        return ancestors;
    }

    public TreeNode findFather(TreeNode node) {
        TreeNode father = fatherMap.get(node);
        if (father != node) {
            father = findFather(father);
        }
        // set the nodes'parent on the path to the root father
        fatherMap.put(node, father);

        return father;
    }

    public void union(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }

        TreeNode father1 = fatherMap.get(node1);
        TreeNode father2 = fatherMap.get(node2);
        if (father1 != father2) {
            int rank1 = rankMap.get(father1);
            int rank2 = rankMap.get(father2);
            if (rank1 < rank2) {
                fatherMap.put(father1, father2);
            } else if (rank1 > rank2) {
                fatherMap.put(father2, father1);
            } else {
                fatherMap.put(father2, father1);
                rankMap.put(father1, rank1 + 1);
            }
        }
    }

    private void preOrderMake(TreeNode head) {
        if (head == null) {
            return;
        }

        fatherMap.put(head, head);
        rankMap.put(head, 0);
        preOrderMake(head.left);
        preOrderMake(head.right);
    }

    public static void main(String[] args) {

    }
}

class Query {
    public TreeNode node1;
    public TreeNode node2;

    public Query(TreeNode node1, TreeNode node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
}