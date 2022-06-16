package homework3;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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

    ArrayList<ToDoItem> list = new ArrayList<>();

    // Create menu bar
    MenuBar menuBar = new MenuBar();
    Menu catMenu = new Menu("Categories");
    MenuItem editCat = new MenuItem("Edit Categories...");

    // Create labels and buttons
    Label item = new Label("New To-Do Item Title");
    Label category = new Label("Category:");
    TextField titleText = new TextField();
    ComboBox categories;

    Button addButton = new Button("Add New Item ->");
    Button deleteButton = new Button("Delete Selected Item ->");
    Button raise = new Button("Raise");
    Button lower = new Button("Lower");
    Button view = new Button("View Item Detail");

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
    FileInputStream readFile2;
    DataInputStream readCatData;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // list.setMinWidth (200.0);
        // Stop method, override

        catMenu.getItems().add(editCat);
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
       

        // Create separate method, readDataIn. Load data to application, call immediately after show
        try {
             ReadAndSet();
            String filePath1 = "todoitemdata.dat";
            String filePath2 = "categoriesdata.dat";
            File toDoFile = new File(filePath1);
            File categoryFile = new File(filePath2);

            if (doesFileExist(toDoFile) == false) {
                System.out.println("File 1 created: " + toDoFile.createNewFile());
            } else {
                System.out.println("File 1 exists");
                FileInputStream readFile = new FileInputStream("todoitemdata.dat"); // Pass in originial file
                DataInputStream readToDoData = new DataInputStream(readFile);
            }
            if (doesFileExist(categoryFile) == false) {
                System.out.println("File 2 created: " + categoryFile.createNewFile());
            } else {
                System.out.println("File 2 exists");
                readFile2 = new FileInputStream("categoriesdata.dat"); // Pass in originial file
                readCatData = new DataInputStream(readFile2);

            }

        } catch (EOFException e) {
            // Since it is an endless loop, this will catch when the file ends and allow the program to continue
        }

        editCat.setOnAction(e -> {
            EditCategories(); // Call edit categories when menu is selected
        });

        view.setOnAction(e -> {
            int index = toDoList.getSelectionModel().getSelectedIndex(); // Find the selected item index
            storedObject = toDoList.getSelectionModel().getSelectedItem(); // Store the object in that index
            EditToDo(storedObject.getCategory(), storedObject.getToDoTitle()); // Pull the category and item title, pass to new window
        });

        // Button to add the user-selected category and titleText into the list
        addButton.setOnAction(e -> {
            toDoList.getItems().add(new ToDoItem((String) categories.getValue(), titleText.getText())); // Read selected values, add the new object to the list
        });

        // Button to delete the selected item from the list
        deleteButton.setOnAction(e -> {
            int index = toDoList.getSelectionModel().getSelectedIndex();
            toDoList.getItems().remove(index);
        });

        // Button to raise priority. Ensures item is not at top of the list
        raise.setOnAction(e -> {
            int index = toDoList.getSelectionModel().getSelectedIndex(); // Finds selected index
            if (index > 0) { // Only if the index is not 0
                storedObject = toDoList.getSelectionModel().getSelectedItem(); // Store the object
                toDoList.getItems().remove(index); // Remove the item at the index
                toDoList.getItems().add(index - 1, storedObject); // Add the object at the previous index
                toDoList.getSelectionModel().clearAndSelect(index - 1);
            }
        });

        // Button to lower priority. Ensures item is not at the bottom of the list
        lower.setOnAction(e -> {
            int index = toDoList.getSelectionModel().getSelectedIndex();
            if (index != toDoList.getItems().size() - 1) { // Only if the index is not at the bottom of the list
                storedObject = toDoList.getSelectionModel().getSelectedItem();
                toDoList.getItems().remove(index);
                toDoList.getItems().add(index + 1, storedObject);
                toDoList.getSelectionModel().clearAndSelect(index + 1);
            }
        });
    }

    @Override
    public void stop() throws FileNotFoundException {
//        try {
//            FileOutputStream catBinOutput = new FileOutputStream("categoriesdata.dat");
//            ObjectOutputStream catObjectOutput = new ObjectOutputStream(catBinOutput);
//
//            System.out.println("Closing");
//            for (int i = 0; i < catObsList.size(); i++) {
//                System.out.println(catObsList.get(i));
//                catObjectOutput.writeObject(catObsList.get(i));
//            }
//            catObjectOutput.close();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    public void ReadAndSet() {
        System.out.println("Here!");
        try {
            while (true) {
                String cat = readCatData.readUTF();
                System.out.println(cat);
                catObsList.add(cat);
                catListView.getItems().add(cat);
            }
        } catch (EOFException e) {

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * ********************************************
     * Creates the Edit Categories pop up window
     * *******************************************
     */
    public void EditCategories() {

        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();

        // Text fields and buttons
        TextField catTxt = new TextField();
        Button catAdd = new Button("Add");
        Button delete = new Button("Delete Selected");

        primaryPane.add(catListView, 0, 0);
        primaryPane.add(catTxt, 0, 1);
        primaryPane.add(catAdd, 0, 2);
        primaryPane.add(delete, 0, 3);
        primaryPane.setAlignment(Pos.CENTER);

        Scene primaryScene = new Scene(primaryPane, 400, 400);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Edit Categories");
        primaryStage.show();

        // Read from file and pull in categories? 
        // Button to add the user-entered category into the ListView
        catAdd.setOnAction(e -> {
            catObsList.add(catTxt.getText());
            catListView.getItems().add(catTxt.getText());
        });

        // Button to delete the selected category from the ListView
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
     * *******************************************
     */
    public void EditToDo(String category, String title) {

        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();

        // TextFields and Button
        TextField cat = new TextField(category);
        TextField item = new TextField(title);
        TextArea notes = new TextArea(storedObject.getLongDescription()); // If anything is stored in description, add it to the notes
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
            storedObject.setLongDescription(notes.getText()); // Set the description to what is in the notes

        });
    }

    public boolean doesFileExist(File file) {
        return file.exists();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
