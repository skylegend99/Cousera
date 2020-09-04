//read a line and print the string in the behavior of stack when the next string is '-'.

// input: to be or not to - be - - that - - - is
// output: to be not that or be (2 left on stack)
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class FixedCapacityStack<Item> {
    private Item[] a; //store the string
    private int N; //Size of stack

    // create an empty stack with given capacity

    public FixedCapacityStack(int capacity) {
        a = (Item[]) new Object[capacity];
        N = 0;
    }

    public void push(Item item) { if (!isFull()) {a[N++] = item;} }

    public Item pop() { return a[--N]; }

    public boolean isEmpty() { return N == 0; }

    public boolean isFull() { return  a.length == N; }

    public int size() { return N; }

    public static void main(String[] args) {
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(100);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-") ){
                stack.push(item);//push the item into stack if the next string is not "-"
            } else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");//print the last item in the stack while the next item is "-" and the stack is not empty.
            }
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
