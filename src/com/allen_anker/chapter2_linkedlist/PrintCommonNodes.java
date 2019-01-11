package com.allen_anker.chapter2_linkedlist;

public class PrintCommonNodes {
    /**
     * Print the common nodes value of two linked list.
     * Each element in both lists is sorted based on its value.
     *
     * @param head1
     * @param head2
     */
    public void printLinkedListCommonNodes(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return;
        }

        ListNode curr1 = head1;
        ListNode curr2 = head2;
        while (curr1 != curr2 && curr1 != null && curr2 != null) {
            if (curr1.val < curr2.val) {
                curr1 = curr1.next;
            } else if (curr1.val > curr2.val) {
                curr2 = curr2.next;
            } else {
                curr1 = curr1.next;
            }
        }

        if (curr1 == curr2) {
            while (curr1 != null) {
                System.out.print(curr1.val);
                curr1 = curr1.next;
                if (curr1 != null) {
                    System.out.print("->");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        head1.next = new ListNode(2);
        head2.next = new ListNode(1);
        ListNode common = new ListNode(3);
        common.next = new ListNode(4);
        head1.next.next = common;
        head2.next.next = common;
        new PrintCommonNodes().printLinkedListCommonNodes(head1, head2);
    }
}
