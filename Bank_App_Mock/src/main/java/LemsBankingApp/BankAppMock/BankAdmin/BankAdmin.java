package LemsBankingApp.BankAppMock.BankAdmin;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "bank_admin") // Avoid using "Admin" as table name
public class BankAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false) // Password should not be unique
    private String password;

    // Default Constructor
    public BankAdmin() {
    }

    // Parameterized Constructor
    public BankAdmin(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Builder Pattern
    public static class BankAdminBuilder {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String password;

        public BankAdminBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BankAdminBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BankAdminBuilder email(String email) {
            this.email = email;
            return this;
        }

        public BankAdminBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public BankAdminBuilder password(String password) {
            this.password = password;
            return this;
        }

        public BankAdmin build() {
            return new BankAdmin(id, name, email, phone, password);
        }
    }

    // Static method to create a builder
    public static BankAdminBuilder builder() {
        return new BankAdminBuilder();
    }
}
