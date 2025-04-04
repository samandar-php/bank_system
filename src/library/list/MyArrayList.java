package library.list;

public class MyArrayList implements MyList {
    private Object[] elements;
    private int size;

    private static final int DEFAULT_CAPACITY = 100;

    public MyArrayList(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity){
        elements = new Object[capacity];
    }

    @Override
    public void add(Object element) {
        if (size >= elements.length){
            Object[] temp = new Object[elements.length * 2];
            System.arraycopy(elements,0,temp,0,elements.length);
            elements = temp;
        }
        elements[size++] = element;
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    public void clear(){
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }
}
