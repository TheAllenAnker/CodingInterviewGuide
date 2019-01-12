package com.allen_anker.chapter2_linkedlist;

public class ReverseLinkedList {
    public ListNode reverseSingleLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode curr = head;
        while (head != null) {
            curr = head;
            head = head.next;
            curr.next = pre;
            pre = curr;
        }

        return curr;
    }

    public DoubleLinkedNode reverseDoubleLinked(DoubleLinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        DoubleLinkedNode pre = null;
        DoubleLinkedNode curr = head;
        while (head != null) {
            curr = head;
            head = head.next;
            curr.next = pre;
            if (pre != null) {
                pre.prev = curr;
            }
            pre = curr;
        }

        return curr;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        ReverseLinkedList rll = new ReverseLinkedList();
        head = rll.reverseSingleLinkedList(head);
        LinkedListUtils.printLinkedListBeautifully(head);
        DoubleLinkedNode head1 = new DoubleLinkedNode(0);
        head1.next = new DoubleLinkedNode(1);
        head1.next.next = new DoubleLinkedNode(2);
        head1 = rll.reverseDoubleLinked(head1);
        System.out.println();
    }
}
