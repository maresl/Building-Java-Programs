/*
Written by Leslie Mares on 4-17-19 in response to the
Building Java Programs: A Back to Basics Approach 3rd Ed. prompt:

Write an inheritance hierarchy of three-dimensional shapes. Make a top-level shape interface
that has methods for getting information such as volume and surface area. Then make classes
and subclasses that implement various shapes such as cubes, rectangular prisms, spheres...
Place common behavior in superclasses whenever possible, and use abstract classes as appropriate.
*/
public class ShapesMain {
    public static void main(String[] args){
        Shape3D[] shapes = new Shape3D[5];
        shapes[0] = new Cube(5);
        shapes[1] = new RectangularPrism(5, 6, 7);
        shapes[2] = new Cylinder(5, 6);
        shapes[3] = new Sphere(5);
        shapes[4] = new Cone(5, 6);

        printStats(shapes);

    }

    public static void printStats(Shape3D[] shapes){
        for(int i = 0; i < shapes.length; i ++){
            System.out.println(shapes[i]);
            shapes[i].shapeDescription();
            System.out.printf("Surface area = %.2f, volume = %.2f\n",
                    shapes[i].getSurfaceArea(), shapes[i].getVolume());
            System.out.println();
        }
    }
}
