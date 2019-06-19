/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {
    int[][] sites;
    int size;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        this.size = n;
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                this.sites[i][j] = -1;
            }
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if(this.sites[row][col] == -1)
            this.sites[row][col] = 0;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return this.sites[row][col] == 0;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return this.sites[row][col] == 1;
    }

    // number of open sites
    public int numberOfOpenSites() {
        int openSites = 0;
        for(int i=0; i<=this.size; i++){
          for(int j=0; j<=this.size; j++){
              if(this.sites[i][j] == 0) {
                  openSites++;
              }
          }
        }
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // Weigted UF obj
        // sites[1][i] , connected to one of sites[n][i] ?
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
