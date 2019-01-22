package com.allen_anker.chapter2_linkedlist;

public class CombineTwoSortedLinkedLists {
    public static ListNode combineTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode curr1 = head1, curr2 = head2;
        ListNode newHead;
        if (curr1.val > curr2.val) {
            newHead = curr2;
            curr2 = curr2.next;
        } else {
            newHead = curr1;
            curr1 = curr1.next;
        }
        ListNode curr = newHead;
        while (curr1 != null || curr2 != null) {
            if (curr1 != null && curr2 != null) {
                if (curr1.val > curr2.val) {
                    curr.next = curr2;
                    curr2 = curr2.next;
                } else {
                    curr.next = curr1;
                    curr1 = curr1.next;
                }
            } else if (curr1 != null) {
                curr.next = curr1;
                curr1 = null;
            } else {
                curr.next = curr2;
                curr2 = null;
            }
            curr = curr.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(5);
        head1.next.next.next = new ListNode(6);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);
        head2.next.next.next = new ListNode(7);
        LinkedListUtils.printLinkedListBeautifully(combineTwoLists(head1, head2));
    }
}
