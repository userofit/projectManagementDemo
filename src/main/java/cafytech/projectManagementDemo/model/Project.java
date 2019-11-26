package cafytech.projectManagementDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * This class is an Entity/Model for setting Project table.
 * spring-boot version 2.1.5.RELEASE
 * java.version 1.8
 * MySQL connection: jdbc:mysql://localhost:3306/userManagementDB
 * ERD Mapping: One project only belongs to one client, while one client can have zero to many projects. (M:1)
 * @author Cafy Yang
 * Date: June.24.2019
 */


@Entity
@JsonIgnoreProperties(value={"client"})
@Table (name = "Project")
public class Project {


    @Id
    @GeneratedValue
    @Column(name = "id") //project id would be provided by the front-end
    private int projectID;

    @Column(name = "Title",unique = true) //this value is also unique
    private String projectTitle;

    @Column(name = "Description")
    private String projectDesc;

    @Column(name = "visibleToTeam")
    private Boolean visibleToTeam;

    private String label;

    @Column(name = "numberOfFiles")
    private int numOfFile;

    private Date deadline;
//  private int hourSpent;


    @Column(name = "status")
    private String proStatus; //other three status: complete, in process, in queue


    @Nullable
    @ManyToOne
    @JsonIgnore
//    @JoinColumn(name = "clientID")
    private Client client;



//    @OneToMany(mappedBy = "project")
//    @JsonIgnore
//    private List<Quotation> quotation;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<ToDo> toDos;


    @ManyToOne
    @JsonIgnore
    private User user;


    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Boolean getVisibleToTeam() {
        return visibleToTeam;
    }

    public void setVisibleToTeam(Boolean visibleToTeam) {
        this.visibleToTeam = visibleToTeam;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNumOfFile() {
        return numOfFile;
    }

    public void setNumOfFile(int numOfFile) {
        this.numOfFile = numOfFile;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public List<ToDo> getToDos() {
        return toDos;
    }

    public void setToDos(List<ToDo> toDos) {
        this.toDos = toDos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", projectTitle='" + projectTitle + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", visibleToTeam=" + visibleToTeam +
                ", label='" + label + '\'' +
                ", numOfFile=" + numOfFile +
                ", deadline=" + deadline +
                ", proStatus='" + proStatus + '\'' +
                '}';
    }
}