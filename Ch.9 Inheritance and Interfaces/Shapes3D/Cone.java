/*
Written by Leslie Mares on 4-17-19 in response to the
Building Java Programs: A Back to Basics Approach 3rd Ed. prompt:

Write an inheritance hierarchy of three-dimensional shapes. Make a top-level shape interface
that has methods for getting information such as volume and surface area. Then make classes
and subclasses that implement various shapes such as cubes, rectangular prisms, spheres...
Place common behavior in superclasses whenever possible, and use abstract classes as appropriate.
*/
public class Cone extends Cylinder{
    //makes a new cone with given dimensions using cylinder's costructor
    public Cone(double radius, double height) {
        super(radius, height);
    }


    //returns surface area of shape
    public double getSurfaceArea() {
        return Math.PI * getRadius() * (getRadius() + getSlantHeight());
    }

    //returns volume of shape
    public double getVolume() {
        return((double) 1 / 3) * super.getVolume();
    }

    //returns slant height of the cone; distance from apex to edge of the base
    private double getSlantHeight(){
        double inside = (getRadius() * getRadius()) + (getHeight() * getHeight());
        return Math.sqrt(inside);
    }

    //gives a description of the shape
    public void shapeDescription() {
        System.out.println("A right circular cone is a 3D shape with a flat circular base and curved sides\n" +
                "that lead up to a point at the top. You can put ice cream into this shape.");
    }

    //returns name of shape and its dimensions
    public String toString(){
        return "Cone(r = " + getRadius() + ", h = " + getHeight() + ")";
    }
}
