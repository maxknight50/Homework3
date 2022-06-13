/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// this is a test hello maxine 
package homework3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author coolm
 */
public class Homework3 extends Application {

    Label item = new Label("New To-Do Item Title");
    Label category = new Label("Category:");
    
    GridPane tPane1 = new GridPane();
    Tab tab1 = new Tab("Categories");
    GridPane eventsPane = new GridPane();
    TabPane tabPane = new TabPane();

    @Override
    public void start(Stage primaryStage) {
        eventsPane.add(tabPane, 0, 1);
        tab1.setContent(tPane1);
        tabPane.getTabs().add(tab1);
        tab1.setClosable(false);
        
        ComboBox<String> categories = new ComboBox<>();
        Button addButton = new Button("Add New Item ->");
        Button deleteButton = new Button("Delete Selected Item ->");
        
        tPane1.add(item, 0, 0);
        tPane1.add(categories, 0, 2);
        tPane1.add(category, 0,3);
        tPane1.add(addButton, 0,4);
        tPane1.add(deleteButton, 0,5);
        
        primaryStage = new Stage();
        Scene primaryScene = new Scene(eventsPane, 600, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("ToDo");
        primaryStage.show();  
        
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
