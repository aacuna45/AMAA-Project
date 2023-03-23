package Project.TaskMaster.src;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TaskMaster_Main extends Application{
    
    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        //Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, Color.DARKGREY);
        Image icon = new Image("Project/TaskMaster/src/TaskMaster_icon_trial.png");
        
        stage.setTitle("TaskMaster");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}
