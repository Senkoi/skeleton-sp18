public class LinkedListDeque<T> {

    private class DNode<T> {
        T value;
        DNode prev;
        DNode next;
        /*
        DNode(T value, DNode prev, DNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
        */
        DNode(DNode prev, DNode next) {
            this.prev = prev;
            this.next = next;
        }
    }
    private DNode sentinel;
    private int size = 0;
    public  LinkedListDeque() {
        sentinel = new DNode<T>(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque other) {
        this();
        for(int i = 0; i < other.size(); i++) {
            addLast((T)other.get(i));
        }
    }
    public void addFirst(T item) {
        DNode tmp = new DNode<T>(sentinel, sentinel.next);
        tmp.value = item;
        tmp.next.prev = tmp;
        sentinel.next = tmp;
        size += 1;
    }
    public void addLast(T item) {
        sentinel.prev = new DNode<T>(sentinel.prev, sentinel);
        sentinel.prev.value = item;
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        DNode tmp = sentinel.next;
        while(tmp != sentinel) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }
    public T removeFirst() {
        DNode<T> tmp = sentinel.next;
        if(tmp == sentinel)
            return null;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return tmp.value;
    }
    public T removeLast() {
        DNode<T> tmp = sentinel.prev;
        if(tmp == sentinel)
            return null;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return tmp.value;
    }

    /**
     *
     * @param index index of what you want.
     * @return Expect node.
     */
    public T get (int index) {
        DNode tmp = sentinel.next;
        if(index > size)
            return null;
        while(index-- > 0) {
            tmp = tmp.next;
        }
        return (T)tmp.value;
    }

    private T getNodeRecursive(DNode node, int index) {
        if(index == 0)
            return (T)node.value;
        else
            return (T)getNodeRecursive(node.next, index - 1);
    }
    public T getRecursive(int index) {
        return (T)getNodeRecursive(sentinel.next, index);
    }
}
