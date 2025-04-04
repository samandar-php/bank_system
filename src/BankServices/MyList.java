package BankServices;

public class MyList {

    private Object[] elements;
    private static final int count = 4;
    private int size;

    public MyList() {
        elements = new Object[count];
    }

    public Object get(int index) {
        if (index >= 0 && index < size) {
            return elements[index];
        }
        throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    public void add(Object element) {
        if (size >= elements.length) {
            Object[] newElements = new Object[2 * elements.length];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
        elements[size++] = element;
    }

    public int size() {
        return size;
    }

}
