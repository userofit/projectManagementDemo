package cafytech.projectManagementDemo.api;


import cafytech.projectManagementDemo.dao.UserDAO;

import cafytech.projectManagementDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * for testing purpose...
 */


@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private UserDAO repoUser;


    @GetMapping
    public String listTasks(){
        return "task list";
    }


    @GetMapping(path = "/user/{userID}", produces = "application/json")
    @ResponseBody
    public Optional<User> getUserByID(@PathVariable("userID") int userID) {
        return repoUser.findById(userID);
    }


    @PostMapping("/add/{taskId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //w
    public String newTasks(@PathVariable("taskId")int id){
        return "create a new task: "+id;
    }


    @PutMapping("/update/{taskId}") //w
    public String updateTasks(@PathVariable("taskId")int id){
        return "update task with id: "+id;
    }


    @DeleteMapping("/delete/{taskId}")
    @PreAuthorize("hasRole('ROLE_USER')") //w
    public String deleteTasks(@PathVariable("taskId")int id){
        return "delete task with id : "+id;
    }

}