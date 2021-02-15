import java.util.ArrayList;

/**
 * Write a description of class OfficeHour here.
 *
 * @author course instructor
 * @version 2021.02.11
 */
public class OfficeHour
{
   private String instructor;
   private String dayOfWeek;
   private int startTimeHours;
   private int startTimeMinutes;
   private int durationInMinutes;
   private String campus;
   private String office;
   private String course;
   
   // Ignore these two declarations
   private String minutesString;
   private String hoursString;

   /**
    * Constructor for objects of class OfficeHour
    */
   public OfficeHour(String instructor, String day, int startTimeH, int startTimeM, int duration,  
                     String campus, String office, String course)
   {
       this.instructor = instructor;
       dayOfWeek = day;
       startTimeHours = startTimeH;
       startTimeMinutes = startTimeM;
       durationInMinutes = duration;
       this.campus = campus;
       this.office = office;
       this.course = course;
       
       //ignore this part
       minutesString = startTimeM == 0 ? "00" : "" + startTimeM;
       hoursString = startTimeH < 10 ? "0" + startTimeH : ""+startTimeH;
   }
   
   public String getInstructor()
   {
       return instructor;
   }
   
   public int getStartHour()
   {
       return startTimeHours;
   }
   
   
   public String getCampus()
   {
       return campus;
   }
   
   public String getDay()
   {
       return dayOfWeek;
   }
   
   public int getDuration()
   {
       return durationInMinutes;
   }
   
   /**
    * Prints information about the office hour
    */
   public void printInfo()
   { 
       System.out.println(instructor + " | " + dayOfWeek + " | " + hoursString + ":" + minutesString +
       " | " + durationInMinutes + " minutes | " + campus + " | " + office + " | " + course);
   }   
}
