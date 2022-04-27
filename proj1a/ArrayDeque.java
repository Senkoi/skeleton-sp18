public class ArrayDeque<T> {
    private T[] arr = (T[]) new Object[8];
    private int prev = 0;
    private int tail = 0;
    private double Ratio = 0.25;
    public ArrayDeque() {
        arr = (T[]) new Object[8];
        prev = 0;
        tail = 0;
    }
    public ArrayDeque(ArrayDeque other) {
        prev = other.prev;
        tail = other.tail;
        arr = (T[]) new Object[other.size()];
        System.arraycopy(arr, 0, other, 0, arr.length);
    }
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(tmp, 0, arr, 0, arr.length);
        arr = tmp;
    }
    public void addFirst(T item) {
        if(arr.length == size()) {
            resize(arr.length * 2);
        }
        arr[(--prev + arr.length) % arr.length] = item;
    }
    public void addLast(T item) {
        if(arr.length == size()) {
            resize(arr.length * 2);
        }
        arr[(tail++ + arr.length) % arr.length] = item;
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return Math.abs(prev) + Math.abs(tail);
    }
    public void printDeque() {
        for(int i = prev; i < tail; i++) {
            System.out.println(arr[(i + arr.length) % arr.length]);
        }
    }
    public T removeFirst() {
        if(prev == tail)
            return null;
        T tmp = arr[(prev + arr.length) % arr.length];
        //arr[(prev-- + arr.length) % arr.length] = null;
        prev++;
        if((double)size() / arr.length < Ratio && size() > 16)
            resize(arr.length / 2);
        return tmp;
    }
    public T removeLast() {
        if(prev == tail)
            return null;
        T tmp = arr[(tail + arr.length) % arr.length];
        //arr[(tail-- + arr.length) % arr.length] = null;
        tail--;
        if((double)size() / arr.length < Ratio && size() > 16)
            resize(arr.length / 2);
        return tmp;
    }
    public T get(int index) {
        return arr[tail + index];
    }
}
