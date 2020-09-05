import java.util.Iterator;
public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private class Node{
        Node next;
        Item item;
    }
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public Iterator<Item> iterator(){
        return new Bag.ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public boolean isEmpty() {
        return first == null;
    }
}
