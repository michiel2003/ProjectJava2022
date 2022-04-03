// Michiel De Cap
// r0879855
package fact.it.supermarket.model;

import java.util.Locale;

public class Person {

    private String firstName;
    private String surName;

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public Person(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }



    @Override
    public String toString() {
        return surName.toUpperCase() + " " +  firstName;
    }


}
