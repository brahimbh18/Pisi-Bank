package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.Transaction;
import PISI.BANK.Pisi.bank.repositories.TransactionRepository;

import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> geTransactionsByCin(int num) {
        return transactionRepository.getTransactionsByCin(num);
    }

    public List<Transaction> geTransactionsByRib(int num) {
        return transactionRepository.getTransactionsByAccount(num);
    }
}