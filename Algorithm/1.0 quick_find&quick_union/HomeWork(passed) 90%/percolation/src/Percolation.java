import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private boolean[] grid;
    private final int dimension;
    private final int virtualtop;
    private int count = 0;
    private final WeightedQuickUnionUF topBottom;
    private final WeightedQuickUnionUF top;

    private int xyTo1d(int x, int y) {
        check(x,y);
        return dimension * x - dimension + y - 1;
    }

    private void check(int row, int col) {
        if (row <= 0 || row > dimension || col > dimension || col <= 0) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        topBottom = new WeightedQuickUnionUF(n * n + 2);
        top = new WeightedQuickUnionUF(n * n + 1);
        grid = new boolean[n * n];
        dimension = n;
        virtualtop = n * n;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        if (!isOpen(row, col)) {
            int axis = xyTo1d(row, col);
            grid[axis] = true;
            count++;
            if (row == 1) {
                topBottom.union(virtualtop, axis);
                top.union(virtualtop, axis);
            } else if (row == dimension) {
                topBottom.union(virtualtop+1, axis);
            }

            //check if the down row open

            if (row < dimension && isOpen(row + 1, col)) {
                topBottom.union(axis, xyTo1d(row + 1, col));
                top.union(axis, xyTo1d(row + 1, col));
            }

            //check if the up row open

            if (row > 1 && isOpen(row - 1, col)) {
                topBottom.union(axis, xyTo1d(row - 1, col));
                top.union(axis, xyTo1d(row - 1, col));
            }

            //check if the right row open

            if (col < dimension && isOpen(row, col + 1)) {
                topBottom.union(axis, xyTo1d(row, col + 1));
                top.union(axis, xyTo1d(row, col + 1));
            }

            //check if the left row open

            if (col > 1 && isOpen(row, col - 1)) {
                topBottom.union(axis, xyTo1d(row, col - 1));
                top.union(axis, xyTo1d(row, col - 1));
            }

        }
    }

    // is the site (row, col) open?

    public boolean isOpen(int row, int col) {
        check(row, col);
        return grid[xyTo1d(row, col)];
    }

    // is the site (row, col) full?

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            return top.connected(xyTo1d(row, col), virtualtop);
        }
        return false;
    }

    // returns the number of open sites

    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?

    public boolean percolates() {
        return topBottom.connected(virtualtop,virtualtop+1);
    }
}
