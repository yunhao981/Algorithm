import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex1_1_5 {
    public static void main(String[] args) {
        double x = StdIn.readDouble();
        double y = StdIn.readDouble();
        if(x > 0 && x < 1 && y > 0 && y < 1) {
            System.out.println("True");
        }else{
            System.out.println("False");
        }
    }
}