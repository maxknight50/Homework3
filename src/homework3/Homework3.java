package homework3;

import java.io.Serializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Maxine Knight, Zachary Taylor Homework 3
 */
public class Homework3 extends Application implements Serializable {

    // Create menu bar
    MenuBar menuBar = new MenuBar();
    Menu catMenu = new Menu("Categories");
    MenuItem item1 = new MenuItem("Edit Categories...");

    // Create labels and buttons
    Label item = new Label("New To-Do Item Title");
    Label category = new Label("Category:");
    TextField titleText = new TextField();
    ComboBox categories;
    //ComboBox<String> categories = new ComboBox<>();
    Button addButton = new Button("Add New Item ->");
    Button deleteButton = new Button("Delete Selected Item ->");
    Button raise = new Button("Raise");
    Button lower = new Button("Lower");
    Button view = new Button("View Item Detail");

    // For Edit Categories
    TextField catTxt = new TextField();
    Button catAdd = new Button("Add");
    Button delete = new Button("Delete Selected");
    ObservableList<String> catObsList = FXCollections.observableArrayList();
    ListView<String> catListView = new ListView<>();

    // Create GridPanes
    GridPane overallPane = new GridPane();
    GridPane listControl = new GridPane();
    GridPane buttonPane = new GridPane();
    GridPane newPane = new GridPane();
    GridPane mnu = new GridPane();

    ListView<ToDoItem> toDoList = new ListView<>();

    ToDoItem storedObject; //STORES OBJECT FOR LATER USE

    @Override
    public void start(Stage primaryStage) {
        // list.setMinWidth (200.0);
        catMenu.getItems().add(item1);
        menuBar.getMenus().add(catMenu);
        categories = new ComboBox(catObsList);

        buttonPane.setPadding(new Insets(10, 10, 10, 10));
        buttonPane.setHgap(5);

        newPane.add(toDoList, 1, 1);

        mnu.add(menuBar, 0, 0);
        buttonPane.add(item, 0, 1);
        buttonPane.add(titleText, 0, 2);
        buttonPane.add(category, 0, 3);
        buttonPane.add(categories, 0, 4);
        buttonPane.add(addButton, 0, 5);
        buttonPane.add(deleteButton, 0, 6);

//        eventsPane.add(list, 2, 1);
        listControl.add(raise, 1, 0);
        listControl.add(lower, 2, 0);
        listControl.add(view, 3, 0);

        overallPane.add(mnu, 0, 0);
        overallPane.add(buttonPane, 0, 1);
        overallPane.add(newPane, 1, 1);
        overallPane.add(listControl, 1, 3);
        primaryStage = new Stage();
        Scene primaryScene = new Scene(overallPane, 600, 550);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("ToDo");
        primaryStage.show();

        item1.setOnAction(e -> {
            EditCategories();
        });

        view.setOnAction(e -> {
            int index = toDoList.getSelectionModel().getSelectedIndex(); // Find the selected item index
            // Find the object that is in that index
            // Pull the category and the item title
            // Pass those into the method so they can be populated into TextField
            // Another observable list??
            storedObject = toDoList.getSelectionModel().getSelectedItem(); // Store the object in that index

            EditToDo(storedObject.getCategory(), storedObject.getToDoTitle()); // Pull the category and item title, pass to new window
        });

        addButton.setOnAction(e -> {
            System.out.println(categories.getValue());
            System.out.println(titleText.getText());
            toDoList.getItems().add(new ToDoItem((String) categories.getValue(), titleText.getText())); // Read selected values, add the new object to the list
        });

        deleteButton.setOnAction(e -> {
            int index = toDoList.getSelectionModel().getSelectedIndex();
            toDoList.getItems().remove(index);
        });
        
        ////////******//////////////
        raise.setOnAction(e -> { 
            int index = toDoList.getSelectionModel().getSelectedIndex();
            //toDoList.getSelectionModel().selectedIndexProperty();
            toDoList.getItems().remove(index);
        });
    }

    /**
     * ********************************************
     * Creates the Edit Categories pop up window
 ********************************************
     */
    public void EditCategories() {

        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();

        primaryPane.add(catListView, 0, 0);
        primaryPane.add(catTxt, 0, 1);
        primaryPane.add(catAdd, 0, 2);
        primaryPane.add(delete, 0, 3);
        primaryPane.setAlignment(Pos.CENTER);

        Scene primaryScene = new Scene(primaryPane, 400, 400);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Edit Categories");
        primaryStage.show();

        catAdd.setOnAction(e -> {
            catObsList.add(catTxt.getText());
            catListView.getItems().add(catTxt.getText());
        });

        delete.setOnAction(e -> {
            int index = catListView.getSelectionModel().getSelectedIndex();
            String itemToRemove = catListView.getSelectionModel().getSelectedItem();
            catListView.getItems().remove(index);
            catObsList.remove(index);
        });
    }

    /**
     * ********************************************
     * Creates the Edit To-Do pop up window
 ********************************************
     */
    public void EditToDo(String category, String title) {

        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();

        TextField cat = new TextField(category);
        TextField item = new TextField(title);
        TextArea notes = new TextArea(storedObject.getLongDescription());
        notes.setMinHeight(200);
        notes.setWrapText(true);
        Button save = new Button("Save Changes -->");

        primaryPane.add(cat, 0, 0);
        primaryPane.add(item, 0, 1);
        primaryPane.add(notes, 0, 2, 2, 2);
        primaryPane.add(save, 1, 1);
        primaryPane.setAlignment(Pos.CENTER);

        Scene primaryScene = new Scene(primaryPane, 400, 390);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Edit To-Do Item");
        primaryStage.show();

        save.setOnAction(e -> {
            //ToDoItem object = new ToDoItem();
            System.out.println(notes.getText());
            storedObject.setLongDescription(notes.getText());

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
