/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import static java.lang.Math.abs;

public class Board {
    private int[][] tiles;
    private int[][] sample;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        int n = this.tiles.length;
        this.sample = new int[n][n];
        int tmp = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tmp == 9) {
                    tmp = 0;
                }
                this.sample[i][j] = tmp;
                tmp++;
            }
        }
    }

    // string representation of this board
    public String toString() {
        int n = this.tiles.length;
        String ans = "";
        StdOut.println(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n - 1) {
                    ans += this.tiles[i][j] + "\n";
                }
                else {
                    ans += this.tiles[i][j] + " ";
                }
            }
        }
        return ans;
    }

    // board dimension n
    public int dimension() {
        return this.tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int cnt = 0;
        int n = this.dimension();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != this.sample[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int n = this.dimension();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int row = this.tiles[i][j] / 3 + 1;
                int col = this.tiles[i][j] % 3;
                ans += abs(row - i - 1) + abs(col - j - 1);
            }
        }
        return ans;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return this.toString().equals(y.toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {


    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
}
