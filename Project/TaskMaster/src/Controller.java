/*
* Controller Class for the buttons
*/

package Project.TaskMaster.src;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    @FXML private TextField reminderTitle;
    @FXML private DatePicker reminderDate;
    @FXML private TextArea reminderDescription;
    @FXML private Label reminderConfirm;

    // ListView setup
    @FXML private ListView<TaskMaster_Reminders> remindersListView;

    @FXML private TableView<TaskMaster_Reminders> tableView = new TableView<>();
    @FXML private VBox vbox;
    private ObservableList<TaskMaster_Reminders> reminders;

    private String title;
    private String description;
    private LocalDate date;
    private Stage stage;
    private Scene scene;
    private Parent root;

    
    @FXML
    public void showAll(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene_ShowAll.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Controller showAllController = loader.getController();

        // Initialize the table and add it to the VBox container
        showAllController.showTable();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void showTable() {
        TableColumn<TaskMaster_Reminders, String> titleColumn = new TableColumn<>("Title");
        TableColumn<TaskMaster_Reminders, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<TaskMaster_Reminders, LocalDate> dateColumn = new TableColumn<>("Date");
    
        titleColumn.setCellValueFactory(new PropertyValueFactory<TaskMaster_Reminders, String>("name"));
        titleColumn.setPrefWidth(80);
        titleColumn.setCellFactory(tc -> {
            TableCell<TaskMaster_Reminders, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(titleColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
        

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<TaskMaster_Reminders, String>("description"));
        descriptionColumn.setPrefWidth(255);
        descriptionColumn.setCellFactory(tc -> {
            TableCell<TaskMaster_Reminders, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(descriptionColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        dateColumn.setPrefWidth(100);


        tableView.getColumns().addAll(titleColumn, descriptionColumn, dateColumn);
    
        List<TaskMaster_Reminders> reminderHolder = TaskMaster_Reminders.LoadReminders();
        reminders = FXCollections.observableArrayList();

        for (int i = 1; i < reminderHolder.size(); i++){
            reminders.add(reminderHolder.get(i));
        }
        tableView.setItems(reminders);
    
        vbox.getChildren().add(tableView);
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