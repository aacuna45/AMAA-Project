/*
* Controller Class FXML file
*/

package Project.TaskMaster.src;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/* 
 * The following supress is ONLY used to supress the "generic type" warning
 * when dealing with TableColumn and adding new columns to said table. 
 */
@SuppressWarnings("unchecked")
public class Controller {

    @FXML private TextField reminderTitle;
    @FXML private DatePicker reminderDate;
    @FXML private TextArea reminderDescription;
    @FXML private Label reminderConfirm;
    @FXML private Label deleteConfirmation;
    @FXML private VBox deleteReminderVBox;

    // TableView setup
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
        TableColumn<TaskMaster_Reminders, LocalDate> dateColumn = new TableColumn<>("Date Due");
    
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
    } // end showTable
    
    public void deleteTable() {
        TableColumn<TaskMaster_Reminders, String> titleColumn = new TableColumn<>("Title");
        TableColumn<TaskMaster_Reminders, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<TaskMaster_Reminders, LocalDate> dateColumn = new TableColumn<>("Date Due");
        TableColumn<TaskMaster_Reminders, Void> deleteColumn = new TableColumn<>("Delete?");

        deleteColumn.setPrefWidth(60); // Set the width to 60 pixels
        deleteColumn.setCellFactory(column -> {
            return new TableCell<TaskMaster_Reminders, Void>(){
                private final Button deleteButton = new Button("Delete");
                {
                    deleteButton.setOnAction(event -> {
                        TaskMaster_Reminders deleteReminder = getTableView().getItems().get(getIndex());
                        TaskMaster_Reminders.DeleteReminder(deleteReminder);
                        deleteConfirmation.setText("Reminder Deleted!");
                        try {
                            delete(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
        });
    
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
        descriptionColumn.setPrefWidth(300);
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


        tableView.getColumns().addAll(titleColumn, descriptionColumn, dateColumn, deleteColumn);
    
        List<TaskMaster_Reminders> reminderHolder = TaskMaster_Reminders.LoadReminders();
        reminders = FXCollections.observableArrayList();

        for (int i = 1; i < reminderHolder.size(); i++){
            reminders.add(reminderHolder.get(i));
        }
        tableView.setItems(reminders);
    
        deleteReminderVBox.getChildren().add(tableView);
    } //end deleteTable()

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene_DeleteReminders.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Controller deleteController = loader.getController();

        deleteController.deleteTable();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
    public void backConfirm(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Scene_TaskMaster_Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        save();
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

    @FXML
    public void deleteAllReminders(ActionEvent event) throws IOException{
        TaskMaster_Reminders.FlushReminders(); //delete the file
        TaskMaster_Reminders.StoreReminders(TaskMaster_Reminders.reminderHolder); //recreate the file with nothing but the header in there!
        delete(event); //reshow the table!
    }

    @FXML
    public void about(ActionEvent event){
        try{
            Desktop.getDesktop().browse(new URI("https://alenrtan.github.io/amaa-team.github.io/"));
        }catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void export(ActionEvent event) throws IOException{
        File csvFile = new File("reminder.csv");
        Desktop.getDesktop().open(csvFile);
    }

    @FXML
    public void save(){
        TaskMaster_Reminders.StoreReminders(TaskMaster_Reminders.reminderHolder);
        TaskMaster_Reminders.reminderHolder.clear();
    }
}