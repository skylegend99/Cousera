// add a new node "not" in the head: Node oldfirst = first;
//                                   first = new Node();
//                                   first.item = "not";
//                                   first.next = oldfirst;
// delete the head node: first = first.next;
// add a new node "not in the last: Node oldlast = last;
//                                  last = new Node();
//                                  last.item = "not";
//                                  oldlast.next = last;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private int N;
    private class Node{
        Node next; // create a new object
        Item item;
    }
    public Iterator<Item> iterator() {
        return new Stack.ListIterator();
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
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") ){
                s.push(item);//push the item into stack if the next string is not "-"
            } else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");//print the last item in the stack while the next item is "-" and the stack is not empty.
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
