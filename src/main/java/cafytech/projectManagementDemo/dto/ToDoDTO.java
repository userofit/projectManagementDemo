package cafytech.projectManagementDemo.dto;


import java.util.Date;

public class ToDoDTO {

    //can be null value
    private long toDoID;
    private String task; //could be very long string
    private String label; //there would be more than one labels
    private String operator;
    private Date createDate; //can be generated by back-end

//    private String status;

    private String projectTitle;


    public long getToDoID() {
        return toDoID;
    }

    public void setToDoID(long toDoID) {
        this.toDoID = toDoID;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }


}