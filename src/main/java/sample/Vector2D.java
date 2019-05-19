package sample;

/**
 * EXPERIMENTAL CLASS
 * Preparing to open world update
 * This class given by Roland named user
 * https://stackoverflow.com/questions/31856158/move-objects-on-screen-in-javafx
 */
public class Vector2D {
    public double x;
    public double y;

    /**
     * Creating a 2D Vector
     * @param x X value
     * @param y Y value
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set values of a 2DVector
     * @param x X
     * @param y Y
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the magnitude of a 2DVector
     * @return a double value
     */
    public double magnitude() {
        return (double) Math.sqrt(x * x + y * y);
    }

    /**
     * Add the parameter 2DVector to an another
     * @param v 2DVector
     */
    public void add(Vector2D v) {
        x += v.x;
        y += v.y;
    }

    /**
     * Add the parameter X and Y values to a 2DVector
     * @param x X
     * @param y Y
     */
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void multiply(double n) {
        x *= n;
        y *= n;
    }

    public void div(double n) {
        x /= n;
        y /= n;
    }

    public void normalize() {
        double m = magnitude();
        if (m != 0 && m != 1) {
            div(m);
        }
    }

    public void limit(double max) {
        if (magnitude() > max) {
            normalize();
            multiply(max);
        }
    }

    static public Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public double heading2D() {
        return Math.atan2(y, x);
    }

}
