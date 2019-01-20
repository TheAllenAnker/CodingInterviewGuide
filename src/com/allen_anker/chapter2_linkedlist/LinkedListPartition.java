package com.allen_anker.chapter2_linkedlist;

public class LinkedListPartition {
    /**
     * Given a linked list and a pivot, partition the given linked list into three parts.
     * Left, middle, right part is numbers that are smaller than, equal to and bigger than the pivot respectively.
     * Time complexity must be smaller than O(n). Space complexity O(1).
     *
     * @param head
     * @param pivot
     * @return
     */
    public ListNode paritionLinkedList(ListNode head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode left = new ListNode(0), middle = new ListNode(0), right = new ListNode(0);
        ListNode curr = head, currLeft = left, currMid = middle, currRight = right;
        while (curr != null) {
            if (curr.val < pivot) {
                currLeft.next = curr;
                currLeft = currLeft.next;
            } else if (curr.val == pivot) {
                currMid.next = curr;
                currMid = currMid.next;
            } else {
                currRight.next = curr;
                currRight = currRight.next;
            }
            curr = curr.next;
        }

        if (left.next != null) {
            head = left.next;
            if (middle.next != null) {
                currLeft.next = middle.next;
                currMid.next = right.next;
            } else {
                currLeft.next = right.next;
            }
        } else if (middle.next != null) {
            head = middle.next;
            currMid.next = right.next;
        } else {
            head = right.next;
        }
        currRight.next = null;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        head.next = new ListNode(0);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(1);
        LinkedListPartition partition = new LinkedListPartition();
        LinkedListUtils.printLinkedListBeautifully(partition.paritionLinkedList(head, 5));
    }
}
