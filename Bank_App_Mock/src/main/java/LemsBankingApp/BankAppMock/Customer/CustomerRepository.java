package LemsBankingApp.BankAppMock.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
    Optional<Customer> findByAccountNo(String accountNo);
    Optional<Customer> findById(int Id);


}