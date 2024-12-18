// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 4 Homework
// Triangle Class

public class Triangle extends Shape {
  protected double base;
  protected double height;
  protected double side1;
  protected double side2;

  public Triangle(double x, double y, double base, double height, double side1, double side2) {
    this.x = x;
    this.y = y;
    this.base = base;
    this.height = height;
    this.side1 = side1;
    this.side2 = side2;   
  }

  @Override
  public double getArea() {
    return (base * height) / 2;
  }
 
  @Override
  public double getPerimeter() {
    return base + side1 + side2;
  }

  @Override
  public void drawShape() {
    System.out.println("Drawing the " + getClass().getName() + " at coordinates " + "(" + x + "," + y + ")");
    System.out.println("The base of the " + getClass().getName() + " is " + base);
    System.out.println("The height of the " + getClass().getName() + " is " + height);
    System.out.println("Sides 1 and 2 of the " + getClass().getName() + " are " + side1 + " and " + side2);

    if (super.getFill()) {
      System.out.println("Now filling the shape...");
    }
    else {
      System.out.println("Now outlining the shape...");
    }
    System.out.println("The shape color is: " + super.getColor()); 
  }
} 