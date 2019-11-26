package cafytech.projectManagementDemo.registration;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 *  This class is one of the classes that work for validating Registration Data
 *  DTO -  which stands for Data Transfer Object, we use it here to
 *  reduce the number of calls when working with remote interfaces.
 */


public class UserDto {


    @NotNull
    @NotEmpty
    private String username;

//    @ValidPassword
    @NotNull
    @NotEmpty
    private String password;

    //confirm your password
    private String matchingPassword;

//    @ValidEmail
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be null")
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
