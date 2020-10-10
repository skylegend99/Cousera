
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {

    private List<LineSegment> lineSegments = new LinkedList<LineSegment>();


    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        int numOfPoints = points.length;
        Point[] clone = new Point[numOfPoints];

        // This part check and return IllgegalArgumentException if the points contains null value or repeated value.
        // Also copy the points to another Point[] object.
        for (int i = 0; i<numOfPoints; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = i+1; j<numOfPoints; j++) {
                // check if any repeated value in here
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
            clone[i] = points[i];
        }

        List<Point> pointList = new LinkedList<Point>();
        for (int i = 0; i < numOfPoints; i++) {
            Arrays.sort(clone); // Prevent the initial point is in the middle of the line segment
            // Sorts the specified array of objects according to the order induced by the specified comparator.
            Arrays.sort(clone, clone[i].slopeOrder());

            // Add the line segment that has the same slope to each other.
            // During each time of the outer loop, clone[i] was chosen as origin by its slope to the other points.
            for (int j = 0; j < numOfPoints-1; j++) {
                pointList.add(clone[j]); // Add the origin point
                // When the slope of origin to j equals the slope of origin to j+1, we find two line segment that
                // has the same slope. We add the line that has the greater length which is the line from origin to j+1.
                while (j + 1 < numOfPoints && clone[0].slopeTo(clone[j]) == clone[0].slopeTo(clone[j+1])) {

                    pointList.add(clone[++j]);
                }

                if (pointList.size() >= 3 && clone[0].compareTo(pointList.get(0)) < 0) {
                    LineSegment lineSegment = new LineSegment(clone[0], pointList.get(pointList.size() -1));
                    lineSegments.add(lineSegment);
                }
                pointList.clear();
            }

        }


    }    // finds all line segments containing 4 or more points
    public int numberOfSegments() {
        return lineSegments.size();
    }       // the number of line segments
    public LineSegment[] segments() {
        LineSegment[] res = new LineSegment[lineSegments.size()];
        int i = 0;
        for (LineSegment segment : lineSegments) {
            res[i++] = segment;
        }
        return res;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}