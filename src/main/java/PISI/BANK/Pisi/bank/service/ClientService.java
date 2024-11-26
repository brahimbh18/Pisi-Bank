package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.repositories.ClientRepository;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Client client) {
        // Check if a client already exists with the same CIN or email
        if (clientRepository.getClientByCin(client.getCin()) != null) {
            throw new IllegalArgumentException("Client with this CIN already exists.");
        }

        if (clientRepository.getClientByEmail(client.getEmail()) != null) {
            throw new IllegalArgumentException("Client with this email already exists.");
        }

        // If no existing client, create the new client
        clientRepository.createClient(client);
    }

    public Client getClientByCin(int cin) {
        return clientRepository.getClientByCin(cin);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.getClientByEmail(email);

    }

    public void updateClient(Client client) {
        clientRepository.updateClient(client);
    }

    public void deleteClient(int cin) {
        clientRepository.deleteClient(cin);
    }

    public boolean authenticateClient(String email, String password) {
        Client client = clientRepository.getClientByEmail(email);
        if (client != null) {
            return true;

        }
        return false;
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }
}
