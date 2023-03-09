package Project.TaskMaster.src;

import java.util.Scanner;

/* SOME VALUES HARD-CODED FOR DEMO PURPOSES ONLY */
public class TaskMaster_Main{
    public static String user = "USERNAME";
    static Scanner userInput = new Scanner(System.in);

    //Hard-coded values for the Demo
    static TaskMaster_Reminders test = new TaskMaster_Reminders("Task 1", "This is a test to see if this works", "12/12/12");
    

    public static void WelcomeScreen(){
        System.out.println("\t\t*****\tWelcome To TaskMaster\t" + user + "!*****\n\n");
        userOptions();
    } //end WelcomeScreen
    
    public static void userOptions(){
        System.out.println("What would you like to do? Please select on of the following:");
        System.out.println("1) Show all reminders\t\t 2) Create a new reminder\t\t 3) Show Motivational Quote\t\t 4) Exit\n");

        int userOption = userInput.nextInt();
        
        while(true){
            if (userOption == 1){
                showReminders(test);
                break;
            }else if (userOption == 2){
                createReminder();
                break;
            }else if (userOption == 3){
                showQuote();
                break;
            }else if (userOption == 4){
                System.out.println("Thank you for using our program. Have a great day!\n");
                System.exit(0);
            }else{
                System.out.println("Invalid Input. Please try again\n");
                userOption = userInput.nextInt();
            }
        }
    } //end userOptions

    public static void showReminders(TaskMaster_Reminders reminder){
        System.out.println("DEMO VERSION. FEATURE NOT IMPLEMENTED YET.");
        userOptions();
    }

    public static void createReminder(){
        System.out.println("DEMO VERSION. FEATURE NOT IMPLEMENTED YET.");
        userOptions();
    }

    public static void showQuote(){
        System.out.println("\"To live life is a wonderful gift\"\n");
        userOptions();
    }
} //end TaskMaster_Main

class MainClass extends TaskMaster_Main{
    public static void main(String[] arg){
        //starting sequence
        WelcomeScreen();

        //end sequence
        userInput.close();
        System.out.println("\n\n\n\n\n");
    }
}
