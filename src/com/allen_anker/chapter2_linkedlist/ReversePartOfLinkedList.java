package com.allen_anker.chapter2_linkedlist;

public class ReversePartOfLinkedList {
    public ListNode reversePartOfLinkedList(ListNode head, int from, int to) {
        if (head == null || head.next == null || from >= to || from < 1) {
            return head;
        }

        int size = 1;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        if (from >= size) {
            return head;
        }
        curr = head;

        int count = 1;
        ListNode prev = null;
        while (count != from && curr != null) {
            prev = curr;
            curr = curr.next;
            count++;
        }
        ListNode leftHead = prev;
        ListNode tail = head;
        if (curr != null) {
            tail = curr;
            prev = curr;
            curr = curr.next;
            ListNode next;
            count++;
            while (curr != null && count <= to) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }
        }
        tail.next = curr;

        if (leftHead == null) {
            return prev;
        } else {
            leftHead.next = prev;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        ReversePartOfLinkedList rpll = new ReversePartOfLinkedList();
        head = rpll.reversePartOfLinkedList(head, 3, 4);
        LinkedListUtils.printLinkedListBeautifully(head);
    }
}
