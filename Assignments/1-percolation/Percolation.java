/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] open;
    private int openSites;
    private final int size;
    private final WeightedQuickUnionUF wquf;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)throw new java.lang.IllegalArgumentException();
        this.size = n;
        this.openSites = 0;
        this.open = new boolean[n*n];
        this.wquf = new WeightedQuickUnionUF(n*n + 2);
    }

    private int map2dTo1d(int row, int col) {
        return (row - 1) * size + col;
    }

    private boolean indicesValid(int row, int col) {
        return (row > 0 && row <= size && col > 0 && col <= size);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!indicesValid(row, col)) throw new java.lang.IllegalArgumentException();
        int index = map2dTo1d(row, col);
        if (row == 1) {
            wquf.union(0, index);
            open[index-1] = true;
        } else if (row == size) {
            wquf.union(size * size + 1, index);
            open[index-1] = true;
        }

        int left = col-1;
        int right = col+1;
        int up = row-1;
        int down = row+1;

        if (indicesValid(row, left) &&  isOpen(row, left)) {
            wquf.union(index - 1, index);
            open[index-1] = true;
        }

        if (indicesValid(row, right) && isOpen(row, right)) {
            wquf.union(index + 1, index);
            open[index-1] = true;
        }

        if (indicesValid(up, col) && isOpen(up, col)) {
            wquf.union(index - size, index);
            open[index-1] = true;
        }

        if (indicesValid(down, col) && isOpen(down, col)) {
            wquf.union(index + size, index);
            open[index-1] = true;
        }
        openSites++;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return open[(row - 1) * size + col -1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return wquf.connected((row - 1) * size + col, 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
       return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.connected(0, size * size + 1);
    }

    // test client (optional)
    // public static void main(String[] args) {
    //
    // }
}
