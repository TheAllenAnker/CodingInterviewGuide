package com.allen_anker.chapter2_linkedlist;

public class RecomstructLinkedList {
    /**
     * Insert the latter part of the linked list into the former part of the linked list.
     * Find the middle node first then combine the two separated lists together.
     *
     * @param head
     * @return
     */
    public static ListNode reconstructList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head, slowPrev = null;
        while (fast != null && fast.next != null) {
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        slowPrev.next = null;
        ListNode curr1 = head, curr2 = slow, next1 = curr1.next, next2 = curr2.next;
        while (true) {
            curr1.next = curr2;
            if (next1 != null) {
                curr2.next = next1;
                curr1 = next1;
                curr2 = next2;
                next1 = curr1.next;
                next2 = curr2.next;
            } else {
                break;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        LinkedListUtils.printLinkedListBeautifully(reconstructList(head));
    }
}
