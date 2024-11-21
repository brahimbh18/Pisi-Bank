package PISI.BANK.Pisi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import PISI.BANK.Pisi.bank.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
}
