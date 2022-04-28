public class ArrayDeque<T> {
    private T[] arr = (T[]) new Object[8];
    private int prev = 0;
    private int size = 0;
    private double Ratio = 0.25;
    public ArrayDeque() {
        arr = (T[]) new Object[8];
        prev = 0;
        size = 0;
    }
    //public ArrayDeque(ArrayDeque other) {
    //    prev = other.prev;
    //    size = other.size;
    //    arr = (T[]) new Object[other.size()];
    //    System.arraycopy(other, 0, arr, 0, arr.length);
    //}
    private int getFirstIndex() {
        return (prev % arr.length + arr.length) % arr.length;
    }
    private int getLastIndex() {
        if(size == 0) return getFirstIndex();
        return (getFirstIndex() + size - 1) % arr.length;
    }
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        if(size > arr.length - 1 - getFirstIndex()) {
            System.arraycopy(arr, getFirstIndex(), tmp, 0, arr.length - getFirstIndex());
            System.arraycopy(arr, 0, tmp, arr.length - getFirstIndex(), size - arr.length + getFirstIndex());
        }
        else{
            System.arraycopy(arr, getFirstIndex(), tmp,0, size);
        }
        prev = 0;
        arr = tmp;
    }
    public void addFirst(T item) {
        if(arr.length - 1 == size()) {
            resize(arr.length * 2);
        }
        prev--;
        arr[getFirstIndex()] = item;
        size++;
    }
    public void addLast(T item) {
        if(arr.length - 1 == size()) {
            resize(arr.length * 2);
        }
        size++;
        arr[getLastIndex()] = item;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for(int i = 0; i < size; i++) {
            int tmp = (getFirstIndex() + i) % arr.length;
            System.out.println(arr[tmp]);
        }
    }
    public T removeFirst() {
        if(size == 0)
            return null;
        T tmp = arr[getFirstIndex()];
        //arr[(prev-- + arr.length) % arr.length] = null;
        prev++;
        if((double)size() / arr.length < Ratio && size() > 16)
            resize(arr.length / 2);
        size--;
        return tmp;
    }
    public T removeLast() {
        if(size == 0)
            return null;
        T tmp = arr[getLastIndex()];
        //arr[(tail-- + arr.length) % arr.length] = null;
        size--;
        if((double)size() / arr.length < Ratio && size() > 16)
            resize(arr.length / 2);
        return tmp;
    }
    public T get(int index) {
        return arr[(getFirstIndex() + index) % arr.length];
    }
}
