package cafytech.projectManagementDemo.service;


import cafytech.projectManagementDemo.dto.ClientDTO;
import cafytech.projectManagementDemo.model.Client;

public interface ClientService {

    public Client createClient(ClientDTO clientDTO);

    public Client updateClient(ClientDTO clientDTO);

}
