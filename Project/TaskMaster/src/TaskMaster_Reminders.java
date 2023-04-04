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
    public String name;
    public String discription;
    public String date; //TODO:Change to Date and Calendar class later
    public int month;
    public int day;
    public int year;

    /* constructors to create a new reminder*/
    public TaskMaster_Reminders(String name, String discription, int month, int day, int year){
        this.name = name;
        this.discription = discription;
        this.month = month;
        this.day = day; 
        this.year = year; 
        
    }

    public TaskMaster_Reminders(String name, String discription, String date){
        this.name = name;
        this.discription = discription;
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
    public void CreateReminder(){

    }


    public void DeleteReminder(){

    }

    /*
     * Deletes all reminders from storage / reloads
     * @TODO FINISH
     */
    public void FlushReminders(){

    }

    /*
     * Sorts reminders by date 
     * @TODO FINISH
     */
    public void SortReminders(){

    }

    /*
     * User determines if they completed the task
     * @TODO FINISH
     */
    public void resolveReminders(){

    }

    /*
     * Grabs quote from quote file 
     * @TODO FINISH
     */
    public String generateQuote(){
        return "ADD QUOTES";
    }

    /*
     * Calculates completion percentage to select quote category
     * @TODO FINISH
     */
    public int determineQuote(){
        return 0;
    }


    public String toString2(){
        return "Reminder: " + this.name + "\n\t" + this.discription + "\n\tdue at: " + this.month + "/" + this.day + "/"+ this.year+ "\n";
    }

    @Override
    public String toString(){
        return "Reminder: " + this.name + "\n\t" + this.discription + "\n\tdue at: " + this.date;
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
            TaskMaster_Reminders temp ; 
            List<String[]> rem_data = new ArrayList<String[]>();

            //creates csv file 
            File file = new File("./reminder.csv") ;

            //creates csv writer 
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            
            //loads each reminder in list 
            for (int i = 0; i < loaded_Rem.size(); i++){

                //loads current reminder
                temp = loaded_Rem.get(i); 

                //converts reminder into string array and adds to reminder buffer
                String[] curr_remData = {temp.name, temp.discription, Integer.toString(temp.month),Integer.toString(temp.day),Integer.toString(temp.year) }; 
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
                TaskMaster_Reminders curr_load = new TaskMaster_Reminders(curr_rem[0], curr_rem[1], Integer.parseInt(curr_rem[2]), Integer.parseInt(curr_rem[3]), Integer.parseInt(curr_rem[4]));
                
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
