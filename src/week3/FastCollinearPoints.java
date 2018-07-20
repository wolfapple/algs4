import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] copyPoints;
    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        copyPoints = points.clone();
        Arrays.sort(copyPoints);
        Point[] otherPoints = copyPoints.clone();
        for (int i = 0; i < copyPoints.length - 3; i++) {
            Point p = copyPoints[i];
            Arrays.sort(otherPoints, p.slopeOrder());
            if (p.slopeTo(otherPoints[i+1]) == p.slopeTo(otherPoints[i+2])) {
                if (p.slopeTo(otherPoints[i+1]) == p.slopeTo(otherPoints[i+3])) {
                    LineSegment segment = new LineSegment(p, otherPoints[i+3]);
                    segments.add(segment);
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[numberOfSegments()];
        return segments.toArray(result);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In("week3/input100.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
