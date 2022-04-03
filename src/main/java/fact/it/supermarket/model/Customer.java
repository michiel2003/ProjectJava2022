// Michiel De Cap
// r0879855
package fact.it.supermarket.model;

import java.util.ArrayList;

public class Customer extends Person{

    private int cardNumber;
    private int yearOfBirth;
    private ArrayList<String> shoppingList = new ArrayList();


    public Customer(String firstName, String surName){
        super(firstName, surName);
        cardNumber = -1;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public ArrayList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public boolean addToShoppingList(String productName){
        if(shoppingList.size() < 5){
            shoppingList.add(productName);
            return true;
        }
        return false;
    }

    public int getNumberOnShoppingList(){
        return shoppingList.size();
    }

    @Override
    public String toString() {
        return "Customer " + super.toString() + " with card number " + this.cardNumber;
    }


}
