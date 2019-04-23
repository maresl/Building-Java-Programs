/*
Written by Leslie Mares on 4-17-19 in response to the
Building Java Programs: A Back to Basics Approach 3rd Ed. prompt:

Write an inheritance hierarchy of three-dimensional shapes. Make a top-level shape interface
that has methods for getting information such as volume and surface area. Then make classes
and subclasses that implement various shapes such as cubes, rectangular prisms, spheres...
Place common behavior in superclasses whenever possible, and use abstract classes as appropriate.
*/
public class Cylinder implements Shape3D{
    private double radius;
    private double height;

    //constructs new shape with given dimensions
    public Cylinder(double radius, double height){
        checkDimension(radius);
        checkDimension(height);
        this.radius = radius;
        this.height = height;
    }

    //returns surface area of shape
    public double getSurfaceArea(){
        return 2 * Math.PI * radius * height + 2 * Math.PI * radius * radius;
    }

    //returns volume of shape
    public double getVolume(){
        return Math.PI * radius * radius * height;
    }

    //returns the radius of the cylinder
    public double getRadius(){
        return radius;
    }

    //returns the height of the cylinder
    public double getHeight(){
        return height;
    }

    //throws an IllegalArgumentException if given value is zero or less
    private void checkDimension(double d){
        if(d <= 0){
            throw new IllegalArgumentException("dimensions must be greater than zero!");
        }
    }

    //gives a description of the shape
    public void shapeDescription(){
        System.out.println("A right cylinder is a 3D shape with a curved surface and \n" +
                "two identical flat ends that are circular");
    }

    //returns name of shape and its dimensions
    public String toString(){
        return "Cylinder(r = " + radius + ", h = " + height + ")";
    }
}
