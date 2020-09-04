// Enqueue() : add item in the last
// Dequeue() : remove item from the beginning
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;
    private class Node{
        Node next;
        Item item;
    }
    public Iterator<Item> iterator(){
        return new Queue.ListIterator();
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

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else{
            oldlast.next = last;
        }
        N++;
    }
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }
    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") ){
                q.enqueue(item);//push the item into stack if the next string is not "-"
            } else if (!q.isEmpty()) {
                StdOut.print(q.dequeue() + " ");//print the last item in the stack while the next item is "-" and the stack is not empty.
            }
        }
        StdOut.println("(" + q.size() + " left on stack)");
    }
}
