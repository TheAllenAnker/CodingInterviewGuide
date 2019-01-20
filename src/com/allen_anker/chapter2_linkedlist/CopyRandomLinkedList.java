package com.allen_anker.chapter2_linkedlist;

public class CopyRandomLinkedList {
    /**
     * No other data structures are used and time complexity is O(n).
     *
     * @param head
     * @return
     */
    public static RandomNode copyOfRandomList(RandomNode head) {
        if (head == null) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        RandomNode curr = head;
        RandomNode oldNext;
        while (curr != null) {
            oldNext = curr.next;
            curr.next = new RandomNode(curr.val);
            curr.next.next = oldNext;
            curr = oldNext;
        }

        curr = head;
        RandomNode newHead = head.next;
        RandomNode newCurr = newHead;
        while (curr != null) {
            curr.next.rand = curr.rand.next;
            curr = curr.next.next;
        }
        curr = head;
        while (curr != null) {
            curr.next = curr.next.next;
            newCurr.next = newCurr.next == null ? null : newCurr.next.next;
            curr = curr.next;
            newCurr = newCurr.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        RandomNode head = new RandomNode(1);
        RandomNode node2 = new RandomNode(2);
        RandomNode node3 = new RandomNode(3);
        head.next = node2;
        head.rand = node3;
        node2.next = node3;
        node2.rand = head;
        node3.rand = head;
        RandomNode newHead = copyOfRandomList(head);
        System.out.println(newHead.val);
    }
}

class RandomNode {
    int val;
    RandomNode next;
    RandomNode rand;

    public RandomNode(int val) {
        this.val = val;
    }
}
