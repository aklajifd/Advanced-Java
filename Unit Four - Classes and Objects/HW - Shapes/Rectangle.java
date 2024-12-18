// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 4 Homework
// Rectangle Class

public class Rectangle extends Shape {
  protected double width;
  protected double height;
 
  public Rectangle(double x, double y, double width, double height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public double getArea() {
    return width * height;
  }
 
  @Override
  public double getPerimeter() {
    return 2 * (width + height);
  }

  @Override
  public void drawShape() {
    System.out.println("Drawing the " + getClass().getName() + " at coordinates " + "(" + x + "," + y + ")");
    System.out.println("The width of the " + getClass().getName() + " is " + width);
    System.out.println("The height of the " + getClass().getName() + " is " + height);

    if (super.getFill()) {
      System.out.println("Now filling the shape...");
    }
    else {
      System.out.println("Now outlining the shape...");
    }
    System.out.println("The shape color is: " + super.getColor()); 
  }
}