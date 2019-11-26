package cafytech.projectManagementDemo.service;


import cafytech.projectManagementDemo.exception.EmailExistsException;
import cafytech.projectManagementDemo.model.User;
import cafytech.projectManagementDemo.registration.UserDto;

public interface UserService {

    User findUserByName(final String username);

    User findUserByEmail(final String email);

    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;


    boolean checkIfValidOldPassword(User user, String password);

    void changeUserPassword(final User user, final String password);


}
