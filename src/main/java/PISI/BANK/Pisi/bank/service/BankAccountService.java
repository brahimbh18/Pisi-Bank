package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.repositories.BankAccountRepository;
import PISI.BANK.Pisi.bank.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void createBankAccount(BankAccount newBankAccount) {

        try {
            newBankAccount.setNum(getNewRib());
            bankAccountRepository.createAccount(newBankAccount);
            System.out.println("successfull creation service");
        } catch (Exception e) {
            System.out.println("service error");
        }
    }

    public long getNewRib() {
        return bankAccountRepository.getLastRib() + 1L;
    }

    public List<BankAccount> getBankAccountsByCin(int cin) {
        String sql = "SELECT * FROM BankAccount WHERE customerCin = ?";
        try {
            return bankAccountRepository.getBankAccountsByCin(cin);
        } catch (Exception e) {
            System.err.println("get bankaccounts by cin error :" + e);
            return null;
        }
    }

    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.updateAccount(account);
        transactionRepository.logTransaction(account.getNum(), amount, "deposit", (Long)null);
    }

    public void withdraw(BankAccount account, double amount) {
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.updateAccount(account);
        transactionRepository.logTransaction(account.getNum(), -amount, "withdrawal", (Long)null);
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