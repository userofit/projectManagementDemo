package cafytech.projectManagementDemo.service;


import cafytech.projectManagementDemo.dao.ClientDAO;
import cafytech.projectManagementDemo.dto.ClientDTO;
import cafytech.projectManagementDemo.exception.ClientNotFoundException;
import cafytech.projectManagementDemo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{


    @Autowired
    private ClientDAO repoC;

    public Client createClient (ClientDTO clientDTO){

        Client client = new Client();
        client.setCompanyName( clientDTO.getCompanyName() );
        client.setCompanyWebsite( clientDTO.getCompanyWebsite() );
        client.setBusinessNumber( clientDTO.getBusinessNumber() );
        client.setCountry( clientDTO.getCountry() );
        client.setCity( clientDTO.getCity() );
        client.setStreet( clientDTO.getStreet() );
        client.setPostalCode( clientDTO.getPostalCode() );
        repoC.save( client );
        return client;
    }

    //company name cannot be changed
    public Client updateClient (ClientDTO clientDTO){

        Client clientToChange = repoC.findBycompanyName( clientDTO.getCompanyName() );

        if ( clientToChange==null){  throw new ClientNotFoundException();     }

        clientToChange.setCompanyWebsite( clientDTO.getCompanyWebsite() );
        clientToChange.setBusinessNumber( clientDTO.getBusinessNumber() );
        clientToChange.setCountry( clientDTO.getCountry() );
        clientToChange.setCity( clientDTO.getCity() );
        clientToChange.setStreet( clientDTO.getStreet() );
        clientToChange.setPostalCode( clientDTO.getPostalCode() );
        return repoC.save( clientToChange );
    }


}
