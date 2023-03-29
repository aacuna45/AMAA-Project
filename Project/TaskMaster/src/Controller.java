/*
 * Controller Class for the buttons
 */

package Project.TaskMaster.src;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void showAll(ActionEvent event) throws IOException{
        System.out.println("Showing all reminders...");
        root = FXMLLoader.load(getClass().getResource("Scene_ShowAll.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void create(ActionEvent e){
        System.out.println("Create a new reminder:");
    }

    public void delete(ActionEvent e){
        System.out.println("Delete current reminder? Y/N");
    }
}
