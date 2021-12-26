/* *****************************************************************************
 *  Name: Yunhao Liu
 *  Date: 2019-11-25
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        // finds all line segments containing 4 points
        Point[] points1 = Arrays.copyOf(points, points.length);
        for (Point point : points1) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        Arrays.sort(points1);
        for (int i = 0; i < points1.length; i++) {
            if (i > 0 && points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        //n^4
        int n = points1.length;
        List<LineSegment> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = points1[i];
                        Point q = points1[j];
                        Point r = points1[k];
                        Point s = points1[l];
                        if (Double.compare(p.slopeTo(q), p.slopeTo(r)) == 0
                                && Double.compare(p.slopeTo(r), p.slopeTo(s)) == 0) {
                            LineSegment segment = new LineSegment(p, s);
                            ans.add(segment);
                        }
                    }
                }
            }
        }
        LineSegment[] ls = new LineSegment[ans.size()];
        this.segments = ans.toArray(ls);
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments;
    }

    public static void main(String[] args) {

    }

}
