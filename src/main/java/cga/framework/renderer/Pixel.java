package cga.framework.renderer;

public class Pixel {

    public int x;
    public int y;

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pixel(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Pixel{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}
