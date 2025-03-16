package LemsBankingApp.BankAppMock.BankAdmin;

import LemsBankingApp.BankAppMock.Customer.Customer;
import LemsBankingApp.BankAppMock.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class BankAdminController {

    @Autowired
    private BankAdminService bankAdminService;

    @Autowired
    private CustomerService customerService;


    //Controller Method to display login page
    @GetMapping("/adminLoginPage")
    public String showLoginForm() {
        return "adminLogin"; // Returns login.html
    }

    //Controller Method to display register page
    @GetMapping("/adminRegister")
    public String showSignupPage(Model model) {
        model.addAttribute("bankAdmin", new BankAdmin());
        return "adminSignUp"; // Renders signup.html
    }

    //Controller Method for login in and displaying login details
    @PostMapping("/adminLoginInput")
    public String loginAdmin(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            BankAdmin loggedAdmin = bankAdminService.loginAdmin(email,password);

            model.addAttribute("bankAdmin", loggedAdmin);
            return "adminDashboard"; // Load the Thymeleaf dashboard page
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "adminLogin"; // Reload login page with an error message
        }
    }

    //Controller Method for registering in and displaying registered details
    @PostMapping("/adminRegisterInput")
    public String registerAdmin(@ModelAttribute BankAdmin bankAdmin, Model model) {
        try {
            bankAdminService.registerAdmin(bankAdmin);
            model.addAttribute("message", "Registration Successful!");
            return "adminDashboard"; // Redirect to customer dashboard after successful signup
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "adminSignUp"; // Return to signup page with error message
        }
    }
//Controller method to view all customers

    @GetMapping("showAllCustomers")
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerService.searchForCustomer(""));
        return "customerList"; // Name of your Thymeleaf HTML file
    }

    //Controller method to search for customers
    @GetMapping("/search")
    public String searchCustomers(@RequestParam("query") String query, Model model) {
        List<Customer> customers = customerService.searchForCustomer(query);
        model.addAttribute("customers", customers);
        return "customerList"; // Ensure this is the same Thymeleaf template
    }

    //Controller method to delete for customer
    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("accountNo") String accountNo, Model model) {
        String message = customerService.deleteCustomer(accountNo);
        model.addAttribute("deleteMessage", message);
        model.addAttribute("customers", customerService.searchForCustomer(""));
        return "customerList";
    }

}
