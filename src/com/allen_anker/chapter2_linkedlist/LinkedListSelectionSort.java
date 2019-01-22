package com.allen_anker.chapter2_linkedlist;

public class LinkedListSelectionSort {
    /**
     * Use selection sort to sort a given linked list.
     * Space complexity: O(1).
     *
     * @param head
     * @return
     */
    public static ListNode sortLinkedList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        while (curr != null) {
            ListNode checking = curr.next;
            while (checking != null) {
                int val = checking.val;
                if (val < curr.val) {
                    int temp = curr.val;
                    curr.val = val;
                    checking.val = temp;
                }
                checking = checking.next;
            }
            curr = curr.next;
        }

        return head;
    }

    public static ListNode sortLinkedList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sortedTail = null;
        ListNode curr = head;
        ListNode newHead = new ListNode(0);
        while (curr != null) {
            ListNode smallestPrev = getSmallestPrev(curr);
            if (smallestPrev == null && sortedTail != null) {
                sortedTail.next = curr;
                sortedTail = sortedTail.next;
            } else {
                // the sorted part is empty
                if (sortedTail == null) {
                    newHead.next = smallestPrev.next;
                    sortedTail = newHead.next;
//                    sortedTail.next = curr;
                } else {
                    sortedTail.next = smallestPrev.next;
                    sortedTail = sortedTail.next;
                }
                smallestPrev.next = smallestPrev.next.next;
            }
            if (smallestPrev == null) {
                curr = curr.next;
            }
        }

        return newHead.next;
    }

    private static ListNode getSmallestPrev(ListNode head) {
        ListNode curr = head.next, smallestPrev = null, prev = head, smallest = head;
        while (curr != null) {
            if (curr.val < smallest.val) {
                smallestPrev = prev;
                smallest = curr;
            }
            prev = curr;
            curr = curr.next;
        }

        return smallestPrev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(0);
//        LinkedListUtils.printLinkedListBeautifully(sortLinkedList1(head));
        LinkedListUtils.printLinkedListBeautifully(sortLinkedList2(head));
    }
}
