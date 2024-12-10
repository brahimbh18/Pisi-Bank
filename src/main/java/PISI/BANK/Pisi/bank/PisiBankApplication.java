package PISI.BANK.Pisi.bank;

import PISI.BANK.Pisi.bank.config.DatabaseConfig;
import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.repositories.BankAccountRepository;
import PISI.BANK.Pisi.bank.repositories.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class PisiBankApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PisiBankApplication.class, args);

		// Pass JdbcTemplate to CustomerRepository
		CustomerRepository customerRepository = new CustomerRepository();
		//BankAccountRepository bankRepository = new BankAccountRepository();
		// Now you can use the customerRepository instance
		BankAccount b = new BankAccount();
		System.out.println(customerRepository.getCustomerByEmail("gadour@gmail.com"));
	}

}
