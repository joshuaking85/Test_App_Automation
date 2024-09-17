package DataBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob ;
    private static final SimpleDateFormat POSTGRES_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final SimpleDateFormat DISPLAY_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    public Record(int id, String firstName, String lastName, String gender, String dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }
    public String getDob() {
        return dob != null ? DISPLAY_DATE_FORMAT.format(dob) : "N/A"; // Format for display or return "N/A" if null
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + firstName + " " + lastName + ", Gender: " + gender + ", DOB: " + getDob();
    }
}
