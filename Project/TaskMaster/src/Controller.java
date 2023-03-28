/*
 * Controller Class for the buttons
 */

package Project.TaskMaster.src;

import javafx.event.ActionEvent;

public class Controller {
    public void showAll(ActionEvent e){
        System.out.println("Showing all reminders...");
    }

    public void create(ActionEvent e){
        System.out.println("Create a new reminder:");
    }

    public void delete(ActionEvent e){
        System.out.println("Delete current reminder? Y/N");
    }
}
