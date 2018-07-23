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

        for (int i = 0; i < copyPoints.length; i++) {
            Point p = copyPoints[i];
            Point[] otherPoints = copyPoints.clone();
            Arrays.sort(otherPoints, p.slopeOrder());
            for (int j = 1; j < otherPoints.length - 2; j++) {
                if (p.slopeTo(otherPoints[j]) == p.slopeTo(otherPoints[j+1]) && p.slopeTo(otherPoints[j]) == p.slopeTo(otherPoints[j+2])) {
                    LineSegment segment = new LineSegment(p, otherPoints[j+2]);
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
