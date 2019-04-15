/* Written by Leslie Mares on 3-27-19 in response to following prompt
from Building Java Programs: A Back to Basics Approach, 3rd Ed.:

Chapter 8 Programming Project
Write a class called Date that represents a date consisting of a year,
month, and day. A Date object should have the following methods:

public Date(int year, int month, int day)
public void addDays(int days)
public void addWeeks(int weeks)
public int daysTo(Date other)
public int getDay()
public int getMonth()
public int getYear()
public boolean isLeapYear()
public String toString()
*/
public class Date {
    private int day;
    private int month;
    private int year;
    //array of days in each month (length of February depends on whether it is leap year)
    private int[] daysInMonth = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //constructs a new Date object
    public Date(int year, int month, int day){
        //throws an IllegalArgumentException if the month/year/day value passed is zero or less
        //or if the month is greater than 12
        zeroOrLessTest(year);
        zeroOrLessTest(month);
        zeroOrLessTest(day);
        if(month > 12){
            throw new IllegalArgumentException("The month cannot be greater than 12");
        }
        //save year and adjusts days in February if it is a leap year
        this.year = year;
        modifyFebruary();
        //throws IllegalArgumentException if day passed is greater than the days in that month
        if(day > daysInMonth[month - 1]){
            throw new IllegalArgumentException("That day does not exist; the month of "
            + month + " in the year " + year + " only has "+ daysInMonth[month - 1] + " days.");
        }
        this.month = month;
        this.day = day;
    }

    //Moves this date forward in time by the given number of days
    public void addDays(int days){
        zeroOrLessTest(days);
        while(days > 0){
            this.day++;
            if(this.day > daysInMonth[month - 1]){
                this.day = 1;
                if(month == 12){
                    month = 1;
                    year++;
                    modifyFebruary();
                } else {
                    month++;
                }
            }
            days--;
        }
    }

    //Moves this date forward in time by the given number of weeks
    public void addWeeks(int weeks){
        zeroOrLessTest(weeks);
        addDays(weeks * 7);
    }

    //Returns the number of days that this date must be adjusted to
    //make it equal to the given other date; does not accept past Dates
    public int daysTo(Date other){
        //throws an exception if the other Date is in the past
        if(other.getYear() < year || (other.getYear() == year && other.getMonth() < month)
            || (other.getYear() == year && other.getMonth() == month && other.getDay() < day)){
            throw new IllegalArgumentException("This date is in the past");
        }
        //save a snapshot of the current values of Date
        int presentYear = year;
        int presentMonth = month;
        int presesntDay = day;

        int daysUntil = 0;
        while(year != other.getYear() || month != other.getMonth() || day != other.getDay()){
            daysUntil++;
            addDays(1);
        }
        //reset Date variables to what they were before
        year = presentYear;
        month = presentMonth;
        day = presesntDay;
        modifyFebruary();

        return daysUntil;
    }

    //returns day of this Date
    public int getDay(){
        return day;
    }

    //returns month of this Date
    public int getMonth(){
        return month;
    }

    //returns year of this Date
    public int getYear(){
        return year;
    }

    //returns whether this Date's year is a leap year
    public boolean isLeapYear(){
        if(year % 100 == 0 && year >= 100){
            return year % 400 == 0;
        } else {
            return year % 4 == 0;
        }
    }

    //Returns a String representation of this date in year/month/day order
    public String toString(){
        return String.format("%04d/%02d/%02d", year, month, day);
    }

    //throws an IllegalArgumentException if the value passed is zero or less
    private void zeroOrLessTest(int n){
        if(n <= 0){
            throw new IllegalArgumentException("Values less than 1 are not accepted");
        }
    }

    //modifies the length of February depending on whether it is leap year or not
    private void modifyFebruary(){
        if(isLeapYear()){
            daysInMonth[1] = 29;
        } else {
            daysInMonth[1] = 28;
        }
    }
    
    //compares this Date to another; the one that comes chronologically earlier
    //is "less" than dates that occur after it
    public int compareTo(Date other) {
        if (year != other.year) {
            return year - other.year;
        } else if (month != other.month) {
            return month - other.month;
        } else {
            return day - other.day;
        }
    }
}
