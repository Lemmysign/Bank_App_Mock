package LemsBankingApp.BankAppMock.Customer;


import LemsBankingApp.BankAppMock.BankAdmin.BankAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BankAdminService bankAdminService;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("customer");
        return "index";
    }

    //Method to display login page
    @GetMapping("/loginPage")
    public String showLoginForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "login"; // Returns login.html
    }

    //Method to display signup page
    @GetMapping("/signupPage")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }

    //Method for login in and displaying login details
    @PostMapping("/loginInput")
    public String loginCustomer(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            Customer loggedInCustomer = customerService.loginCustomer(email, password);

            model.addAttribute("customer", loggedInCustomer);
            return "customerDashboard"; // Load the Thymeleaf dashboard page
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login"; // Reload login page with an error message
        }
    }

    //Method for registering in and displaying registered details
    @PostMapping("/registerInput")
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            customerService.registerCustomer(customer);
            model.addAttribute("message", "Registration Successful!");
            return "customerDashboard"; // Redirect to customer dashboard after successful signup
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup"; // Return to signup page with error message
        }
    }

    @GetMapping("/dashboard/{email}")
    public String showCustomerDashboard(@PathVariable String email, Model model) {
        Customer customer = customerService.findCustomerByEmail(email);
        if (customer == null) {
            return "redirect:/signup"; // Redirect to signup if user not found
        }
        model.addAttribute("customer", customer);
        return "customerDashboard"; // Returns the updated Thymeleaf HTML file
    }

    @PostMapping("/addFunds")
    public String addFunds(@RequestParam String accountNo, @RequestParam double amount, RedirectAttributes redirectAttributes) {
        String message = customerService.addFunds(accountNo, amount);
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/dashboard?accountNo=" + accountNo; // Redirect to avoid resubmission
    }

    @PostMapping("/withDrawFunds")
    public String withDrawFunds(@RequestParam String accountNo, @RequestParam double amount, RedirectAttributes redirectAttributes) {
        String message = customerService.withDrawFunds(accountNo, amount);
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/dashboard?accountNo=" + accountNo; // Redirect to avoid resubmission
    }

    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam(required = false) String accountNo, Model model) {
        if (accountNo != null) {
            Optional<Customer> customerOptional = customerService.findByAccountNo(accountNo);
            customerOptional.ifPresent(customer -> model.addAttribute("customer", customer));
        }
        return "customerDashboard";
    }




}

