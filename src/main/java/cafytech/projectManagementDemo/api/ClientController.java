package cafytech.projectManagementDemo.api;

import cafytech.projectManagementDemo.dao.ClientDAO;
import cafytech.projectManagementDemo.dto.ClientDTO;
import cafytech.projectManagementDemo.exception.ClientNotFoundException;
import cafytech.projectManagementDemo.model.Client;
import cafytech.projectManagementDemo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This class is a Client/Customer API for fetching data from userManagementDB and generating data in JSON format.
 * spring-boot version 2.1.5.RELEASE
 * java.version 1.8
 * MySQL connection: jdbc:mysql://localhost:3306/userManagementDB
 * @author Cafy Yang
 * Date: June.21.2019
 */


// only one column get user name from user table.
@RestController
public class ClientController {

    @Autowired
    ClientDAO repo;


    @Autowired
    ClientService clientService;


    // for api testing
    @GetMapping(path = "/clients", produces = {"application/json"}) //w
    @ResponseBody
    public List<Client> getClientJson() {

        return repo.findAll();
    }


    @RequestMapping("/client/{clientID}") //w
    @ResponseBody
    public Client getClientByID(@PathVariable("clientID") int clientID) {
             return repo.findById(clientID).orElseThrow(ClientNotFoundException::new);
    }


    @PostMapping(path = "/client/add", consumes = "application/json") //w
    public Client addClient(@RequestBody ClientDTO clientDTO) {

        Client client = clientService.createClient( clientDTO );
//        repo.save(client);
        return client;

    }


    //company name cannot be changed
    @PutMapping(path = "/client/update", consumes = "application/json")
    public Client updateClient(@RequestBody ClientDTO clientDTO) {

        Client client = clientService.updateClient( clientDTO );
//
//        repo.save(client);
        return client;

    }


//    @PutMapping(path = "/client/update/{email}", consumes = "application/json") //w
//    public Client updateClientByEmail(@RequestBody Client client,@PathVariable String email ) {
//
//        if (!email.equals(client.getEmail())){
//            throw new ClientIdMismatchException();
//        }
//        //ClientNotFoundException
//
//            repo.save(client);
//            return client;
//
//    }


    //basically, we don't allow account owner to delete the company that he/she has created.
    @DeleteMapping("/client/delete/{clientID}") //w
    public String deleteClient(@PathVariable int clientID) {
        Client c = repo.findById(clientID).orElseThrow(ClientNotFoundException::new);
            repo.delete(c);
            return "Deleted";
    }


}
