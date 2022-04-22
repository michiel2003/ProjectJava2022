// Michiel De Cap
// r0879855
package fact.it.supermarket.model;

public class Person {

    private String firstName;
    private String surname;

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Person(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    @Override
    public String toString() {
        return surname.toUpperCase() + " " +  firstName;
    }


}
