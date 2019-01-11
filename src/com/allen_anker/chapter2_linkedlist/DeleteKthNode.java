package com.allen_anker.chapter2_linkedlist;

public class DeleteKthNode {
    public ListNode deleteSingleLinkedListNode(ListNode head, int k) {
        if (head == null || k <= 0) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        ListNode target = head;
        for (int i = 0; i < k; i++) {
            if (target == null) {
                throw new IllegalArgumentException("Invalid parameter k");
            }
            target = target.next;
        }
        ListNode helper = head;
        ListNode prev = head;
        while (target != null) {
            prev = helper;
            target = target.next;
            helper = helper.next;
        }
        if (prev == helper) {
            head = prev.next;
        } else {
            prev.next = helper.next;
        }

        return head;
    }

    public DoubleLinkedNode deleteDoubleLinkedListNode(DoubleLinkedNode head, int k) {
        if (head == null || k <= 0) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        DoubleLinkedNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        for (int i = 0; i < k - 1; i++) {
            curr = curr.prev;
        }

        // the node to be deleted is the tail
        if (curr.next == null) {
            curr.prev.next = null;
        } else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        DeleteKthNode dkn = new DeleteKthNode();
        head = dkn.deleteSingleLinkedListNode(head, 3);
        LinkedListUtils.printLinkedListBeautifully(head);
    }
}
