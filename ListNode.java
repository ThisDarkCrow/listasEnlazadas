public class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }

    public String toString(){
        String toString="null";
        if(next!=null){
            toString=""+next.val;
        }
        toString="val: "+val + ", " +"next: "+ toString;
        return toString;
    }
}