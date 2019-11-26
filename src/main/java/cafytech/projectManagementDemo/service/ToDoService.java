package cafytech.projectManagementDemo.service;


import cafytech.projectManagementDemo.dao.ProjectDAO;
import cafytech.projectManagementDemo.dao.ToDoDAO;
import cafytech.projectManagementDemo.dto.ToDoDTO;
import cafytech.projectManagementDemo.exception.ProjectNotFoundException;
import cafytech.projectManagementDemo.exception.ToDoItemNotFoundException;
import cafytech.projectManagementDemo.model.Project;
import cafytech.projectManagementDemo.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    @Autowired
    private ToDoDAO repoTodo;

    @Autowired
    private ProjectDAO repoPro;


    public ToDo createToDoItem (ToDoDTO toDoDTO) {

        ToDo toDo = new ToDo();

        toDo.setTask( toDoDTO.getTask() );
        toDo.setLabel( toDoDTO.getLabel() );
        toDo.setOperator( toDoDTO.getOperator() );
        toDo.setCreateDate( toDoDTO.getCreateDate() ); //may use current date and time

        Project project = repoPro.findByprojectTitle( toDoDTO.getProjectTitle() );
        if (project ==null ) { throw new ProjectNotFoundException();    }
        toDo.setProject( project );

        repoTodo.save( toDo );
        return toDo;
    }


    public ToDo updateToDoItem (ToDoDTO toDoDTO){

        ToDo toDo = repoTodo.getOne( toDoDTO.getToDoID() );
        if (toDo == null) {throw new ToDoItemNotFoundException();   }

        toDo.setTask(toDoDTO.getTask());
        toDo.setLabel( toDoDTO.getLabel() );
        toDo.setOperator( toDoDTO.getOperator() );
        toDo.setCreateDate( toDoDTO.getCreateDate() );
        //we don't need to change anything for project

        return  repoTodo.save( toDo );

    }
}
