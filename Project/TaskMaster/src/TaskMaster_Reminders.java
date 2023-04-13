package Project.TaskMaster.src;

//Java util libraries
import java.util.ArrayList;
import java.util.List;

//Java io libraries
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//CSV Parser libraries
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class TaskMaster_Reminders {
    private String name;
    private String description;
    private String date; 
    private int month;
    private int day;
    private int year;

    //A global TaskMaster_Reminders List object for storage purposes
    private static List<TaskMaster_Reminders> reminderHolder = new ArrayList<TaskMaster_Reminders>();

    /* constructors to create a new reminder*/
    public TaskMaster_Reminders(String name, String description, int month, int day, int year){
        this.name = name;
        this.description = description;
        this.month = month;
        this.day = day; 
        this.year = year; 
    }

    public TaskMaster_Reminders(String name, String description, String date){
        this.name = name;
        this.description = description;
        this.date = date;

    }

    /* BEGIN CLASS METHODS */

    /* 
     * Searches for reminder from storage
     * @param name is the name of the reminder
     */
    public TaskMaster_Reminders getReminder(String name) {
        return null;
    }

    /*
     * Creates user filled reminder / stores and reloads reminders
     * @TODO FINISH
     */
    public static void CreateReminder(TaskMaster_Reminders inputtedReminder){
        if (reminderHolder.isEmpty()){
            System.out.println("EMPTY");
        }

        reminderHolder.add(inputtedReminder);
        StoreReminders(reminderHolder);
        System.out.println("DONE INPUTTING");
    }


    public static void DeleteReminder(){

    }

    /*
     * Deletes all reminders from storage / reloads
     * @TODO FINISH
     */
    public static void FlushReminders(){

    }

    /*
     * Sorts reminders by date 
     * @TODO FINISH
     */
    public static void SortReminders(){

    }

    /*
     * User determines if they completed the task
     * @TODO FINISH
     */
    public static void resolveReminders(){

    }

    /*
     * Grabs quote from quote file 
     * @TODO FINISH
     */
    public static String generateQuote(){
        return "ADD QUOTES";
    }

    /*
     * Calculates completion percentage to select quote category
     * @TODO FINISH
     */
    public static int determineQuote(){
        return 0;
    }


    public String toString2(){
        return "Reminder: " + this.name + "\n\t" + this.description + "\n\tdue at: " + this.month + "/" + this.day + "/"+ this.year+ "\n";
    }

    @Override
    public String toString(){
        return "Reminder: " + this.name + "\n\t" + this.description + "\n\tdue at: " + this.date;
    }

    /*TODO: probably need a reminder list class if load/store 
        function cant be used in main function*/

    /**
     * Stores loaded reminders list to save file 
     * @param loaded_Rem Loaded Reminder List 
     */
    static public void StoreReminders(List<TaskMaster_Reminders> loaded_Rem){
        //trys to create and write csv file 
        try{
            //temp variables for reminder buffer
            System.out.println("CREATING A NEW CSV FILE...");
            TaskMaster_Reminders temp ; 
            List<String[]> rem_data = new ArrayList<String[]>();

            //creates csv file 
            File file = new File("./reminder.csv") ;

            //creates csv writer 
            FileWriter outputfile;

            if(file.exists()){
                outputfile = new FileWriter(file, true);
            }else{
                outputfile = new FileWriter(file);
            }
            
            CSVWriter writer = new CSVWriter(outputfile);
            
            //loads each reminder in list 
            for (int i = 0; i < loaded_Rem.size(); i++){

                //loads current reminder
                temp = loaded_Rem.get(i); 

                //converts reminder into string array and adds to reminder buffer
                String[] curr_remData = {temp.name, temp.description, temp.date}; 
                rem_data.add(curr_remData);
            }
            //writes buffer to file in csv format 
            writer.writeAll(rem_data,false);

            //closes writer 
            writer.close();
            return;
            }
            //throws expection if cant write file 
            catch (IOException e) {e.printStackTrace();return;}
    }


    /**
     * Load reminders into app list from save file 
     * @return Reminder list loaded from save file 
     */
    static public List<TaskMaster_Reminders> LoadReminders(){
      
        //trys to read csv file 
        try{
            //temp variables for return reminder list  
            List<TaskMaster_Reminders> loaded_Rem = new ArrayList<TaskMaster_Reminders>(); 
            
            //loads file and creates CSV reader 
            File file = new File("./reminder.csv") ;
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);

            //string array to store 
            String[] curr_rem;

            //loads csv rows to string array until EOF 
            while ((curr_rem = csvReader.readNext()) != null) {

                //creates reminder from csv row
                TaskMaster_Reminders curr_load = new TaskMaster_Reminders(curr_rem[0], curr_rem[1], curr_rem[2]);
                
                //adds reminder to return list
                loaded_Rem.add(curr_load);
            }  

            //closes csv reader 
             csvReader.close();

             //returns reminder list 
             return loaded_Rem; 
        }  
        //Throws error if cant read file  
        catch (IOException e) {e.printStackTrace(); return null;}
    } 



} //end class
