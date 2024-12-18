// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 5 Homework
// RegisteredPerson Class

public class RegisteredPerson extends Person {
  private String govID;

  public RegisteredPerson(String firstName, String lastName, String govID) {
    super(firstName, lastName);
    this.govID = govID;
  }

  public RegisteredPerson(Person p, String govID) {
    super(p);
    this.govID = govID;
  }
 
  public RegisteredPerson(RegisteredPerson p) {
    super(p);
    govID = p.govID;
  }

  public String getGovernmentID() {
    return govID;
  }

  public boolean equals(RegisteredPerson p) {  // the usual equals method
    return super.equals(p) && govID.equalsIgnoreCase(p.govID);
  }

  @Override
  public boolean equals(Person p) {  // compare only Person fields, ignore government ID
    return super.equals(p);
  }

  @Override
  public String toString() {
    return super.toString() + " [" + govID + "]";  // Adds [govID] to end of string
  }
}