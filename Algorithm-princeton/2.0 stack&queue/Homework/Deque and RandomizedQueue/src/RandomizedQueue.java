// Using array to create random queue
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item[] queue;
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < max; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
        temp = null;
    }
    // construct an empty randomized queue
    public RandomizedQueue(int capacity) {
        queue = (Item[]) new Object[capacity];
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {return N == 0;}

    // return the number of items on the randomized queue
    public int size() {return N; }

    // add the item
    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException("Cannot enqueue nothing");
        if (N == queue.length) resize(2*queue.length);
        queue[N++] = item;  //store the value in quene[N] and N = N + 1;
    }

    // remove and return a random item
    public Item dequeue() {
        checkEmpty();
        int randNum = StdRandom.uniform(N);
        Item item = queue[randNum];
        if (randNum != N-1) {
            queue[randNum] = queue[--N];
        }
        queue[N] = null;
        if (N>0 && N == queue.length/4) resize(queue.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkEmpty();
        return queue[StdRandom.uniform(N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new randIterator();
    }
    private class randIterator implements Iterator<Item>{
        private int l;
        private Item[] iterQueue;

        public randIterator() {
            l = N;
            iterQueue = (Item[]) new Object[N];
            for (int i=0;i<N;i++){
                iterQueue[i] = queue[i];
            }

        }
        public boolean hasNext() {return l!=0;}
        public Item next() {
            if (N==0) throw new java.util.NoSuchElementException("iterqueue is empty");
            int randNum = StdRandom.uniform(l);
            Item next = iterQueue[randNum];
            if (randNum != l-1) {
                iterQueue[randNum] = iterQueue[l-1];
            }
            iterQueue[--l] = null;
            return next;
        }
        public void remove(){throw new UnsupportedOperationException("Unable to remove");}
    }

    public void checkEmpty() {if (N==0) throw new java.util.NoSuchElementException("queue is empty");}
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) rq.enqueue(item);
            else if (!rq.isEmpty()) StdOut.println(rq.dequeue());
        }
    }
}