package LinkedList;

import java.util.Stack;

public class ReverseLinkedList {
    public static LLNode reverse(LLNode head) {
        Stack<LLNode> st = new Stack<>();

        while(head != null) {
            st.push(head);
            head = head.next;
        }
        LLNode revHead = null;
        LLNode tail = null;
        while(!st.isEmpty()) {
            if(revHead == null) {
                revHead = st.pop();
                tail = revHead;
            } else {
                tail.next = st.pop();
                tail = tail.next;
                tail.next = null;
            }
        }

        return revHead;
    }

    public static LLNode reverseR(LLNode head) {
        return reverseRHelper(null, head);
    }

    public static LLNode reverseRHelper(LLNode prev, LLNode cur) {
        if(cur == null) {

        }
        return null;
    }


}
