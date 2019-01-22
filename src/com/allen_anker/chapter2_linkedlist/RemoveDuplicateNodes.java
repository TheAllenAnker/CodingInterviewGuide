package com.allen_anker.chapter2_linkedlist;

import java.util.HashSet;

public class RemoveDuplicateNodes {
    /**
     * O(n) time complexity but with O(n) space complexity.
     *
     * @param head
     * @return
     */
    public static ListNode removeDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        HashSet<Integer> nodeSet = new HashSet<>();
        ListNode curr = head, prev = null;
        while (curr != null) {
            if (nodeSet.contains(curr.val)) {
                prev.next = curr.next;
            } else {
                nodeSet.add(curr.val);
            }
            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    /**
     * O(1) space complexity but with O(n^2) time complexity.
     * Similar to selection sort.
     *
     * @param head
     * @return
     */
    public static ListNode removeDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode check = head;
        while (check != null) {
            ListNode prev = check;
            ListNode curr = check.next;
            while (curr != null) {
                if (curr.val == check.val) {
                    prev.next = curr.next;
                }
                prev = curr;
                curr = curr.next;
            }
            check = check.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
//        LinkedListUtils.printLinkedListBeautifully(removeDuplicates1(head));
        LinkedListUtils.printLinkedListBeautifully(removeDuplicates2(head));
    }
}
