package LemsBankingApp.BankAppMock;
import LemsBankingApp.BankAppMock.Customer.Customer;
import LemsBankingApp.BankAppMock.Customer.CustomerRepository;
import LemsBankingApp.BankAppMock.Customer.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	private Customer testCustomer;

	@BeforeEach
	void setUp() {
		testCustomer = new Customer();
		testCustomer.setId(1L);
		testCustomer.setName("John Doe");
		testCustomer.setEmail("john@example.com");
		testCustomer.setPassword("password123");
		testCustomer.setAccountNo("12345678");
		testCustomer.setBalance(500.0);
	}

	@Test
	void testGetExistingUser() {
		when(customerRepository.findAll()).thenReturn(List.of(testCustomer));

		List<Customer> customers = customerService.getExistingUser();

		assertEquals(1, customers.size());
		verify(customerRepository, times(1)).findAll();
	}

	@Test
	void testDisplayBalance_ValidAccount() {
		when(customerRepository.findByAccountNo("12345678")).thenReturn(Optional.of(testCustomer));

		double balance = customerService.displayBalance("12345678");

		assertEquals(500.0, balance);
		verify(customerRepository, times(1)).findByAccountNo("12345678");
	}

	@Test
	void testDisplayBalance_InvalidAccount() {
		when(customerRepository.findByAccountNo("00000000")).thenReturn(Optional.empty());

		double balance = customerService.displayBalance("00000000");

		assertEquals(0.0, balance);
		verify(customerRepository, times(1)).findByAccountNo("00000000");
	}

	@Test
	void testAddFunds_Success() {
		when(customerRepository.findByAccountNo("12345678")).thenReturn(Optional.of(testCustomer));

		String response = customerService.addFunds("12345678", 200.0);

		assertEquals("Funds added successfully. New balance: 700.0", response);
		verify(customerRepository, times(1)).save(testCustomer);
	}

	@Test
	void testWithDrawFunds_Success() {
		when(customerRepository.findByAccountNo("12345678")).thenReturn(Optional.of(testCustomer));

		String response = customerService.withDrawFunds("12345678", 100.0);

		assertEquals("Withdrawal successful. New balance: 400.0", response);
		verify(customerRepository, times(1)).save(testCustomer);
	}

	@Test
	void testWithDrawFunds_InsufficientFunds() {
		when(customerRepository.findByAccountNo("12345678")).thenReturn(Optional.of(testCustomer));

		String response = customerService.withDrawFunds("12345678", 600.0);

		assertEquals("Insufficient funds.", response);
		verify(customerRepository, never()).save(any());
	}

	@Test
	void testLoginCustomer_Success() {
		when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(testCustomer));

		Customer loggedInCustomer = customerService.loginCustomer("john@example.com", "password123");

		assertNotNull(loggedInCustomer);
		assertEquals("John Doe", loggedInCustomer.getName());
	}

	@Test
	void testLoginCustomer_InvalidPassword() {
		when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(testCustomer));

		Exception exception = assertThrows(RuntimeException.class,
				() -> customerService.loginCustomer("john@example.com", "wrongPassword"));

		assertEquals("Invalid Credentials", exception.getMessage());
	}

	@Test
	void testLoginCustomer_NotFound() {
		when(customerRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

		Exception exception = assertThrows(RuntimeException.class,
				() -> customerService.loginCustomer("notfound@example.com", "password123"));

		assertEquals("User not found", exception.getMessage());
	}

	@Test
	void testRegisterCustomer_Success() {
		Customer newCustomer = new Customer();
		newCustomer.setEmail("new@example.com");
		when(customerRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());

		String response = customerService.registerCustomer(newCustomer);

		assertEquals("Registration Successful", response);
		verify(customerRepository, times(1)).save(newCustomer);
	}

	@Test
	void testRegisterCustomer_AlreadyExists() {
		when(customerRepository.findByEmail("john@example.com")).thenReturn(Optional.of(testCustomer));

		Exception exception = assertThrows(RuntimeException.class,
				() -> customerService.registerCustomer(testCustomer));

		assertEquals("User already exists", exception.getMessage());
		verify(customerRepository, never()).save(any());
	}

	@Test
	void testDeleteCustomer_Success() {
		when(customerRepository.findByAccountNo("12345678")).thenReturn(Optional.of(testCustomer));

		String response = customerService.deleteCustomer("12345678");

		assertEquals("Customer deleted successfully", response);
		verify(customerRepository, times(1)).delete(testCustomer);
	}

	@Test
	void testDeleteCustomer_NotFound() {
		when(customerRepository.findByAccountNo("00000000")).thenReturn(Optional.empty());

		String response = customerService.deleteCustomer("00000000");

		assertEquals("Customer not found", response);
		verify(customerRepository, never()).delete(any());
	}
}

