public class DListNode<anyType> extends ListNode<anyType> {
    private DListNode<anyType> prev;

    public DListNode(anyType val, DListNode<anyType> next, DListNode<anyType> prev) {
        super(val, next);
        this.prev = prev;
    }

    public DListNode(anyType val) {
        this(val, null, null);
    }

    public void setPrev(DListNode<anyType> prev) {
        this.prev = prev;
    }

    public DListNode<anyType> getPrev() {
        return prev;
    }

    public void setNext(DListNode<anyType> next) {
        super.setNext(next);
    }

    public DListNode<anyType> getNext() {
        return (DListNode<anyType>)super.getNext();
    }
}
