package PISI.BANK.Pisi.bank;

import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.repositories.ClientRepository;
import PISI.BANK.Pisi.bank.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.naming.Context;
import java.util.List;
import java.util.Vector;

@SpringBootApplication
public class PisiBankApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PisiBankApplication.class, args);

		// Pass JdbcTemplate to ClientRepository
		ClientRepository clientRepository = new ClientRepository();

		// Now you can use the clientRepository instance

		System.out.println(clientRepository.getClientByCin(123));


	}

}
