package com.allen_anker.chapter2_linkedlist;

public class JosephusKill {
    /**
     * Basic one, time complexity O(n*k), n is the size of the linked list.
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode josephusKill1(ListNode head, int k) {
        if (head == null || head.next == head || k <= 0) {
            return head;
        }

        ListNode last = head;
        while (last.next != head) {
            last = last.next;
        }

        ListNode curr = head;
        ListNode prev = last;
        int count = 1;
        while (curr.next != curr) {
            if (count == k) {
                prev.next = curr.next;
                count = 1;
            } else {
                count++;
            }
            prev = curr;
            curr = curr.next;
        }

        return curr;
    }

    /**
     * An advanced one, with time complexity of O(n).
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode josephusKill2(ListNode head, int k) {
        if (head == null || head.next == head || k <= 0) {
            return head;
        }

        ListNode curr = head.next;
        int size = 1;
        while (curr != head) {
            size++;
            curr = curr.next;
        }

        int temp = getLive(size, k);
        while (--temp != 0) {
            head = head.next;
        }
        head.next = head;

        return head;
    }

    private int getLive(int i, int k) {
        if (i == 1) {
            return 1;
        }

        return (getLive(i - 1, k) + k - 1) % i + 1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = head;
        JosephusKill jk = new JosephusKill();
        head = jk.josephusKill1(head, 2);
        System.out.println(head.val);
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(2);
        head2.next.next.next = head2;
        head2 = jk.josephusKill2(head2, 2);
        System.out.println(head2.val);
    }
}
