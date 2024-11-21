package PISI.BANK.Pisi.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public BankAccount createAccount(BankAccount account) {
        return accountRepository.save(account);
    }

    public BankAccount getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
}