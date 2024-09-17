package Data;

import com.github.javafaker.Faker;
import org.junit.runners.Parameterized;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Person {
    private static final Random RANDOM = new Random();
    private static final Faker FAKER = new Faker();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    public RelationshipTitle relationship;
    public HispanicSelection hispanicSelection;
    public RaceSelection race;
    public String firstName;
    public String lastName;
    public int age;
    public String telephone;
    public Date birthday;
    public OtherStaySelection otherStay;
    public String gender;

    // Default constructor
    public Person() {
        this.relationship = getRandomRelationship();
        this.firstName = FAKER.name().firstName();
        this.lastName = FAKER.name().lastName();
        this.age = RANDOM.nextInt(100);
        this.telephone = FAKER.phoneNumber().cellPhone().replaceAll("\\.", "");;
        this.birthday = FAKER.date().birthday();
        this.hispanicSelection = getRandomHispanicSelection();
        this.race = getRandomRace();
        this.otherStay = getRandomOtherStay();
        this.gender = getRandomGender();
    }

    /* Parameterized constructor
    public Person(RelationshipTitle relationship, HispanicSelection hispanicSelection, RaceSelection race, OtherStaySelection otherStay) {
        this.relationship = relationship;
        this.firstName = FAKER.name().firstName();
        this.lastName = FAKER.name().lastName();
        this.age = RANDOM.nextInt(100);
        this.telephone = FAKER.phoneNumber().phoneNumber();
        this.birthday = FAKER.date().birthday();
        this.hispanicSelection = hispanicSelection;
        this.race = race;
        this.otherStay = otherStay;
    } */

    // Method to get a formatted birthday string
    public String getFormattedBirthday() {
        return DATE_FORMAT.format(birthday);
    }

    // Private method to get a random RelationshipTitle
    private RelationshipTitle getRandomRelationship() {
        RelationshipTitle[] values = RelationshipTitle.values();
        return values[RANDOM.nextInt(values.length)];
    }

    // Getters and Setters
    public RelationshipTitle getRelationship() {
        return relationship;
    }
    /*
    public void setRelationship(RelationshipTitle relationship) {
        this.relationship = relationship;
    }*/

    // Private method to get a random HispanicSelection
    private HispanicSelection getRandomHispanicSelection() {
        HispanicSelection[] values = HispanicSelection.values();
        return values[RANDOM.nextInt(values.length)];
    }

    /* Getter and Setter for hispanicSelection
    public HispanicSelection getHispanicSelection() {
        return hispanicSelection;
    }

    public void setHispanicSelection(HispanicSelection hispanicSelection) {
        this.hispanicSelection = hispanicSelection;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    } */

    // Randomize Race
    private RaceSelection getRandomRace() {
        RaceSelection[] values = RaceSelection.values();
        return values[RANDOM.nextInt(values.length)];
    }

    /* Getter and Setter for Race
    public RaceSelection getRace() {
        return race;
    }

    public void setRace(RaceSelection race) {
        this.race = race;
    }*/

    // Randomize Other Stay
    private OtherStaySelection getRandomOtherStay() {
        OtherStaySelection[] values = OtherStaySelection.values();
        return values[RANDOM.nextInt(values.length)];
    }

    public String getRandomGender() {
        // Generate a random integer: 0 or 1
        int randomIndex = RANDOM.nextInt(2);

        // Return "male" for 0 and "female" for 1
        return (randomIndex == 0) ? "MALE" : "FEMALE";
    }

    /* Getter and Setter for otherStay
    public OtherStaySelection getOtherStay() {
        return otherStay;
    }

    public void setOtherStay(OtherStaySelection otherStay) {
        this.otherStay = otherStay;
    } */

    @Override
    public String toString() {
        return String.format("Person[firstName='%s', lastName='%s', age=%d, relationship='%s', telephone='%s', birthday=%s, hispanicSelection='%s', race='%S', otherStay='%s', gender='%s']",
                firstName, lastName, age, relationship, telephone, birthday, hispanicSelection, race, otherStay, gender);
    }
}
