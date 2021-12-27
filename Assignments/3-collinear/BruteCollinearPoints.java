/* *****************************************************************************
 *  Name: Yunhao Liu
 *  Date: 2019-11-25
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final Point[] points;
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        // finds all line segments containing 4 points
        this.points = Arrays.copyOf(points, points.length);
        for (Point point : this.points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        Arrays.sort(this.points);
        for (int i = 0; i < this.points.length; i++) {
            if (i > 0 && points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        //n^4
        int n = this.points.length;
        List<LineSegment> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = this.points[i];
                        Point q = this.points[j];
                        Point r = this.points[k];
                        Point s = this.points[l];
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
        return Arrays.copyOf(segments, segments.length);
    }

    public static void main(String[] args) {

    }

}
