package com.allen_anker.chapter2_linkedlist;

public class PalindromeList {
    /**
     * Push the whole list into a stack or half of the list. This will need space complexity of O(n).
     * Or reverse half of the list and then reverse it back. With time complexity of O(n) and space complexity of O(1).
     *
     * @param head
     * @return
     */
    public boolean isPalindromeList(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // find the middle node of the linked list first
        ListNode slow = head;
        ListNode fast = head;
        ListNode beforeSlow = head;
        while (fast != null && fast.next != null) {
            beforeSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ReverseLinkedList rll = new ReverseLinkedList();
        // if fast != null then the size of the linked list is a odd number
        ListNode rightHalfHead = rll.reverseSingleLinkedList(slow);
        if (fast != null) {
            slow.next = null;
        } else {
            beforeSlow.next = null;
        }

        boolean res = true;
        ListNode curr1 = head;
        ListNode curr2 = rightHalfHead;
        while (curr1 != null && curr2 != null) {
            if (curr1.val != curr2.val) {
                res = false;
                break;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        if (curr1 != null || curr2 != null) {
            res = false;
        }

        beforeSlow.next = rll.reverseSingleLinkedList(rightHalfHead);

        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        PalindromeList pl = new PalindromeList();
        System.out.println(pl.isPalindromeList(head));
        LinkedListUtils.printLinkedListBeautifully(head);
    }
}
