// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 4 Homework
// Ellipse Class

public class Ellipse extends Shape {
  protected double radius1;
  protected double radius2;
 
  public Ellipse(double x, double y, double r1, double r2) {
    this.x = x;
    this.y = y;
    this.radius1 = r1;
    this.radius2 = r2;
  }

  @Override
  public double getArea() {
    return Math.PI * radius1 * radius2;
  }
 
  @Override
  public double getPerimeter() {
    return (2 * Math.PI) * Math.sqrt(((radius1 * radius1) + (radius2 * radius2))/2);
  }

  @Override
  public void drawShape() {
    System.out.println("Drawing the " + getClass().getName() + " at coordinates " + "(" + x + "," + y + ")");
    System.out.println("The radii of the " + getClass().getName() + " are " + radius1 + " and " + radius2);

    if (super.getFill()) {
      System.out.println("Now filling the shape...");
    }
    else {
      System.out.println("Now outlining the shape...");
    }
    System.out.println("The shape color is: " + super.getColor()); 
  }
}