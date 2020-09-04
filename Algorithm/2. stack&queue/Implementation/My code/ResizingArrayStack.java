// Why resizing stacks in arrays:
// We used array to store the value of stacks. Once we create the array, we could not change the size of stack.
// Resizing the array extend more memorary space to store value
//read a line and print the string in the behavior of stack when the next string is '-'.
import java.util.Iterator;
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1]; //store the string
    private int N; //Size of stack
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        public boolean hasNext() { return i > 0;}
        public Item next() { return a[--N]; }
        public void remove() {}
    }

    // moving an array which has the size of N to another array that has the length max(N<max)
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // Before push the item into the stacks, check if the size of current array equals to the size of stack,
    // we double the length of array.
    public void push(Item item) {
        if (a.length == N) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return a.length == N;
    }

    public int size() {
        return N;
    }


}
