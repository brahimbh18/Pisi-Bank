package PISI.BANK.Pisi.bank;

import PISI.BANK.Pisi.bank.repositories.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PisiBankApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PisiBankApplication.class, args);

		// Pass JdbcTemplate to CustomerRepository
		CustomerRepository customerRepository = new CustomerRepository();

		// Now you can use the customerRepository instance

		System.out.println(customerRepository.getCustomerByEmail("vhnghb@fddf.gfh"));


	}

}
