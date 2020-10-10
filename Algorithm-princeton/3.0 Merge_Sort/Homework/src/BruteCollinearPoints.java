import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;
    public BruteCollinearPoints(Point[] points) {
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
            clone[i] = points[i]; // Copy all the parameter value into the new array.
        }

        Arrays.sort(clone); // Prevent the initial point is in the middle of the line segment
        List<LineSegment> tempSegement = new LinkedList<>();
        for (int p = 0; p < numOfPoints-3; p++ ) {
            for (int q = p+1; q < numOfPoints-2; q++) {
                for (int r = q+1; r < numOfPoints-1; r++) {
                    for (int s = r+1; s < numOfPoints; s++) {
                        if (clone[p].slopeTo(clone[q]) == clone[p].slopeTo(clone[r]) &&
                            clone[p].slopeTo(clone[q]) == clone[p].slopeTo(clone[s])) {
                            tempSegement.add(new LineSegment(clone[q], clone[s]));
                        }
                    }
                }
            }
        }
        lineSegments = tempSegement.toArray(new LineSegment[tempSegement.size()]);
    }    // finds all line segments containing 4 points
    public int numberOfSegments() {
        return lineSegments.length;
    }
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, numberOfSegments());
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