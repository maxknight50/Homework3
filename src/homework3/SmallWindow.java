
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
public class SmallWindow {
 
    Label lblMsg = new Label();
    Button btnSendBack = new Button("<-- Send message back");
    
    MainWindow parentForm;
    
    public SmallWindow(MainWindow parentForm, String message) { // Method 2: second parameter
        this.parentForm = parentForm; // Whoever instantiates this has to send a reference to itself
        
        // Method 1:
        // lblMsg.setText(this.parentForm.txtMsg.getText());
        
        // Method 2:
        // Constructor Parameter
        lblMsg.setText(message);
        
        Stage primaryStage = new Stage();
        GridPane primaryPane = new GridPane();
        
        primaryPane.add(lblMsg, 0, 0);
        primaryPane.add(btnSendBack, 0, 1);
        primaryPane.setAlignment(Pos.CENTER);
        
        Scene primaryScene = new Scene(primaryPane, 400, 300);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Small WIndow");
        primaryStage.show();
        
        btnSendBack.setOnAction(e -> {
           parentForm.acceptMessage("Hello from SmallWindow");
        });
    }
}
