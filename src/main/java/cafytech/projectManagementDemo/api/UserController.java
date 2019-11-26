package cafytech.projectManagementDemo.api;


import cafytech.projectManagementDemo.dao.UserDAO;
import cafytech.projectManagementDemo.exception.InvalidOldPasswordException;
import cafytech.projectManagementDemo.model.User;
import cafytech.projectManagementDemo.registration.PasswordDto;
import cafytech.projectManagementDemo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


/**
 * This class is a User API for login/logout, changing password,etc from user table.
 * user and generating data in JSON format.
 * java.version 1.8
 */


@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
@RestController
public class UserController {


    @Autowired
    private UserDAO repoUser;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;


    //for testing use
    @GetMapping(path = "/user", produces = "application/json")
    @ResponseBody
    public List<User> getUserJson() {
        return repoUser.findAll();
    }


    //can be used to fetch jobitems using userID or projectID, mapping M:1.
    @GetMapping(path = "/user/id/{userID}", produces = "application/json") // "/user*" so,after'user' need to be different
    @ResponseBody
    public Optional<User> getUserByID(@PathVariable("userID") int userID) {
        return repoUser.findById(userID);
    }


    @GetMapping("/user/name/{userName}") //w, url has to be unique, otherwise, there would be mapping error.
    public User getUserByName(@PathVariable("userName") String userName) {
        return repoUser.findByuserName(userName);

    }


    @GetMapping("/user/email/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return repoUser.findByemail(email);

    }


    @PutMapping(path = "/user/update", consumes = "application/json") //w
    public User updateUser(@RequestBody User user) {

        repoUser.save(user);
        return user;
    }


    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST) //w
    @ResponseBody
    public String changeUserPassword (@RequestBody PasswordDto passwordDto ){

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) { username = ((UserDetails)principal).getUsername();  }
        else { username = principal.toString();  }

        final User user = repoUser.findByuserName(username);

        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword()))
        {
            throw new InvalidOldPasswordException();
        }

        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return "Your password has been updated successfully!";

    }


    @DeleteMapping("/user/delete/{userID}") //w
    public String deleteUser(@PathVariable("userID") int id) {

        User u = repoUser.getOne(id);
        repoUser.delete(u);
        return "Deleted";
    }

    //1015
    @PutMapping("/user/role/{userID}/{role}") //w
    public String changeRoleAuthority(@PathVariable int userID, @PathVariable String role){

        User u = repoUser.getOne(userID);
        u.setRole( role );
        repoUser.save( u );
        return "Role has been changed to: " +role;
    }


}
