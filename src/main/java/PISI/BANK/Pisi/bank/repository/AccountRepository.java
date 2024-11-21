package PISI.BANK.Pisi.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import PISI.BANK.Pisi.bank.model.BankAccount;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByAccountType(String accountType);
}
