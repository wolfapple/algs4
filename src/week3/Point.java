/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that.y == y) return 0.0;
        if (that.x == x) return Double.POSITIVE_INFINITY;
        if (that.x == x && that.y == y) return Double.NEGATIVE_INFINITY;
        return (double) (that.y - y) / (double) (that.x - x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (that.x == x && that.y == y) return 0;
        if (that.y > y || (that.y == y && that.x > x)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double slope1 = o1.slopeTo(Point.this);
                double slope2 = o2.slopeTo(Point.this);
                if (slope1 < slope2) {
                    return -1;
                } else if (slope1 > slope2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        return comparator;
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(0, 1);
        Point d = new Point(1, 1);
        Point e = new Point(2, 1);

        StdOut.printf("slope between %s and %s: %f\n", a.toString(), e.toString(), a.slopeTo(e));
        StdOut.printf("slope between %s and %s: %f\n", a.toString(), b.toString(), a.slopeTo(b));
        StdOut.printf("slope between %s and %s: %f\n\n", a.toString(), c.toString(), a.slopeTo(c));
        StdOut.printf("%s compareTo %s: %d\n", b.toString(), c.toString(), b.compareTo(c));
        StdOut.printf("%s compareTo %s: %d\n", c.toString(), a.toString(), c.compareTo(a));
        StdOut.printf("%s compareTo %s: %d\n\n", d.toString(), e.toString(), d.compareTo(e));

        Point[] points = {b, c, d, e};
        Arrays.sort(points, a.slopeOrder());
        for (Point p : points) {
            StdOut.println(p);
        }
    }
}