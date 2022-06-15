
package homework3;

import java.io.Serializable;

public class ToDoItem implements Serializable {
    
    String category;
    int idNumber;
    String longDescription;
    int nextID;
    String toDoTitle;

    public ToDoItem(String category, String toDoTitle) {
        this.category = category;
        this.toDoTitle = toDoTitle;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getToDoTitle() {
        return toDoTitle;
    }

    public void setToDoTitle(String toDoTitle) {
        this.toDoTitle = toDoTitle;
    }

    
}
    

   