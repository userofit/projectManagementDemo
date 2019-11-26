package cafytech.projectManagementDemo.api;


import cafytech.projectManagementDemo.dao.ProjectDAO;
import cafytech.projectManagementDemo.dao.ToDoDAO;
import cafytech.projectManagementDemo.dto.ToDoDTO;
import cafytech.projectManagementDemo.exception.ProjectNotFoundException;
import cafytech.projectManagementDemo.exception.ToDoItemNotFoundException;
import cafytech.projectManagementDemo.model.Project;
import cafytech.projectManagementDemo.model.ToDo;
import cafytech.projectManagementDemo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {


    @Autowired
    private ToDoDAO repoTodo;

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private ProjectDAO repoPro;


    @GetMapping("/todos")
    public List<ToDo> getAllToDoItems (){
        return repoTodo.findAll();     }


    @GetMapping("/todo/project-title/{projectTitle}")
    public List<ToDo> getToDoListByProjectTitle (@PathVariable String projectTitle){
         Project project = repoPro.findByprojectTitle(projectTitle);
         if (project==null) {throw new ProjectNotFoundException();  }
         return project.getToDos();
    }

    @PostMapping(path = "/todo/add", consumes = "application/json")
    public ToDo addToDoItem (@RequestBody ToDoDTO toDoDTO){
        ToDo toDo = toDoService.createToDoItem(toDoDTO);
        return toDo;
    }


    @PutMapping(path = "/todo/update", consumes = "application/json")
    public ToDo updateToDoItem (@RequestBody ToDoDTO toDoDTO){
        ToDo toDo = toDoService.updateToDoItem(toDoDTO);
        return toDo;
    }

    @DeleteMapping("/todo/delete/{id}")
    public String deleteToDoItem (@PathVariable("id") long id){
        ToDo toDo = repoTodo.getOne(id);
        if (toDo==null) { throw new ToDoItemNotFoundException();     }
        repoTodo.delete(toDo);
        return "requested to do item has been deleted.";
    }

}
