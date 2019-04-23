/*
Written by Leslie Mares on 4-17-19 in response to the
Building Java Programs: A Back to Basics Approach 3rd Ed. prompt:

Write an inheritance hierarchy of three-dimensional shapes. Make a top-level shape interface
that has methods for getting information such as volume and surface area. Then make classes
and subclasses that implement various shapes such as cubes, rectangular prisms, spheres...
Place common behavior in superclasses whenever possible, and use abstract classes as appropriate.
*/
public class Sphere extends Cylinder{
    //constructs new shape with given dimensions using Cylinder's constructor
    public Sphere(double radius) {
        super(radius, radius * 2);
    }

    //returns volume of this sphere
    public double getVolume() {
        return ((double) 2 / 3) * super.getVolume();
    }

    //returns surface area of this sphere
    public double getSurfaceArea() {
        return ((double) 2 / 3) * super.getSurfaceArea();
    }

    //gives a description of the shape
    public void shapeDescription() {
        System.out.println("A sphere is a 3D shape that is perfectly round like a ball.");
    }

    //returns name of shape and its dimensions
    public String toString() {
        return "Sphere(r = " + getRadius() + ")";
    }
}
