// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 8 Homework
// Final Project - OCCCDate

import java.util.Locale;
import java.util.GregorianCalendar;
import java.io.Serializable;

public class OCCCDate implements Serializable, Comparable<OCCCDate> {
  private int dayOfMonth;
  private int monthOfYear;
  private int year;
  private GregorianCalendar gc;  // private helper object
  private boolean dateFormat = FORMAT_US;  // default is FORMAT_US
  private boolean dateStyle = STYLE_NUMBERS;  // default is STYLE_NUMBERS
  private boolean dateDayName = SHOW_DAY_NAME;  // default is SHOW_DAY_NAME
  public static final boolean FORMAT_US = true;
  public static final boolean FORMAT_EURO = false;
  public static final boolean STYLE_NUMBERS = true;
  public static final boolean STYLE_NAMES = false;
  public static final boolean SHOW_DAY_NAME = true;
  public static final boolean HIDE_DAY_NAME = false;

  // Default constructor, uses current date and time
  public OCCCDate() {
    gc = new GregorianCalendar();
    this.dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
    this.monthOfYear = gc.get(GregorianCalendar.MONTH) + 1;
    this.year = gc.get(GregorianCalendar.YEAR);
  }

  public OCCCDate(int day, int month, int year) {
    // Create the private GregorianCalendar object using given day, month, and year
    this.gc = new GregorianCalendar(year, month - 1, day);

    if (dateCheck(day, month, year, gc)) {  // If they are a match, the given information was valid
      this.dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
      this.monthOfYear = gc.get(GregorianCalendar.MONTH) + 1;
      this.year = gc.get(GregorianCalendar.YEAR);
    }
    else {
      throw new InvalidOCCCDateException();  // The given information was not valid, throw an exception
    }
  }

  public OCCCDate(GregorianCalendar gc) {
    this.gc = gc;
    this.dayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
    this.monthOfYear = gc.get(GregorianCalendar.MONTH) + 1;
    this.year = gc.get(GregorianCalendar.YEAR);
  }

  // Copy constructor
  public OCCCDate(OCCCDate d) {
    gc = new GregorianCalendar(d.year, d.monthOfYear - 1, d.dayOfMonth);
    dayOfMonth = d.dayOfMonth;
    monthOfYear = d.monthOfYear;
    year = d.year;
  }

  public int getDayOfMonth() {
    return dayOfMonth;
  }

  // From Java API, day name can be displayed via getDisplayName method
  public String getDayName() {
    return gc.getDisplayName(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.LONG_FORMAT, Locale.getDefault());
  }

  public int getMonthNumber() {
    return monthOfYear;
  }

  // From Java API, month name can be displayed via getDisplayName method
  public String getMonthName() {
    return gc.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG_FORMAT, Locale.getDefault());
  }

  public int getYear() {
    return year;
  }

  public void setDateFormat(boolean df) {
    this.dateFormat = df;
  }

  public void setStyleFormat(boolean sf) {
    this.dateStyle = sf;
  }

  public void setDayName(boolean nf) {
    this.dateDayName = nf;
  }

  public int getDifferenceInYears() {
    return getDifferenceInYears(new OCCCDate());
  }

  public int getDifferenceInYears(OCCCDate d) {
    return this.year - d.year;
  }

  public boolean equals(OCCCDate dob) {
    return (getDayOfMonth() == dob.getDayOfMonth()) && (getMonthNumber() == dob.getMonthNumber()) 
      && (getYear() == dob.getYear());
  }

  // Date-check algorithm in boolean method
  public boolean dateCheck(int day, int month, int year, GregorianCalendar gc) {
    // Extract the day, month, and year from the GregorianCalendar object, and compare to what was given
    return ((day == gc.get(GregorianCalendar.DAY_OF_MONTH)) && (month == gc.get(GregorianCalendar.MONTH) + 1) && (year == gc.get(GregorianCalendar.YEAR)));
  }

  @Override
  public String toString() {
    String showDay = "", mainDate = "";
  
    // Append the day of the week if dateDayName is true
    if (dateDayName) {
      showDay += getDayName() + ", ";
    }
    // US format mm/dd/yyy or monthName dd, yyyy          
    if (dateFormat) {
      if (dateStyle) {
        mainDate += getMonthNumber() + "/" + getDayOfMonth() + "/" + getYear();
      }
      else {
        mainDate += getMonthName() + " " + getDayOfMonth() + ", " + getYear();
      }
    }
    // Euro format dd/mm/yyyy or dd monthName yyyy       
    else {
      if (dateStyle) {
        mainDate += getDayOfMonth() + "/" + getMonthNumber() + "/" + getYear();
      }
      else {
        mainDate += getDayOfMonth() + " " + getMonthName() + " " + getYear();
      }
    }

    return showDay + mainDate;       
  }

  @Override
  public int compareTo(OCCCDate dob) {
    // Compare the years
    int yearComparison = Integer.compare(this.year, dob.year);
    if (yearComparison != 0) {
      return yearComparison;
    }

    // Compare months if years are equal
    int monthComparison = Integer.compare(this.monthOfYear, dob.monthOfYear);
    if (monthComparison != 0) {
      return monthComparison;
    }

    // Compare days if years and months are equal
    return Integer.compare(this.dayOfMonth, dob.dayOfMonth);
  }
}