/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] open;
    private int openSites;
    private int size;
    private WeightedQuickUnionUF wquf;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n<=0)throw new java.lang.IllegalArgumentException();
        this.size = n;
        this.openSites = 0;
        this.open = new boolean[n*n];
        this.wquf = new WeightedQuickUnionUF(n*n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = (row - 1) * size + col;
        if(row == 1){
            wquf.union(0, index);
            open[index-1] = true;
        }else if(row == size) {
            wquf.union(size * size + 1, index);
            open[index-1] = true;
        }

        int left = col-1;
        int right = col+1;
        int up = row-1;
        int down = row+1;

        if(left > 0 && left <= size && isOpen(row,left)) {
            wquf.union(index - 1, index);
            open[index-1] = true;
        }

        if(right > 0 && right <= size && isOpen(row,right)) {
            wquf.union(index + 1, index);
            open[index-1] = true;
        }

        if(up > 0 && up <= size && isOpen(up, col)) {
            wquf.union(index - size,index);
            open[index-1] = true;
        }

        if(down > 0 && down <= size && isOpen(down, col)) {
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
    public static void main(String[] args) {

    }
}
