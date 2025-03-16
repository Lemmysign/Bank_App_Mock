package LemsBankingApp.BankAppMock.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

     @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getExistingUser() {
        return customerRepository.findAll();
    }

    @Override
    public double displayBalance(String accountNo) {
        Optional<Customer> customer = customerRepository.findByAccountNo(accountNo);
        return customer.map(Customer::getBalance).orElse(0.0);
    }

    @Override
    public int generateAccNo() {
        return 10000000 + new Random().nextInt(90000000);
    }


    @Override
    public String addFunds(String accountNo, double amount) {
        Optional<Customer> customerOptional = customerRepository.findByAccountNo(accountNo);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setBalance(customer.getBalance() + amount);
            customerRepository.save(customer);
            return "Funds added successfully. New balance: " + customer.getBalance();
        }
        return "Account not found.";
    }

    @Override
    public String withDrawFunds(String accountNo, double amount) {
        Optional<Customer> customerOptional = customerRepository.findByAccountNo(accountNo);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getBalance() >= amount) {
                customer.setBalance(customer.getBalance() - amount);
                customerRepository.save(customer);
                return "Withdrawal successful. New balance: " + customer.getBalance();
            } else {
                return "Insufficient funds.";
            }
        }
        return "Account not found.";
    }

    //service method to login customer
@Override
    public Customer loginCustomer(String email, String password) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);

        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        Customer customer = optionalCustomer.get();
        if (!customer.getPassword().equals(password)) {
            throw new RuntimeException("Invalid Credentials");
        }
        return customer; // Return logged-in customer
    }

 //Method to register Customer
    @Override
    public String registerCustomer(Customer customer) {
        if (customer == null || customer.getEmail() == null) {
            throw new IllegalArgumentException("Invalid customer data");
        }

        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
        if (existingCustomer.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        customer.setAccountNo(String.valueOf(generateAccNo()));
        customer.setBalance(0.00);// Ensure this generates a unique account number
        customerRepository.save(customer);
        return "Registration Successful";
    }

@Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }


    @Override
    public boolean checkIfUserExist(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<Customer> findByAccountNo(String accountNo) {
        return customerRepository.findByAccountNo(accountNo);
    }

    @Override
    public List<Customer> searchForCustomer(String query) {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> result = new ArrayList<>();

        for (Customer c : customers) {
            if (c.getName().equalsIgnoreCase(query) || c.getAccountNo().equalsIgnoreCase(query)) {
                result.add(c);
            }
        }

        return result;
    }


    @Override
    public String deleteCustomer(String accountNo) {
        Optional<Customer> customer = customerRepository.findByAccountNo(accountNo);

        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return "Customer deleted successfully";
        }
        return "Customer not found";
    }


    }







