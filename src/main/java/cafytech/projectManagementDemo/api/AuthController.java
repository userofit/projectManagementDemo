package cafytech.projectManagementDemo.api;


import cafytech.projectManagementDemo.exception.EmailExistsException;
import cafytech.projectManagementDemo.model.User;
import cafytech.projectManagementDemo.registration.UserDto;
import cafytech.projectManagementDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Obtain and Delete token request, in restful api design,which is mapping to login and logout
 */


@RestController
public class AuthController {


    @Autowired
    private UserService userService;

    //need to re-write and add service layer
    @PostMapping(path = "/auth/register", consumes = "application/json")
    public String registerUser(@RequestBody UserDto registerUser) throws EmailExistsException {

        User newUser = userService.registerNewUserAccount(registerUser);
        return newUser.toString();
    }


}