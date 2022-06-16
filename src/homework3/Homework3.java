package homework3;

import java.io.Serializable;
import javafx.application.Application;
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
    ComboBox<String> categories = new ComboBox<>();
    Button addButton = new Button("Add New Item ->");
    Button deleteButton = new Button("Delete Selected Item ->");
    Button raise = new Button("Raise");
    Button lower = new Button("Lower");
    Button view = new Button("View Item Detail");
    
    // For Edit Categories
    TextField catTxt = new TextField();
    Button catAdd = new Button("Add");
    Button delete = new Button("Delete Selected");


    // Create GridPanes
    GridPane overallPane = new GridPane();
    GridPane listControl = new GridPane();
    GridPane buttonPane = new GridPane();
    GridPane newPane = new GridPane();
    GridPane mnu = new GridPane();

    ListView<ToDoItem> list = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        // list.setMinWidth (200.0);
        catMenu.getItems().add(item1);
        menuBar.getMenus().add(catMenu);

        buttonPane.setPadding(new Insets(10, 10, 10, 10));
        buttonPane.setHgap(5);
//        buttonPane.add(tabPane, 0, 1);
//        tab1.setContent(tPane1);
//        tabPane.getTabs().add(tab1);
//        tab1.setClosable(false);

        newPane.add(list, 1, 1);

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

        addButton.setOnAction(e -> {
            SmallWindow();
        });
    }

    ///////////
    public void SmallWindow() {        

        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();

        primaryPane.add(catTxt, 0, 0);
        primaryPane.add(catAdd, 0, 1);
        primaryPane.add(delete, 0, 2);
        primaryPane.setAlignment(Pos.CENTER);

        Scene primaryScene = new Scene(primaryPane, 400, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Edit Categories");
        primaryStage.show();

//        btnSendBack.setOnAction(e -> {
//            
//        });
    }


public static void main(String[] args) {
        launch(args);
    }

}
