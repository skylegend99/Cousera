import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
public class PercolationStats {
    private final double confidence =1.96;
    private final int trailcount;
    private final double[] result;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("N and T must be <= 0");
        }
        int gridsize = n;
        trailcount = trials;
        result = new double[trailcount];
        for (int trial = 0; trial < trailcount; trial++) {
            Percolation percolation = new Percolation(gridsize);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            int opensites = percolation.numberOfOpenSites();
            result[trial] = (double) opensites / (gridsize * gridsize);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((confidence * stddev()) / Math.sqrt(trailcount));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((confidence * stddev()) / Math.sqrt(trailcount));
    }

    // test client (see below)
    public static void main(String[]args) {
        int gridSize = 10;
        int trialCount = 10;
        if (args.length >= 2) {
            gridSize = Integer.parseInt(args[0]);
            trialCount = Integer.parseInt(args[1]);
        }
        PercolationStats ps = new PercolationStats(gridSize, trialCount);

        double confidences = ps.confidenceLo() + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidences);

    }
}