
package homework3;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author coolm
 */
public class PopUps extends Application {
    
    TextField txtMsg = new TextField();
    Button btnSend = new Button("Send ->");
    
    
    @Override
    public void start(Stage primaryStage) {
        GridPane primaryPane = new GridPane();
        primaryPane.add(txtMsg, 0, 0);
        primaryPane.add(btnSend, 0, 1);
        primaryPane.setAlignment(Pos.CENTER);
        
        Scene primaryScene = new Scene(primaryPane, 300, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Window");
        primaryStage.show();
        
        // Each window/form to itself be an instance object. SecondWindow
        
        btnSend.setOnAction(e -> {
            SmallWindow smw = new SmallWindow(this, txtMsg.getText()); // This is reference to instance object running the code
            
            
        });
    }
    
    public void acceptMessage(String message) { // Instance method that will act on instance objects
        txtMsg.setText(message);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
