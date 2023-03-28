package Project.TaskMaster.src;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TaskMaster_Main extends Application{
    
    public static void main(String args[]){
        TaskMaster_Reminders test = new TaskMaster_Reminders("Test Reminder", "This is a test", "12-03-2023");
        System.out.println("\n\n\n" + test.toString() + "\n\n\n");
        launch(args);

        
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TaskMaster_Main.fxml"));
        Scene scene = new Scene(root, Color.DARKGREY);
        Image icon = new Image("Project/TaskMaster/src/TaskMaster_icon_trial.png");
        
        stage.setTitle("TaskMaster");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}
