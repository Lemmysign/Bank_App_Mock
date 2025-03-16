package LemsBankingApp.BankAppMock.Customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String accountNo;

    private double balance;

    // No-args constructor (required by JPA)
    public Customer() {}

    // All-args constructor
    public Customer(Long id, String name, String email, String phone, String password, String accountNo, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.accountNo = accountNo;
        this.balance = balance;
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Builder Pattern
    public static class CustomerBuilder {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private String accountNo;
        private double balance;

        public CustomerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public CustomerBuilder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public Customer build() {
            return new Customer(id, name, email, phone, password, accountNo, balance);
        }
    }

    // Static method to create a builder
    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }
}