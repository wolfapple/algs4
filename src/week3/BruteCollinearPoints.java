import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] copyPoints;
    private ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

        for (int i = 0; i < copyPoints.length - 1; i++) {
            if (copyPoints[i].compareTo(copyPoints[i + 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }

        for (int p = 0; p < copyPoints.length - 3; p++) {
            for (int q = p + 1; q < copyPoints.length - 2; q++) {
                double slopePQ = copyPoints[p].slopeTo(copyPoints[q]);
                for (int r = q + 1; r < copyPoints.length - 1; r++) {
                    double slopeQR = copyPoints[q].slopeTo(copyPoints[r]);
                    if (slopePQ != slopeQR) continue;
                    for (int s = r + 1; s < copyPoints.length; s++) {
                        double slopeRS = copyPoints[r].slopeTo(copyPoints[s]);
                        if (slopePQ == slopeRS) {
                            LineSegment segment = new LineSegment(copyPoints[p], copyPoints[s]);
                            segments.add(segment);
                        }
                    }
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
