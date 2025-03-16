package LemsBankingApp.BankAppMock.BankAdmin;

import LemsBankingApp.BankAppMock.Customer.Customer;
import LemsBankingApp.BankAppMock.Customer.CustomerRepository;
import LemsBankingApp.BankAppMock.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankAdminServiceImpl implements BankAdminService {

    @Autowired
    private BankAdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public BankAdmin loginAdmin(String email, String password) {
        Optional<BankAdmin> staffLogin = adminRepository.findByEmail(email);
        if (staffLogin.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        BankAdmin staff = staffLogin.get();
        if (!staff.getPassword().equals(password)) {
            throw new RuntimeException("Invalid Credentials");
        }
        return staff;
    }


    @Override
    public String registerAdmin(BankAdmin admin) {

        if (admin == null || admin.getEmail() == null) {
            throw new IllegalArgumentException("Invalid customer data");
        }
        Optional<BankAdmin> staffRegister = adminRepository.findByEmail(admin.getEmail());
        if (staffRegister.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        adminRepository.save(admin);
        return "Registered Successfully";
    }

    @Override
    public List<Customer> getExistingUser() {
        return customerRepository.findAll();
    }


}
