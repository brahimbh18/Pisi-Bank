package PISI.BANK.Pisi.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getAccount(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}