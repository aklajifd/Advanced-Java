// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 4 Homework
// Shape Class

import java.awt.Color;

public abstract class Shape {
  protected double x;
  protected double y; 
  protected Color c;
  protected boolean fill; 
  public static final boolean SHAPE_DEFAULT_FILL = false;
  public static final Color SHAPE_DEFAULT_COLOR = Color.gray;
  public static final boolean SHAPE_SET_FILL = true;  // use with setFill
  public static final boolean SHAPE_SET_OUTLINE = false;  // use with setFill

  protected Shape() {  // sets Color and fill to default values
    this.c = SHAPE_DEFAULT_COLOR;
    this.fill = SHAPE_DEFAULT_FILL;
  }

  public Color getColor() {
    return c;
  }

  public void setColor(Color c) {
    this.c = c;
  }

  public boolean getFill() {
    return fill;
  }

  public void setFill(boolean f) {
    this.fill = f;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public abstract double getArea();

  public abstract double getPerimeter();

  public abstract void drawShape();
}