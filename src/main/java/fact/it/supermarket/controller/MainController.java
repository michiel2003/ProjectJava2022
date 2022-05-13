package fact.it.supermarket.controller;


import fact.it.supermarket.model.Customer;
import fact.it.supermarket.model.Department;
import fact.it.supermarket.model.Staff;
import fact.it.supermarket.model.Supermarket;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(allowedHeaders = "*", origins =  "*")
public class MainController {
    private ArrayList<Staff> staffArrayList;
    private ArrayList<Customer> customerArrayList;
    private ArrayList<Supermarket> supermarketArrayList;

//    Write your code here

    @PostConstruct
    public void fillAll(){
        staffArrayList = fillStaffMembers();
        customerArrayList = fillCustomers();
        supermarketArrayList = fillSupermarkets();
    }

    @RequestMapping(value = "/custome/allCustomer")
    public String allCustomer(HttpServletRequest request, Model model){
        model.addAttribute("customerArrayList", customerArrayList);



        return "6_AllCustomers.html";
    }

    @RequestMapping(value = "/staff/allStaff")
    public String allStaff(HttpServletRequest request, Model model){

        model.addAttribute("staffArrayList", staffArrayList);




        return "5_AllStaff.html";
    }


    @RequestMapping(value = "/customer/addCustomer")
    public String addCustomer(HttpServletRequest request, Model model){

        try {

            Customer newCustomer = new Customer(request.getParameter("firstName"), request.getParameter("surname"));
            newCustomer.setYearOfBirth(Integer.parseInt(request.getParameter("yearOfBirth")));

            System.out.println(newCustomer.getYearOfBirth());
            System.out.println(newCustomer.getFirstName());
            System.out.println(newCustomer.getSurname());
            System.out.println("_____________");

            String selectedSuperMarket = request.getParameter("superMarket");

            Supermarket selectedSuperMarketInList = new Supermarket("Impossible");

            for(Supermarket supermarket : supermarketArrayList){
                if(supermarket.getName().equals(selectedSuperMarket)){
                    selectedSuperMarketInList = supermarket;
                }
            }

            selectedSuperMarketInList.registerCustomer(newCustomer);

            System.out.println(selectedSuperMarket);

            model.addAttribute("Customer", newCustomer);

            customerArrayList.add(newCustomer);



        }
        catch (Exception e){
            System.out.println("A non fatal error occurred in addCustomer at MainController");
            System.out.println(e.toString());
            return "1_newCustomer.html";
        }
        return "2_CustomerWelcome.html";

    }

    @RequestMapping(value = "customer/newCustomer")
    public String newCustomer (HttpServletRequest request, Model model){
        model.addAttribute("Supermarkets", supermarketArrayList);
        return "1_newCustomer.html";
    }

    @RequestMapping(value = "/")
    public String index(){
        return "index.html";
    }

    @RequestMapping(value = "/staff/newStaffMember")
    public String newStaffMember(){
        return "3_NewStaffMember.html";
    }

    @RequestMapping(value = "/supermarket/newSupermarket")
    public String newSuperMarket(HttpServletRequest request, Model model){
        return "7_NewSuperMarket.html";
    }

    @RequestMapping(value = "/supermarket/addSuperMarket")
    public String addSuperMarket(HttpServletRequest request, Model model){
        Supermarket newMarket = new Supermarket(request.getParameter("superMarketName"));
        supermarketArrayList.add(newMarket);

        model.addAttribute("SuperMarkets", supermarketArrayList);

        return "8_AllSuperMarkets.html";
    }

    @RequestMapping(value = "/supermarket/allSuperMarkets")
    public String allSuperMarkets(HttpServletRequest request, Model model){
        model.addAttribute("SuperMarkets", supermarketArrayList);
        return "8_AllSuperMarkets.html";
    }

    @RequestMapping(value = "/staff/addStaffMember")
    public String addStaffMember(HttpServletRequest request, Model model){



            Staff newStaff = new Staff(request.getParameter("firstName"), request.getParameter("surname"));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate date = LocalDate.parse(request.getParameter("employedSince"), dtf );

            String isStudent = request.getParameter("student");

            if (isStudent == null){
                newStaff.setStudent(false);
            }else if(isStudent.equals("on")){
                newStaff.setStudent(true);
            }

            newStaff.setStartDate(date);

            model.addAttribute("newStaff", newStaff);

            staffArrayList.add(newStaff);
        return "4_StaffMemberWelcome.html";
    }

    @RequestMapping(value = "department/newDepartment")
    public String newDepartment(HttpServletRequest request, Model model){
        model.addAttribute("SuperMarkets", supermarketArrayList);
        model.addAttribute("staffMembers", staffArrayList);
        return "9_NewDepartment.html";
    }

    @RequestMapping(value = "department/addDepartment")
    public String addDepartment(HttpServletRequest request, Model model){
        Staff staff = null;
        Supermarket supermarket = null;

        try {
            model.addAttribute("SuperMarkets", supermarketArrayList);
            model.addAttribute("staffMembers", staffArrayList);
            String staffToString = request.getParameter("departmentResponsible");
            String superMarketToString = request.getParameter("departmentSuperMarket");
            String photo = request.getParameter("departmentPhoto");
            String isRefrigerated = request.getParameter("departmentRefrigerated");
            String departmentName = request.getParameter("departmentName");


            for (Staff compare : staffArrayList) {
                if (compare.toString().equals(staffToString)) {
                    staff = compare;
                }
            }

            for (Supermarket compare : supermarketArrayList) {
                if (compare.toString().equals(superMarketToString)) {
                    supermarket = compare;
                }
            }

            Department department = new Department();
            department.setResponsible(staff);
            department.setPhoto(photo);

            System.out.println(isRefrigerated);

            if (isRefrigerated == null) {
                department.setRefrigerated(false);
            } else if (isRefrigerated.equals("on")) {
                department.setRefrigerated(true);
            }

            department.setName(departmentName);

            supermarket.addDepartment(department);

            model.addAttribute("superMarket", supermarket);

            return "10_SuperMarketDepartments.html";
        }catch(Exception e){
            if(staff == null){
                model.addAttribute("error", "No staff was selected!!!");
            }else {
                model.addAttribute("error", "No supermarket was selected!!!");
            }

            return "error.html";
        }
    }

    @RequestMapping("/supermarket/viewDepartments")
    public String viewDepartments(HttpServletRequest request, Model model){

        Integer index = Integer.parseInt(request.getParameter("index"));

        model.addAttribute("superMarket", supermarketArrayList.get(index));

        return "10_SuperMarketDepartments.html";
    }

    @RequestMapping("/departments/search")
    public String searchDepartments(HttpServletRequest request, Model model){
        String searchVal = request.getParameter("searchVal");
        Department departmentFound;

        for(Supermarket supermarket: supermarketArrayList){
            Department found = supermarket.searchDepartmentByName(searchVal);
            if (found != null){
                model.addAttribute("department", found);
                return "11_ViewDepartment.html";
            }
        }

        model.addAttribute("error", "There is no department named '" + searchVal + "'");
        return "error.html";
    }




//    






//You wll need these methods in part 3 of the project assignment
    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Customer> fillCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("Dominik", "Mioens");
        customer1.setYearOfBirth(2001);
        Customer customer2 = new Customer("Zion", "Noops");
        customer2.setYearOfBirth(1996);
        Customer customer3 = new Customer("Maria", "Bonetta");
        customer3.setYearOfBirth(1998);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.get(0).addToShoppingList("Butter");
        customers.get(0).addToShoppingList("Bread");
        customers.get(1).addToShoppingList("Apple");
        customers.get(1).addToShoppingList("Banana");
        customers.get(1).addToShoppingList("Grapes");
        customers.get(1).addToShoppingList("Oranges");
        customers.get(2).addToShoppingList("Fish");

        Customer me = new Customer("Michiel", "De Cap");
        me.setYearOfBirth(2003);
        customers.add(me);
        customers.get(3).addToShoppingList("Milk");
        customers.get(3).addToShoppingList("Orange juice");

        return customers;
    }

    private ArrayList<Supermarket> fillSupermarkets() {
        ArrayList<Supermarket> supermarkets = new ArrayList<>();
        Supermarket supermarket1 = new Supermarket("Delhaize");
        Supermarket supermarket2 = new Supermarket("Colruyt");
        Supermarket supermarket3 = new Supermarket("Albert Heyn");
        Department department1 = new Department("Fruit");
        Department department2 = new Department("Bread");
        Department department3 = new Department("Vegetables");
        department1.setPhoto("/img/fruit.jpg");
        department2.setPhoto("/img/bread.jpg");
        department3.setPhoto("/img/vegetables.jpg");
        department1.setResponsible(staffArrayList.get(0));
        department2.setResponsible(staffArrayList.get(1));
        department3.setResponsible(staffArrayList.get(2));
        supermarket1.addDepartment(department1);
        supermarket1.addDepartment(department2);
        supermarket1.addDepartment(department3);
        supermarket2.addDepartment(department1);
        supermarket2.addDepartment(department2);
        supermarket3.addDepartment(department1);
        supermarket3.addDepartment(department3);
        supermarkets.add(supermarket1);
        supermarkets.add(supermarket2);
        supermarkets.add(supermarket3);
        return supermarkets;
    }
}
