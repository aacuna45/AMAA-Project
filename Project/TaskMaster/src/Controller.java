/*
 * Controller Class for the buttons
 */

package Project.TaskMaster.src;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller{

    @FXML private TextField reminderTitle;
    @FXML private TextField reminderDate;
    @FXML private TextField reminderDescription;
    @FXML private Label reminderConfirm;
    
    private String title;
    private String description;
    private String date;
    private Stage stage;
    private Scene scene;
    private Parent root;



    public void showAll(ActionEvent event) throws IOException{
        System.out.println("Showing all reminders...");
        root = FXMLLoader.load(getClass().getResource("Scene_ShowAll.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        List<TaskMaster_Reminders> loadReminders = TaskMaster_Reminders.LoadReminders();
        for(int i = 1; i < loadReminders.size(); i++){
            System.out.println(loadReminders.get(i).toString());
        }
    }

    public void create(ActionEvent event) throws IOException{
        System.out.println("Create a new reminder:");
        root = FXMLLoader.load(getClass().getResource("Scene_CreateReminder.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void delete(ActionEvent event) throws IOException{
        System.out.println("Delete current reminder? Y/N");
        root = FXMLLoader.load(getClass().getResource("Scene_TBD.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void back(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Scene_TaskMaster_Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent event) throws IOException{
        System.out.println("CLOSING PROGRAM...");
        System.exit(0);
    }

    public void createSubmit(ActionEvent event){
        title = reminderTitle.getText();
        description = reminderDescription.getText();
        date = reminderDate.getText();

        TaskMaster_Reminders newReminder = new TaskMaster_Reminders(title, description, date);
        TaskMaster_Reminders.CreateReminder(newReminder);
        
        reminderTitle.clear();
        reminderDescription.clear();
        reminderDate.clear();
        reminderConfirm.setText("Reminder Created!");
        //TODO: Add checks to make sure the user is not leaving anything blank
    }

    
}
