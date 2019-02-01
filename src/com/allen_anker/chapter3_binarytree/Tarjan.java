package com.allen_anker.chapter3_binarytree;

import java.util.HashMap;
import java.util.LinkedList;

public class Tarjan {
    private HashMap<TreeNode, LinkedList<TreeNode>> queryMap;
    private HashMap<TreeNode, LinkedList<Integer>> indexMap;
    private HashMap<TreeNode, TreeNode> ancestorMap;
    private DisjointSets sets;

    public Tarjan() {
        queryMap = new HashMap<>();
        indexMap = new HashMap<>();
        ancestorMap = new HashMap<>();
        sets = new DisjointSets();
    }

    public TreeNode[] query(TreeNode head, Query[] queries) {
        TreeNode[] ans = new TreeNode[queries.length];
        setQueries(queries, ans);
        sets.makeSets(head);
        setAnswers(head, ans);

        return ans;
    }

    private void setQueries(Query[] queries, TreeNode[] ans) {
        TreeNode node1 = null;
        TreeNode node2 = null;
        for (int i = 0; i < ans.length; i++) {
            node1 = queries[i].node1;
            node2 = queries[i].node2;
            if (node1 == node2 || node1 == null || node2 == null) {
                ans[i] = node1 != null ? node1 : node2;
            } else {
                if (!queryMap.containsKey(node1)) {
                    queryMap.put(node1, new LinkedList<>());
                    indexMap.put(node1, new LinkedList<>());
                }
                if (!queryMap.containsKey(node1)) {
                    queryMap.put(node2, new LinkedList<>());
                    indexMap.put(node2, new LinkedList<>());
                }
                queryMap.get(node1).add(node2);
                indexMap.get(node1).add(i);
                queryMap.get(node2).add(node1);
                indexMap.get(node2).add(i);
            }
        }
    }

    private void setAnswers(TreeNode head, TreeNode[] ans) {
        if (head == null) {
            return;
        }

        setAnswers(head.left, ans);
        sets.union(head.left, head);
        ancestorMap.put(sets.findFather(head), head);
        setAnswers(head.right, ans);
        sets.union(head.right, head);
        ancestorMap.put(sets.findFather(head), head);
        LinkedList<TreeNode> nodeList = queryMap.get(head);
        LinkedList<Integer> indexList = indexMap.get(head);
        TreeNode node = null;
        TreeNode nodeFather = null;
        int index = 0;
        while (nodeList != null && !nodeList.isEmpty()) {
            node = nodeList.poll();
            index = indexList.poll();
            nodeFather = sets.findFather(node);
            if (ancestorMap.containsKey(nodeFather)) {
                ans[index] = ancestorMap.get(nodeFather);
            }
        }
    }
}
