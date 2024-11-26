package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.repositories.BankAccountRepository;
import PISI.BANK.Pisi.bank.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.updateAccount(account);
        transactionRepository.logTransaction(account.getNum(), amount, "deposit", null);
    }

    public void withdraw(BankAccount account, double amount) {
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.updateAccount(account);
        transactionRepository.logTransaction(account.getNum(), -amount, "withdrawal", null);
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        bankAccountRepository.updateAccount(fromAccount);
        bankAccountRepository.updateAccount(toAccount);

        // Log the transfer transaction for both the sender and the receiver
        transactionRepository.logTransaction(fromAccount.getNum(), -amount, "transfer", toAccount.getNum());
        transactionRepository.logTransaction(toAccount.getNum(), amount, "transfer", fromAccount.getNum());
    }
}