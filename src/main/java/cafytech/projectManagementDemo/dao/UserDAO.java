package cafytech.projectManagementDemo.dao;



import cafytech.projectManagementDemo.model.Project;
import cafytech.projectManagementDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserDAO extends JpaRepository<User, Integer> {

    User findByuserName(String userName);

    User findBytoken(String token);

    User findByemail(String email);


//    User findByClient_companyName(String companyName);

    @Query(value = "select u.projects from User u where u.userID=?1") //w
    List<Project> getProjectsByuserID(int userID);

    @Query(value = "select u.projects from User u where u.userName=?1") //w
    List<Project> getProjectsByuserName(String userName);


}
