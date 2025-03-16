package LemsBankingApp.BankAppMock.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getExistingUser();

    Customer loginCustomer(String email, String password);

    String registerCustomer(Customer customer);

    String addFunds(String accountNo, double amount);

    String withDrawFunds(String accountNo, double amount);

    double displayBalance(String accountNo);

    int generateAccNo();


    boolean checkIfUserExist(String email);

    Customer findCustomerByEmail(String email);

    Optional<Customer> findByAccountNo(String accountNo);

    List<Customer> searchForCustomer(String query);
    String deleteCustomer(String accountNo);


}