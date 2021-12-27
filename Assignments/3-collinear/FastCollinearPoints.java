import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final Point[] points;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

//        this.points = points;
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

        //merge sort ?
        ArrayList<LineSegment> ans = new ArrayList<>();
        for (Point p : this.points) {
            Point[] ps = Arrays.copyOf(this.points, this.points.length);
            if (ps.length < 4) {
                continue;
            }

            Arrays.sort(ps, p.slopeOrder());
            int begin = 1;
            int end = 1;
            double last = p.slopeTo(ps[end]);
            for (int i = 2; i < ps.length; i++) {
                double slope = p.slopeTo(ps[i]);
                if (Double.compare(last, slope) != 0) {
                    if (end - begin >= 2) {
                        if (p.compareTo(ps[begin]) < 0) {
                            ans.add(new LineSegment(p, ps[end]));
                        }
                    }
                    last = slope;
                    begin = i;
                }
                end = i;
            }
            if (end - begin >= 2) {
                if (p.compareTo(ps[begin]) < 0) {
                    ans.add(new LineSegment(p, ps[end]));
                }
            }
        }
        LineSegment[] ls = new LineSegment[ans.size()];
        this.segments = ans.toArray(ls);
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    public int numberOfSegments() {
        return this.segments.length;
    }

}
