package com.allen_anker.chapter2_linkedlist;

public class AddLinkedList {
    public static ListNode addTwoLinkedLists(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ReverseLinkedList rll = new ReverseLinkedList();
        head1 = rll.reverseSingleLinkedList(head1);
        head2 = rll.reverseSingleLinkedList(head2);
        ListNode res = new ListNode(0);
        int plus = 0;
        ListNode curr1 = head1;
        ListNode curr2 = head2;
        ListNode newCurr = res;
        ListNode newNode;
        while (curr1 != null || curr2 != null) {
            int sum;
            if (curr1 != null && curr2 != null) {
                sum = curr1.val + curr2.val + plus;
            } else if (curr1 == null) {
                sum = curr2.val + plus;
            } else {
                sum = curr1.val + plus;
            }
            if (sum > 9) {
                sum -= 10;
                plus = 1;
            } else {
                plus = 0;
            }
            newNode = new ListNode(sum);
            newCurr.next = newNode;
            if (curr1 != null) {
                curr1 = curr1.next;
            }
            if (curr2 != null) {
                curr2 = curr2.next;
            }
            newCurr = newCurr.next;
        }
        if (plus == 1) {
            newCurr.next = new ListNode(1);
        }

        return rll.reverseSingleLinkedList(res.next);
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(7);
        ListNode head2 = new ListNode(6);
        head2.next = new ListNode(3);
        ListNode sumList = addTwoLinkedLists(head1, head2);
        LinkedListUtils.printLinkedListBeautifully(sumList);
    }
}
