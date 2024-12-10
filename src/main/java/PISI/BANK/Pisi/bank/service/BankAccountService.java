package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.model.Customer;
import PISI.BANK.Pisi.bank.repositories.BankAccountRepository;
import PISI.BANK.Pisi.bank.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public BankAccount initiateAccount(Customer customer, BankAccount account) {
        account.setCustomerCin(customer.getCin());
        account.setNum(getNewRib());
        if (account.getType().equals("checkings")){
            account.setOverdraft(getOverdraft(customer));
        }
        account.setCode(getNewCode());
        return account;
    }

    public int getOverdraft(Customer customer) {
        int overdraftAmount = 0;

        // Overdraft limits by job type and income category
        final Map<String, Map<String, Integer>> overdraftTable = Map.of(
                "Liberal", Map.of(
                        "Low", 700,
                        "Middle", 1_500,
                        "High", 2_500,
                        "Very High", 3_000
                ),
                "Employee", Map.of(
                        "Low", 500,
                        "Middle", 1_000,
                        "High", 1_800,
                        "Very High", 2_500
                ),
                "Self-employed", Map.of(
                        "Low", 300,
                        "Middle", 800,
                        "High", 1_200,
                        "Very High", 1_800
                ),
                "Unemployed", Map.of(
                        "Low", 0,
                        "Middle", 0,
                        "High", 0,
                        "Very High", 0
                ),
                "Other", Map.of(
                        "Low", 300,
                        "Middle", 1_000,
                        "High", 1_500,
                        "Very High", 2_000
                )
        );

        // Get the job type and income category
        String jobType = customer.getJobType();
        String incomeCategory = customer.getIncomeCategory();

        // Validate the input and fetch overdraft value
        if (overdraftTable.containsKey(jobType)) {
            Map<String, Integer> incomeToOverdraft = overdraftTable.get(jobType);
            if (incomeToOverdraft.containsKey(incomeCategory)) {
                overdraftAmount = incomeToOverdraft.get(incomeCategory);
            }
        }

        return overdraftAmount;
    }

    public void createBankAccount(Customer customer, BankAccount newBankAccount) {
        newBankAccount = initiateAccount(customer, newBankAccount);
        System.out.println(newBankAccount.toString());
        try {
            bankAccountRepository.createAccount(newBankAccount);
            System.out.println("successfull creation service");
        } catch (Exception e) {
            System.out.println("service error");
        }
    }

    public String getNewCode() {
        Random rand = new Random();
        int new_code = rand.nextInt(10000); // Generates a number between 0 and 999

        // Ensure unique code by checking if it already exists
        while (getBankAccountByCode(String.valueOf(new_code)) != null) {
            new_code = rand.nextInt(10000);
        }

        return String.valueOf(new_code); // Convert int to String before returning
    }


    public BankAccount getBankAccountByCode(String code) {
        String sql = "SELECT * FROM BankAccount WHERE num = ?";
        try {
            return bankAccountRepository.getAccountByCode(code);
        } catch (Exception e) {
            return null;
        }
    }

    public int getNewRib() {
        Random rand = new Random();
        int new_rib = rand.nextInt(10000);
        while (getBankAccountByRib(new_rib) != null) {
            new_rib = rand.nextInt(1000);
        }
        return new_rib;
    }

    public BankAccount getBankAccountByRib(int rib) {
        String sql = "SELECT * FROM BankAccount WHERE num = ?";
        try {
            return bankAccountRepository.getAccountByNum(rib);
        } catch (Exception e) {
            return null;
        }
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