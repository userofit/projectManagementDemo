package cafytech.projectManagementDemo.dao;



import cafytech.projectManagementDemo.model.Client;
import cafytech.projectManagementDemo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProjectDAO extends JpaRepository<Project, Integer> {

    //    Project findByprojectID(String projectID);

    Project findByprojectTitle(String projectTitle);


    @Query(value = "select p.client from Project p where p.projectID=?1") //w - get..By
    Client getClientByprojectID(int projectID);



    //    List<Project> getProjectsByuserID(int userID);
//
//    List<Project> getProjectsByuserName(String userName);

}