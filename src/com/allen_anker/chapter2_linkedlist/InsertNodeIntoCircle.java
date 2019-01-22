package com.allen_anker.chapter2_linkedlist;

public class InsertNodeIntoCircle {
    public static ListNode insertNodeIntoCircle(ListNode head, int num) {
        if (head == null) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        ListNode tail = getCircleTail(head);
        ListNode prev = tail;
        ListNode curr = head;
        ListNode newNode = new ListNode(num);
        while (curr != tail) {
            if (curr.val >= num) {
                prev.next = newNode;
                newNode.next = curr;
                if (curr == head) {
                    head = newNode;
                }
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        if (curr == tail) {
            tail.next = newNode;
            newNode.next = head;
        }

        return head;
    }

    private static ListNode getCircleTail(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.next == head) {
                break;
            }
            curr = curr.next;
        }

        return curr;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = head;
        ListNode newHead = insertNodeIntoCircle(head, 4);
        System.out.println(newHead.val);
    }
}
