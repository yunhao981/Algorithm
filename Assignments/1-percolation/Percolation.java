/* *****************************************************************************
 *  Name: Yunhao Liu
 *  Date: 2019-01-22
 *  Description: Programming Assignment 1 Percolation
 **************************************************************************** */


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private WeightedQuickUnionUF uf;
    private boolean [] siteStatus;
    private int openSiteNum;

    public Percolation(int n) {
        if (n<=0) throw new IllegalArgumentException("n <= 0");
        this.n = n;
        uf = new WeightedQuickUnionUF((n+2) * (n+2));
        siteStatus = new boolean[(n+2)*(n+2)];
        for (int i = 0; i < siteStatus.length; i++) {
            siteStatus[i] = false;
        }
        openSiteNum = 0;
    }

    public void open(int row, int col) {
        if (row<1 || row>n || col<1 || col>n) throw new IllegalArgumentException("row or col illegal");
        siteStatus[ row * (n+1) + col ] = true;

    }

    public boolean isOpen(int row, int col) {
        if(row<1 || row>n || col<1 || col>n) throw new IllegalArgumentException("row or col illegal");
        return siteStatus[row * (n+1) + col];
    }

    public boolean isFull(int row, int col) {
        if(row<1 || row>n || col<1 || col>n) throw new IllegalArgumentException("row or col illegal");
        return uf.connected(0, row * (n+1) + col);
    }

    public int numberOfOpenSites() {
        int num = 0;
        for (int i = 0; i < siteStatus.length; i++) {
            if (siteStatus[i]) {
                num++;
            }
        }
        this.openSiteNum = num;
        return num;
    }

    public boolean percolates() {
        return uf.connected(0,(n+1) * (n+1));
    }

    public static void main(String[] args) {


    }
}
