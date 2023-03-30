/*
 * Controller Class for the buttons
 */

package Project.TaskMaster.src;

import java.io.IOException;

import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // //for ChoiceBox
    // @FXML
    // private ChoiceBox<String> mainScreenChoiceBox;
    // private String[] userActions = {"Show All", "Create", "Delete"};
    // @FXML
    // private void initialize(){

    // }

    public void showAll(ActionEvent event) throws IOException{
        System.out.println("Showing all reminders...");
        root = FXMLLoader.load(getClass().getResource("Scene_ShowAll.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void create(ActionEvent event) throws IOException{
        System.out.println("Create a new reminder:");
        root = FXMLLoader.load(getClass().getResource("Scene_TBD.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event) throws IOException{
        System.out.println("Delete current reminder? Y/N");
        root = FXMLLoader.load(getClass().getResource("Scene_TBD.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void back(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("TaskMaster_Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent event) throws IOException{
        System.exit(0);
    }
}
