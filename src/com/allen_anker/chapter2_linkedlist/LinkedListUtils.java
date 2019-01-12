package com.allen_anker.chapter2_linkedlist;

public class LinkedListUtils {
    public static void printLinkedListBeautifully(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            curr = curr.next;
            if (curr != null) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    public static int getLinkedListSize(ListNode head) {
        if (head == null) {
            return 0;
        }

        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            size++;
            curr = curr.next;
        }

        return size;
    }
}
