package doodlejump.Control;

public final class Vector2D { 

    public final double x;
    public final double y;


    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double magnitude() {
        return (double) Math.sqrt(x * x + y * y);
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(x+v.x, y+v.y);
    }

    public Vector2D add(double x, double y) {
        return new Vector2D(x+this.x, y+this.y);
    }

    public Vector2D multiply(double n) {
        return new Vector2D(x*n, y*n);
    }

    public Vector2D div(double n) {
        return new Vector2D(x/n, y/n);
    }

    public Vector2D normalize() {
        double m = magnitude();
        if (m != 0 && m != 1) {
            return div(m);
        }
        else
            return this;
    }

    public Vector2D limit(double max) {
        if (magnitude() > max) {
            return normalize().multiply(max);
        }
        else
            return this;
    }

    static public Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public double heading2D() {
        return Math.atan2(y, x);
    }

}
