// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 8 Homework
// Final Project - InvalidOCCCDateException

public class InvalidOCCCDateException extends IllegalArgumentException {
  private String info;
 
  public InvalidOCCCDateException() {
    super();
    info = "Date is invalid.";
  }

  public InvalidOCCCDateException(String info) {
    super(info);
    this.info = info;
  }

  public String getMessage() {
    return info; 
  }
   
  public String toString() {
    return "InvalidOCCCDateException: " + info;
  }
}