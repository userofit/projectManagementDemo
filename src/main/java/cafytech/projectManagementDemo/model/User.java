package cafytech.projectManagementDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


/**
 * This class is created for storing user information
 * @author Cafy Yang
 */


@Entity
public class User {


    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;
    private String userName;
    // private String title;
    private String email; //required, need to do verification
    private String password;
    private String token;
    private boolean enabled;
    private boolean tokenExpired;

    private String role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Project> projects;

    //  private String address;
    //  private String mobile;
    //   private int numOfJob;
    //  private String teamName = "3D printing";
    //  private String createdByUserID; // convert int to string first
    //  private Date createdDate;

    //  private String firstName="John";
    //  private String lastName ="Smith";


//    @ManyToMany
//    @JsonIgnore
//    private Collection<Role> role;


//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<JobItem> jobItem;

//    @ManyToOne
//    @JsonIgnore
//    private Client client;


//    public User(final String username,
//                final String password) {
//
//        this.userName = requireNonNull(username);
//        this.password = requireNonNull(password);
//    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
