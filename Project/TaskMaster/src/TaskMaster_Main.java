package Project.TaskMaster.src;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TaskMaster_Main extends Application{
    
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        String cssFile = this.getClass().getResource("TaskMaster_Styles.css").toExternalForm();

        Parent root = FXMLLoader.load(getClass().getResource("Scene_TaskMaster_Main.fxml"));
        Scene scene = new Scene(root);
        Image icon = new Image("Project/TaskMaster/src/TaskMaster_icon_trial.png");
        
        stage.setTitle("TaskMaster");
        stage.getIcons().add(icon);
        scene.getStylesheets().add(cssFile);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        

        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Window is closing");
                TaskMaster_Reminders.StoreReminders(TaskMaster_Reminders.reminderHolder);
            }
        });

    }
}
