package cz.ddmjm.jumper.model;

public class Platform {

    private double x;
    private double y;
    private double length;

    public Platform(double x, double y, double length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return length;
    }

}
