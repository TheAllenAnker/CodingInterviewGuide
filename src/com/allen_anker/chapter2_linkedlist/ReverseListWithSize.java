package com.allen_anker.chapter2_linkedlist;

import java.util.Stack;

public class ReverseListWithSize {
    /**
     * Reverse the given linked list by groups with the size K.
     * For example: 1->2->3->4->5->6->7 with k = 3, result is: 3->2->1->6->5->4->7.
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseWithSize(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        ListNode res = new ListNode(1);
        ListNode curr = head;
        Stack<ListNode> stack = new Stack<>();
        ListNode currHead = res;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
            if (stack.size() == k) {
                while (!stack.isEmpty()) {
                    currHead.next = stack.pop();
                    currHead = currHead.next;
                }
                currHead.next = null;
            }
        }

        ReverseLinkedList rll = new ReverseLinkedList();
        if (!stack.isEmpty()) {
            ListNode lastPart = currHead;
            while (!stack.isEmpty()) {
                currHead.next = stack.pop();
                currHead = currHead.next;
            }
            currHead.next = null;
            lastPart.next = rll.reverseSingleLinkedList(lastPart.next);
        }

        return res.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
//        head.next.next.next.next.next.next.next.next = new ListNode(9);
        LinkedListUtils.printLinkedListBeautifully(head);
        LinkedListUtils.printLinkedListBeautifully(reverseWithSize(head, 3));
    }
}
