/*
Written by Leslie Mares on 4-17-19 in response to the
Building Java Programs: A Back to Basics Approach 3rd Ed. prompt:

Write an inheritance hierarchy of three-dimensional shapes. Make a top-level shape interface
that has methods for getting information such as volume and surface area. Then make classes
and subclasses that implement various shapes such as cubes, rectangular prisms, spheres...
Place common behavior in superclasses whenever possible, and use abstract classes as appropriate.
*/
public class Cube extends RectangularPrism{
    //makes new Cube using the given dimension and RectangularPrism's constructor
    public Cube(double side){
        super(side, side, side);
    }

    //gives a short description of the shape
    public void shapeDescription(){
        System.out.println("A cube is a 3D shape made of six square faces.");
    }

    //returns name of shape as well as its dimensions
    public String toString(){
        return "Cube(s = " + getLength() + ")";
    }
}
