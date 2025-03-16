# Bank_App_Mock
A mock banking app built with Java, Spring MVC, Thymeleaf, and PostgreSQL, featuring CRUD operations for managing bank accounts, transactions, and customer details.

🚀 Features
- Customer & Admin Components
- Traditional MVC Controllers (for web pages)
- REST APIs (for data access)
- Business Logic Layer (handles transactions & rules)
- Repository Layer (manages database operations)
- PostgreSQL (as the database)

🛠️ Tech Stack
- Backend: Java, Spring Boot, Spring MVC.
- Frontend: Thymeleaf, HTML, CSS, Bootstrap JavaScript.
- Database: PostgreSQL.
- Build Tool: Maven

🔧 Setup

1. Clone the repo:

   cd Bank_App_Mock

3. Configure PostgreSQL:
- Create a database (e.g., bank_app_db).
- Update application.properties:
 
properties

spring.datasource.url=jdbc:postgresql://localhost:5432/bank_app_db

spring.datasource.username=your_username

spring.datasource.password=your_password

3. Run the application:
mvn spring-boot:run

4. Open the app in your browser:
http://localhost: (Setup your own port number) or use 1998





CustomerController Endpoints

GET Endpoints

/home → Returns index.html (Home Page).

/loginPage → Returns login.html (Login Page).

/signupPage → Returns signup.html (Signup Page).

/dashboard/{email} → Returns customerDashboard.html for a given email.

/dashboard → Returns customerDashboard.html (optional accountNo for details).

POST Endpoints

/loginInput → Logs in a customer, redirects to dashboard.

/registerInput → Registers a customer, redirects to dashboard.

/addFunds → Adds funds to an account, redirects to dashboard.

/withDrawFunds → Withdraws funds, redirects to dashboard.




BankAdminController Endpoints


GET Endpoints

/adminLoginPage → Returns adminLogin.html (Admin Login Page).

/adminRegister → Returns adminSignUp.html (Admin Signup Page).

/showAllCustomers → Returns customerList.html with all customers.

/search → Searches customers by query, returns customerList.html.

POST Endpoints

/adminLoginInput → Logs in an admin, redirects to dashboard.

/adminRegisterInput → Registers an admin, redirects to dashboard.

/delete → Deletes a customer account, redirects to customer list.
