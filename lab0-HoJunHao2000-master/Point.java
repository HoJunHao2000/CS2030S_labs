/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2021/22
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author Ho Jun Hao (Group 8A)
 */
class Point {
  // TODO
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")"; 
  }
}
