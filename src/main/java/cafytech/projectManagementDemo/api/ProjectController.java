package cafytech.projectManagementDemo.api;


import cafytech.projectManagementDemo.dao.ClientDAO;
import cafytech.projectManagementDemo.dao.ProjectDAO;
import cafytech.projectManagementDemo.dao.UserDAO;
import cafytech.projectManagementDemo.dto.ProjectDTO;
import cafytech.projectManagementDemo.dto.UpdateProjectDTO;
import cafytech.projectManagementDemo.exception.ProjectNotFoundException;
import cafytech.projectManagementDemo.model.Client;
import cafytech.projectManagementDemo.model.Project;
import cafytech.projectManagementDemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This class is a Project API for fetching data from userManagementDB and generating data in JSON format.
 * spring-boot version 2.1.5.RELEASE
 * java.version 1.8
 * MySQL connection: jdbc:mysql://localhost:3306/userManagementDB
 * @author Cafy Yang
 */


@RestController
public class ProjectController {

    @Autowired
    private ProjectDAO repoPro;

    @Autowired
    private ClientDAO repoC;


    @Autowired
    private UserDAO repoUser;

    @Autowired
    private ProjectService projectService;


    @RequestMapping("/testFindAllProject") // w
    @ResponseBody
    public String getProject() {
        return repoPro.findAll().toString();

    }


    @GetMapping(path = "/projects", produces = {"application/json"}) //get method, w
    @ResponseBody
    public List<Project> getProjectJson() {
        return repoPro.findAll();
    }


    @RequestMapping("project/{projectID}") //get one record by primary key ID , w
    @ResponseBody
    public Project getProjectByID(@PathVariable("projectID") int proID) {

        return repoPro.findById( proID ).orElseThrow( ProjectNotFoundException::new );
    }


    @RequestMapping("project/client/{projectID}")  //w
    @ResponseBody
    public Client getClientByProjectID(@PathVariable("projectID") int proID) {

       return repoPro.getClientByprojectID(proID);
    }


    //when we create a project, we need add user and client for this project
    @PostMapping(path = "/project/add", consumes = "application/json") //w
    public Project addProject(@RequestBody ProjectDTO projectDTO) {

        Project project = projectService.createProjectWithClient( projectDTO );
        return repoPro.save( project );
    }

    /**
     * New api - Sep/20
     *
     * @param projectDTO
     * @return
     */
    //when user create a unnamed project, which has no specific client
    @PostMapping(path = "/project/unnamed/add", consumes = "application/json") //w
    public Project addUnnamedProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.createProjectWithoutClient( projectDTO );
    }

    //ui updated - update project
    @PutMapping(path = "/project/update", consumes = "application/json") //w
    public Project updateProject(@RequestBody UpdateProjectDTO projectDTO) {
        return  projectService.updateProject( projectDTO );
    }


    @DeleteMapping("/project/delete/{projectID}") //w
    public String deleteProject(@PathVariable int projectID) {

        Project p = repoPro.findById( projectID ).orElseThrow(ProjectNotFoundException::new);
        repoPro.delete(p);
        return "Deleted";
    }


    //new api request from new ui design
    @PutMapping("/project/new-title") //w
    public String changeProjectTitle(@RequestParam String oldTitle, @RequestParam String newTitle) {

        boolean result = projectService.changeProjectTitle( oldTitle, newTitle );
        return result ? "project title is updated successfully" : "request is rejected, title already exists";
    }


    //new api - 1011
    @GetMapping("/project/userID/{userID}") //w
    public List<Project> getProjectsByUser(@PathVariable int userID) {
        return repoUser.getProjectsByuserID( userID );
    }


    //new api - 1015
    @GetMapping("/project/username/{userName}") //w
    public List<Project> getProjectsByUserName(@PathVariable String userName) {
        return repoUser.getProjectsByuserName( userName );
    }



}