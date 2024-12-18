// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 8 Homework
// Final Project - OCCCPerson

public class OCCCPerson extends RegisteredPerson {
  private String studentID;

  public OCCCPerson(String firstName, String lastName, OCCCDate dob, String govID, String studentID) {
    super(firstName, lastName, dob, govID);
    this.studentID = studentID;
  }

  public OCCCPerson(RegisteredPerson p, String studentID) {
    super(p);
    this.studentID = studentID;
  }

  public OCCCPerson(OCCCPerson p) {
    super(p);
    studentID = p.studentID;
  }

  public String getStudentID() {
    return studentID;
  }

  public boolean equals(OCCCPerson p) {  // the usual equals method
    return super.equals(p) && studentID.equalsIgnoreCase(p.studentID);
  }

  @Override
  public boolean equals(RegisteredPerson p) {  // compare only RegisteredPerson fields, ignore stud ID
    return super.equals(p);
  }

  @Override
  public boolean equals(Person p) { // compare only name fields and DOB fields
    return super.equals(p);
  }

  @Override
  public String toString() {
    return super.toString() + " [" + studentID + "]";  // Adds [studentID] to end of string
  }
}