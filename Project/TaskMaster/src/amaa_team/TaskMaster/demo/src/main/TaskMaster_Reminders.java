package Project.TaskMaster.src.amaa_team.TaskMaster.demo.src.main;

public class TaskMaster_Reminders {
    public String name;
    public String discription;
    public String date; //Change to Date and Calendar class later
    public int month;
    public int day;
    public int year;

    /* constructors to create a new reminder*/
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
    public void ResolveReminders(){

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
    public int DetermineQuote(){
        return 0;
    }

    @Override
    public String toString(){
        return "Reminder: " + name + discription + "due at: " + date;
    }
} //end class
