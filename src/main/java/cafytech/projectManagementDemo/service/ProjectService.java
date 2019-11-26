package cafytech.projectManagementDemo.service;


import cafytech.projectManagementDemo.dao.ProjectDAO;
import cafytech.projectManagementDemo.dao.UserDAO;
import cafytech.projectManagementDemo.dto.ProjectDTO;
import cafytech.projectManagementDemo.dto.UpdateProjectDTO;
import cafytech.projectManagementDemo.exception.ClientNotFoundException;
import cafytech.projectManagementDemo.exception.ProjectNotFoundException;
import cafytech.projectManagementDemo.model.Client;
import cafytech.projectManagementDemo.model.Project;
import cafytech.projectManagementDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    @Autowired
    private ProjectDAO repoProject;

    @Autowired
    private UserDAO repoUser;

    @Autowired
    private ClientServiceImpl clientService;

    //for named project
    public Project createProjectWithClient(ProjectDTO projectDTO){

        User user = repoUser.findByuserName( projectDTO.getUserName() );
        Project project = new Project();

        project.setUser(user);
        project.setProjectID( projectDTO.getProjectID() );
        project.setProjectTitle( projectDTO.getProjectTitle() );
        project.setProjectDesc( projectDTO.getProjectDesc() );
        project.setDeadline( projectDTO.getDeadline() );
        project.setVisibleToTeam( true );
        project.setLabel( projectDTO.getLabel() );

        Client client = clientService.createClient(projectDTO.getClientDTO() );
        if (client==null) { throw new ClientNotFoundException();  }

        project.setClient( client );
        project.setProStatus( "in preparation" );

        return project;
    }

    //for unnamed project
    public Project createProjectWithoutClient(ProjectDTO projectDTO){

        User user = repoUser.findByuserName( projectDTO.getUserName() );

        Project project = new Project();

        project.setUser(user);
        project.setProjectID( projectDTO.getProjectID() );
        project.setProjectTitle( "unnamed project" );
        project.setProjectDesc( projectDTO.getProjectDesc() );
        project.setDeadline( projectDTO.getDeadline() );
        project.setVisibleToTeam( true );
        project.setLabel( "3d printing" );
        project.setProStatus( "unnamed project" );

        return repoProject.save( project );
    }


    //ui updated 1015
    public Project updateProject(UpdateProjectDTO projectDTO){

        Project project  = repoProject.getOne(projectDTO.getProjectID());
        if ( project==null){ throw new ProjectNotFoundException();  }

        project.setProjectTitle( projectDTO.getProjectTitle() );
        project.setProjectDesc( projectDTO.getProjectDesc() );
        project.setDeadline( projectDTO.getDeadline() );
        project.setLabel( projectDTO.getLabel() );
        project.setProStatus( "project updated" );
        return repoProject.save( project );
    }


    //ui updated, then can be done by "update project" api
    public boolean changeProjectTitle (String oldTitle, String newTitle){

        Project project = repoProject.findByprojectTitle( oldTitle );

        if(project==null) {throw new ProjectNotFoundException(  ); }

        //check if the new name has been used by other project
        Project existingPro = repoProject.findByprojectTitle( newTitle );

        if (existingPro !=null) {
            //request would be rejected, title already exists - false
            return false;
        }

        else {
            //return updated successfully - true
            project.setProjectTitle( newTitle );
            repoProject.save( project );
            return true;
        }

    }


}
