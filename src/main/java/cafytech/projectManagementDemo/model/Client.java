package cafytech.projectManagementDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;


/**
 * This class is an Entity/Model for setting Client/Customer table.
 * spring-boot version 2.1.5.RELEASE
 * java.version 1.8
 * MySQL connection: jdbc:mysql://localhost:3306/userManagementDB
 * ERD Mapping: One Client/Customer can submit zero to many project,
 *              while one project only belongs to one Client. (1:M)
 * @author Cafy Yang
 * Creation Date: June.21.2019
 */


@Entity
@JsonIgnoreProperties(value={"project"})
@Table(name = "company")
public class Client {

    //required, once account owner purchased the software, he/she needs to register with email.
    //username and email address would be displayed on the profile page first time, account owner fills in the info.

    @Id
    @GeneratedValue //manually add prefix id
    @Column(name = "id")
    private int clientID; //it works, regardless what you entered, the id will be generated automatically.
    @Column(name = "companyName")
    private String companyName;

    @Column(name = "businessNumber")
    private String businessNumber;

    @Column(name = "website")
    private String companyWebsite;

    @Column(name = "street")
    private String street;

    private String country;
    private String city;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "contactTitle")
    private String title;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "contactPosition")
    private String position;

    private String email;

    @Column(name = "contactPhone")
    private String phone;

    private String fax;

    @Column(name = "sameAddress")
    private boolean sameAddress = true;

    private String logoPicPath;



//    @Column(name = "cStatus")
//    private String cStatus = "active"; //other status - inactive


    @Nullable
    @OneToMany(mappedBy = "client") //put clientID into project table
    @JsonIgnore
    private List<Project> project;



//    @OneToMany(mappedBy = "client")
//    @JsonIgnore
//    private List<User> users;


    public Client() {
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public boolean isSameAddress() {
        return sameAddress;
    }

    public void setSameAddress(boolean sameAddress) {
        this.sameAddress = sameAddress;
    }

    public String getLogoPicPath() {
        return logoPicPath;
    }

    public void setLogoPicPath(String logoPicPath) {
        this.logoPicPath = logoPicPath;
    }


    @Nullable
    public List<Project> getProject() {
        return project;
    }

    public void setProject(@Nullable List<Project> project) {
        this.project = project;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }


}