// Using linked list to create double opened queue
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int N;
    private class Node<Item>{
        Node<Item> forward;
        Node<Item> backward;
        Item item;
    }
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the deque
    public int size(){
        return N;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("value could not be null");
        Node<Item> oldFirst = first;
        first = new Node();
        first.item = item;
        if (!isEmpty()) {
            first.forward = oldFirst;
            oldFirst.backward = first;
            first.backward = null;
        } else {
            first.backward = null;
            first.forward = null;
            last = first;
        }
        N++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("value could not be null");
        Node<Item> oldLast = last;
        last = new Node();
        last.item = item;
        last.forward = null;
        if (!isEmpty()) {
            oldLast.forward = last;
            last.backward = oldLast;
        }else{
            last.backward = null;
            first = last;
        }
        N++;
    }
    // remove and return the item from the front
    public Item removeFirst(){
        if (N == 0) throw new NoSuchElementException("the deque is empty");
        Item item = first.item;
        first = first.forward;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (N == 0) throw new NoSuchElementException("the deque is empty");
        Item item = last.item;
        last = last.backward;
        last.forward = null;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new dequeIterator();
    }
    private class dequeIterator implements Iterator<Item> {
        private Node<Item> current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.forward;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> queue = new Deque<String>();
        queue.addFirst("a");
        queue.addFirst("b");
        queue.addLast("c");
        queue.addFirst("d");
        queue.addLast("e");
        Iterator<String> iter = queue.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }

}



















