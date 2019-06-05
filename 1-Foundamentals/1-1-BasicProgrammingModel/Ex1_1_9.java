import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Ex1_1_9
 */
public class Ex1_1_9 {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        String s = "";

        StdOut.print(Integer.toBinaryString(N));

        for(int n=N; n>0; n/=2)
            s = (n%2) + s;
        StdOut.print(s);
    }
}