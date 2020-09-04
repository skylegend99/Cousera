// Using array to create random queue
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
        queue[randNum] = queue[--N];
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
            iterQueue[randNum] = iterQueue[--l];
            iterQueue[l] = null;
            return next;
        }
        public void remove(){throw new UnsupportedOperationException("Unable to remove");}
    }

    public void checkEmpty() {if (N==0) throw new java.util.NoSuchElementException("queue is empty");}
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>(10);
        rq.enqueue("冉冉");
        rq.enqueue("跳起来");
        rq.enqueue("拍了拍");
        rq.enqueue("茵茵");
        rq.enqueue("屁股");
        rq.enqueue("亲了一口");
        rq.enqueue("傻fufu的");
        rq.enqueue("然后");
        Iterator<String> iter1 = rq.iterator();
        while (iter1.hasNext()) {
            System.out.print(iter1.next() );
        }
    }
}