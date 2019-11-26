package cafytech.projectManagementDemo.service;


import cafytech.projectManagementDemo.dao.UserDAO;
import cafytech.projectManagementDemo.exception.EmailExistsException;
import cafytech.projectManagementDemo.exception.EmailNotNullException;
import cafytech.projectManagementDemo.model.User;
import cafytech.projectManagementDemo.registration.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *  This class is one of the classes that work for validating Registration Data
 */


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDAO repo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    @Override
    public User registerNewUserAccount(UserDto registerUser)
            throws EmailExistsException {

        User user = new User();

        if (emailExists(registerUser.getEmail())) {
            throw new EmailExistsException(
           "There is an account with that email address: " + registerUser.getEmail());
        }

        else if (registerUser.getEmail()==null){
            throw new EmailNotNullException();
        }

        user.setUserName( registerUser.getUsername() );
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        user.setEmail( registerUser.getEmail() );
        user.setRole("ROLE_USER");
        //List<Role> role = new ArrayList<>();  Arrays.asList();
        //not using it right now
//        user.setRole(Arrays.asList());  //Arrays.asList()- convert an array into List<String> type
        return repo.save(user);

    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {

        return bCryptPasswordEncoder.matches(oldPassword, user.getPassword());

    }


    private boolean emailExists(String email) {
        User user = repo.findByemail(email);
        if (user != null) {
            return true;
        }
        return false;
    }


    @Override
    public User findUserByName(String username) {
        return repo.findByuserName( username );
    }

    @Override
    public User findUserByEmail(final String email) {
        return repo.findByemail(email);
    }


    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        repo.save(user);
    }


}
