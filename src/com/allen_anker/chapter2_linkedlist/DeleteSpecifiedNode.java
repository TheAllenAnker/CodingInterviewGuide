package com.allen_anker.chapter2_linkedlist;

public class DeleteSpecifiedNode {
    /**
     * Delete the node in the middle of the linked list.
     * Find the middle node and its previous node first.
     *
     * @param head
     * @return
     */
    public ListNode deleteMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null) {
                prev = slow;
                slow = slow.next;
            }
        }
        if (prev == slow) {
            head = head.next;
        } else {
            prev.next = slow.next;
        }

        return head;
    }

    /**
     * Delete the node at pos a/b.
     *
     * @param head
     * @param a
     * @param b
     * @return
     */
    public ListNode deleteSpecifiedNode(ListNode head, int a, int b) {
        if (head == null || head.next == null || a > b || a <= 0) {
            return head;
        }

        int size = LinkedListUtils.getLinkedListSize(head);
        int deleteN = (int) Math.ceil((double)(a * size) / b);
        int i = 1;
        ListNode curr = head;
        ListNode prev = head;
        while (i++ < deleteN) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == prev) {
            head = head.next;
        } else {
            prev.next = curr.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        DeleteSpecifiedNode dsn = new DeleteSpecifiedNode();
        head = dsn.deleteMidNode(head);
        LinkedListUtils.printLinkedListBeautifully(head);
        head = dsn.deleteSpecifiedNode(head, 3, 3);
        LinkedListUtils.printLinkedListBeautifully(head);
    }
}
