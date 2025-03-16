package LemsBankingApp.BankAppMock.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getExistingUser();
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customers);
        }
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        String response = customerService.registerCustomer(customer);
        if (response.equals("User already exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // 409 Conflict
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 Created
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestParam String email, @RequestParam String password) {
        try {
            Customer customer = customerService.loginCustomer(email, password);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/addFunds")
    public ResponseEntity<String> addFunds(@RequestParam String accountNo, @RequestParam double amount) {
        String response = customerService.addFunds(accountNo, amount);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdrawFunds")
    public ResponseEntity<String> withDrawFunds(@RequestParam String accountNo, @RequestParam double amount) {
        String response = customerService.withDrawFunds(accountNo, amount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> displayBalance(@RequestParam String accountNo) {
        double balance = customerService.displayBalance(accountNo);
        return ResponseEntity.ok(balance);
    }

}