package com.allen_anker.chapter2_linkedlist;

public class FirstCommonNode {
    /**
     * Find the first common node of two linked lists.
     * Time complexity: O(M + N), Space complexity: O(1).
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode getFirstCommonNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        ListNode circleEntry1 = LinkedListUtils.findCircleEntry(head1);
        ListNode circleEntry2 = LinkedListUtils.findCircleEntry(head2);
        // two linked lists without or with circle
        if (circleEntry1 == circleEntry2) {
            // without circle or have the same circle
            if (circleEntry1 != null) {
                return circleEntry1;
            }
            int size1 = 1, size2 = 1;
            ListNode curr1 = head1, curr2 = head2;
            while (curr1.next != null) {
                size1++;
                curr1 = curr1.next;
            }
            while (curr2.next != null) {
                size2++;
                curr2 = curr2.next;
            }
            if (curr1 != curr2) {
                return null;
            } else {
                curr1 = head1;
                curr2 = head2;
                int steps = size1 - size2;
                while (steps != 0) {
                    if (steps > 0) {
                        curr1 = curr1.next;
                        steps--;
                    } else {
                        curr2 = curr2.next;
                        steps++;
                    }
                }
                while (curr1 != null && curr2 != null) {
                    if (curr1 == curr2) {
                        return curr1;
                    } else {
                        curr1 = curr1.next;
                        curr2 = curr2.next;
                    }
                }
            }
        } else if (circleEntry1 != null && circleEntry2 != null) {
            ListNode curr = circleEntry1.next;
            while (curr != circleEntry1) {
                if (curr == circleEntry2) {
                    break;
                }
                curr = curr.next;
            }
            // if the two entries are in the same circle
            if (curr == circleEntry2) {
                return circleEntry1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        node3.next = node2;
        head1.next = new ListNode(1);
        head2.next = new ListNode(1);
        head1.next.next = node2;
        head2.next.next = node3;
        System.out.println(getFirstCommonNode(head1, head2).val);
    }
}
