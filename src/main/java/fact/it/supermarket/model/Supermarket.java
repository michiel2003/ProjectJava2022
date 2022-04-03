// Michiel De Cap
// r0879855


package fact.it.supermarket.model;

import java.util.ArrayList;

public class Supermarket {

    private String name;
    private int numberCustomers = 0;
    private ArrayList<Department> departmentList = new ArrayList<>();

    public Supermarket(String name) {
        this.name = name;
    }

    public int getNumberOfDepartments(){
        return departmentList.size();
    }

    public Department searchDepartmentByName(String name){
        for(Department department:departmentList){
           if(department.getName().equals(name)) {
               return department;
           }
        }
        return null;
    }

    public void addDepartment(Department department){
        departmentList.add(department);
    }

    public void registerCustomer(Customer customer){
        numberCustomers++;
        customer.setCardNumber(numberCustomers);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberCustomers() {
        return numberCustomers;
    }

    public void setNumberCustomers(int numberCustomers) {
        this.numberCustomers = numberCustomers;
    }

    public ArrayList<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList<Department> departmentList) {
        this.departmentList = departmentList;
    }
}
