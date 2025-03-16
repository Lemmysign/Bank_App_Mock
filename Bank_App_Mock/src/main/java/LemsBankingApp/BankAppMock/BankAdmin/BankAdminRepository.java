package LemsBankingApp.BankAppMock.BankAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAdminRepository extends JpaRepository<BankAdmin, Long> {

    Optional<BankAdmin> findById(Long id);
    Optional<BankAdmin> findByEmail(String email);
}
