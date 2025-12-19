class Solution {

    // LINK - https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int ListNode temp1 = head;
        while(n != 0) {
            temp1 = temp1.next;
            n--;
        }

        if(temp1 == null) return head.next;

        ListNode temp2 = head;
        while(temp1.next != null) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        temp2.next = temp2.next.next;
        return head;
    }
}