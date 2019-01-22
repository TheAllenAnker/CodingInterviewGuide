package com.allen_anker.chapter2_linkedlist;

public class RemoveNodesWithValue {
    public static ListNode removeNodesWithValue(ListNode head, int num) {
        if (head == null) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            if (curr.val == num) {
                if (curr == head) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
            }
            prev = curr;
            curr = curr.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(2);
        LinkedListUtils.printLinkedListBeautifully(removeNodesWithValue(head, 2));
        LinkedListUtils.printLinkedListBeautifully(removeNodesWithValue(head, 1));
    }
}
