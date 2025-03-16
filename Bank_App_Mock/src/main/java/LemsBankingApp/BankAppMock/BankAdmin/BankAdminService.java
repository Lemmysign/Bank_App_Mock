package LemsBankingApp.BankAppMock.BankAdmin;

import LemsBankingApp.BankAppMock.Customer.Customer;

import java.util.List;


public interface BankAdminService {

    BankAdmin loginAdmin(String email, String password);

    String registerAdmin(BankAdmin admin);
    List<Customer> getExistingUser();



}
