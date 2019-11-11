package LinkedList;

public class CreateLinkList {

    public static LLNode createSingly() {
        LLNode ll1 = new LLNode(1);
        LLNode ll2 = new LLNode(2);
        LLNode ll3 = new LLNode(3);
        LLNode ll4 = new LLNode(4);
        LLNode ll5 = new LLNode(5);
        LLNode ll6 = new LLNode(6);
        LLNode ll7 = new LLNode(7);
        LLNode ll8 = new LLNode(8);

        ll1.next = ll2;
        ll2.next = ll3;
        ll3.next = ll4;
        ll4.next = ll5;
        ll5.next = ll6;
        ll6.next = ll7;
        ll7.next = ll8;
        ll8.next = null;

        return ll1;
    }

    public static void printList(LLNode head) {
        while(head != null) {
            System.out.println(head.value + "-");
            head = head.next;
        }
    }

    public static LLNode createSingly012() {
        LLNode ll1 = new LLNode(1);
        LLNode ll2 = new LLNode(1);
        LLNode ll3 = new LLNode(0);
        LLNode ll4 = new LLNode(0);
        LLNode ll5 = new LLNode(2);
        LLNode ll6 = new LLNode(1);
        LLNode ll7 = new LLNode(2);
        LLNode ll8 = new LLNode(2);

        ll1.next = ll2;
        ll2.next = ll3;
        ll3.next = ll4;
        ll4.next = ll5;
        ll5.next = ll6;
        ll6.next = ll7;
        ll7.next = ll8;
        ll8.next = null;

        return ll1;
    }
}
