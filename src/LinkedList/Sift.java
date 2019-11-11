package LinkedList;

public class Sift {

    public static void sift(LLNode head) {
        LLNode ln0H = null;
        LLNode ln0T = null;
        LLNode ln1H = null;
        LLNode ln1T = null;
        LLNode ln2H = null;
        LLNode ln2T = null;
        CreateLinkList.printList(head);
        while(head != null) {
            if(head.value == 0) {
                LLNode temp = head;
                head = head.next;
                if(ln0H == null) {
                    ln0H = temp;
                }
                ln0T = insertTail(temp, ln0T);
            }

            if(head.value == 1) {
                LLNode temp = head;
                head = head.next;
                if(ln1H == null) {
                    ln1H = temp;
                }
                ln1T = insertTail(temp, ln1T);
            }

            if(head.value == 2) {
                LLNode temp = head;
                head = head.next;
                if(ln2H == null) {
                    ln2H = temp;
                }
                ln2T = insertTail(temp, ln2T);
            }
        }

        System.out.println("Os list");
        CreateLinkList.printList(ln0H);

        System.out.println("1s list");
        CreateLinkList.printList(ln1H);

        System.out.println("2s list");
        CreateLinkList.printList(ln2H);
    }

    private static LLNode insertTail(LLNode nw, LLNode tail) {
        if(tail == null) {
            tail = nw;
        } else {
            tail.next = nw;
            tail = nw;
        }

        nw.next = null;
        return tail;
    }
}
