/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private int trials;
    private double[] results;

    public PercolationStats(int n, int trials) {
        if(n<=0 || trials<=0)throw new java.lang.IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        this.results = new double[trials];
        for(int i=0; i<trials; i++) {
            Percolation p = new Percolation(n);
            double count = 0;
            while (!p.percolates()) {
                count++;
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                while (p.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }
                p.open(row, col);
            }
            results[i] = count / (n * n);
        }
    }
    public double mean() {
        return StdStats.mean(results);
    }
    public double stddev() {
        return StdStats.stddev(results);
    }
    public double confidenceLo(){
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }
    public double confidenceHi(){
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, T);
        StdOut.print(ps.mean());
        StdOut.print(ps.stddev());
        StdOut.print(ps.confidenceLo());
        StdOut.print(ps.confidenceHi());

    }
}
