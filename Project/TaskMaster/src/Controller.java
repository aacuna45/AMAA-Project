/*
 * Controller Class for the buttons
 */

 package Project.TaskMaster.src;

 import java.io.IOException;
 import java.time.LocalDate;
 import java.util.List;

import javax.sound.sampled.AudioFileFormat.Type;

import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;
 import javafx.event.ActionEvent;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Node;
 import javafx.scene.Parent;
 import javafx.scene.Scene;
 import javafx.scene.control.DatePicker;
 import javafx.scene.control.Label;
 import javafx.scene.control.ListView;
 import javafx.scene.control.TextArea;
 import javafx.scene.control.TextField;
 import javafx.stage.Stage;
 
 public class Controller {
 
     @FXML private TextField reminderTitle;
     @FXML private DatePicker reminderDate;
     @FXML private TextArea reminderDescription;
     @FXML private Label reminderConfirm;
 
     // ListView setup
     @FXML private ListView<TaskMaster_Reminders> remindersListView;
     private ObservableList<TaskMaster_Reminders> reminders;
 
     private String title;
     private String description;
     private LocalDate date;
     private Stage stage;
     private Scene scene;
     private Parent root;
 
     
     @FXML
     public void showAll(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("Scene_ShowAll.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setResizable(false);
         stage.setScene(scene);
         stage.show();
 
         reminders = FXCollections.observableArrayList();
         List<TaskMaster_Reminders> reminderHolder = TaskMaster_Reminders.LoadReminders();
         for (TaskMaster_Reminders temp : reminderHolder) {
             reminders.add(temp);
         }
         
         //add list view stuff here
         
         remindersListView = (ListView<TaskMaster_Reminders>)scene.lookup("#remindersListView");
         remindersListView.setItems(reminders);
 
         //debug stuff
         System.out.println("Done showing all reminders ------ @");
         for (int i = 1; i < reminders.size(); i++) {
             System.out.println("This is reminder: " + i);
             System.out.println(reminders.get(i));
         }
 
     }
 
     @FXML
     public void create(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("Scene_CreateReminder.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setResizable(false);
         stage.setScene(scene);
         stage.show();
         
     }
 
     @FXML
     public void delete(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("Scene_TBD.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setResizable(false);
         stage.setScene(scene);
         stage.show();
     }
 
     @FXML
     public void back(ActionEvent event) throws IOException{
         root = FXMLLoader.load(getClass().getResource("Scene_TaskMaster_Main.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setResizable(false);
         stage.setScene(scene);
         stage.show();
     }
 
     @FXML
     public void close(ActionEvent event) throws IOException{
         System.out.println("CLOSING PROGRAM...");
         System.exit(0);
     }
 
     @FXML
     public void createSubmit(ActionEvent event){
         try{
             title = reminderTitle.getText();
             description = reminderDescription.getText();
             date = reminderDate.getValue();
             System.out.println(date);
             TaskMaster_Reminders newReminder = new TaskMaster_Reminders(title, description, date);
             if (!title.equals("") && !description.equals("") && date != null){
                 TaskMaster_Reminders.CreateReminder(newReminder);
             }else{
                 throw new NullPointerException();
             }
             
             reminderTitle.clear();
             reminderDescription.clear();
             reminderDate.setValue(null);
             reminderDate.setPromptText("Reminder Date");
             
             reminderConfirm.setText("Reminder Created!");
         }catch(NullPointerException e){
             reminderConfirm.setText("Fields in red are empty!");
             if (title.equals("")) reminderTitle.setStyle("-fx-prompt-text-fill: red;");
             if (description.equals("")) reminderDescription.setStyle("-fx-prompt-text-fill: red;");
             if (date == null) reminderDate.setStyle("-fx-prompt-text-fill: red;");
         }
     }
 }