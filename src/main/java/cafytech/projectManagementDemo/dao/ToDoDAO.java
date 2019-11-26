package cafytech.projectManagementDemo.dao;

import cafytech.projectManagementDemo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoDAO extends JpaRepository<ToDo, Long> {

}
