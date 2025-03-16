package LemsBankingApp.BankAppMock.BankAdmin;

import LemsBankingApp.BankAppMock.Customer.Customer;
import LemsBankingApp.BankAppMock.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class BankAdminRestController {

    @Autowired
    private BankAdminService bankAdminService;
    @Autowired
    private CustomerService customerService;

//Rest Controller method to view all customers
    @GetMapping("/allCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getExistingUser();
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customers);
        }
        return ResponseEntity.ok(customers);
    }

    //Rest Controller method to register customer
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody BankAdmin staff) {
        String response = bankAdminService.registerAdmin(staff);
        if (response.equals("User already exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // 409 Conflict
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
    }

    //Rest Controller method to login customer
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestParam String email, @RequestParam String password) {
        try {
            BankAdmin adminStaff = bankAdminService.loginAdmin(email, password);
            return ResponseEntity.ok(adminStaff);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


}



