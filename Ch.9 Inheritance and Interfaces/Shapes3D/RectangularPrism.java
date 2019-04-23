/*
Written by Leslie Mares on 4-17-19 in response to the
Building Java Programs: A Back to Basics Approach 3rd Ed. prompt:

Write an inheritance hierarchy of three-dimensional shapes. Make a top-level shape interface
that has methods for getting information such as volume and surface area. Then make classes
and subclasses that implement various shapes such as cubes, rectangular prisms, spheres...
Place common behavior in superclasses whenever possible, and use abstract classes as appropriate.
*/
public class RectangularPrism implements Shape3D{
    private double length;
    private double width;
    private double height;

    //makes a new RectangularPrism shape using the given dimensions
    public RectangularPrism(double length, double width, double height){
        checkDimension(length);
        checkDimension(width);
        checkDimension(height);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    //returns surface area of shape
    public double getSurfaceArea(){
        return 2 * length * width + 2 * width * height + 2 * length * height;
    }

    //returns volume of shape
    public double getVolume(){
        return length * width * height;
    }

    public double getLength(){
        return length;
    }

    //throws an IllegalArgumentException if given value is zero or less
    private void checkDimension(double d){
        if(d <= 0){
            throw new IllegalArgumentException("dimensions must be greater than zero!");
        }
    }

    //gives a short description of the shape
    public void shapeDescription(){
        System.out.println("A rectangular prism is a 3D shape made of six rectangular faces.\n" +
                "It is also called a \"cuboid\".");
    }

    //returns name of shape as well as its dimensions
    public String toString(){
        return "Rectangular Prism(l = " + length + ", w = " + width + ", h = " + height + ")";
    }
}
