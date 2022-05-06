// Michiel De Cap
// r0879855
package fact.it.supermarket.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Staff extends Person{

    private LocalDate startDate;
    private boolean student;



    public Staff(String firstName, String surName){
        super(firstName, surName);
        this.startDate = LocalDate.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    @Override
    public String toString(){
        if(student == true) {
            return "Staff member " + super.toString() + " (working student) is employed since " + formatter.format(this.startDate);
        }
        else{
            return "Staff member " + super.toString() + " is employed since " + formatter.format(this.startDate);
        }
    }
}
