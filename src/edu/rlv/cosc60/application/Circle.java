package edu.rlv.cosc60.application;

/**
 *
 * @author russel
 */
public class Circle extends Shape{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    
    
    @Override
    public double area() {
        return Math.pow(radius, 2)*Math.PI;
    }

    @Override
    public String toString() {
        return String.format("(Radius: %.2f)",radius);
    }
    
    
    
}
