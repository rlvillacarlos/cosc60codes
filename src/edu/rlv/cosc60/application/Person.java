package edu.rlv.cosc60.application;

/**
 *
 * @author russel
 */
public class Person {
    private final int id;
    private final String firstName;
    private  final String lastName;

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("(").append(id).append(",")
                                  .append(firstName).append(" ")
                                  .append(lastName).append(")")
                                  .toString();
    }

    
}
