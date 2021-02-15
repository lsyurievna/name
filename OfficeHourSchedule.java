import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class OfficeHourSchedule. 
 * It keeps information about available office hours at MUN.
 *
 * @author course instructor
 * @version 2021.02.11
 */
public class OfficeHourSchedule
{
    private ArrayList<OfficeHour> officeHours;

    /**
     * Constructor for objects of class OfficeHourSchedule
     */
    public OfficeHourSchedule()
    {
        officeHours = new ArrayList<>();
        addOfficeHoursFromFile("office.csv");
    }
    
    /**
     * Prints names of instructors who have office hours on the given day
     * @param day The day of the week.
     */
    public void printIsInOn(String day)
    {
        officeHours.stream()
            .filter(oh -> oh.getDay().equals(day))
            .forEach(oh -> System.out.println(oh.getInstructor()));
    }
    
    /**
     * Checks if instructor is available during the week for at least the given time
     * @param instructor The name of the instructor
     * @param minutes The time threashold
     * @return TRUE if instructor is available for the given threashold; otherwise FALSE
     */
    public boolean isAvailableFor(String instructor, int minutes)
    {
        return officeHours.stream()
            .filter(oh -> oh.getInstructor().equals(instructor))
            .map(oh -> oh.getDuration())
            //come back to this later and check logic
            .map(time -> time >= minutes)
            .reduce(false,(acc,time) -> time || acc);

    }
    
    /**
     * Returns names of all instructors offering office hours on given campus at given hour
     */
    public String findInstructorsOnAfter(String campus, int hour)
    {
        HashSet <String> instructors = new HashSet<>();
        officeHours.stream()
            .filter(oh -> oh.getCampus().equals(campus))
            .filter(oh -> oh.getStartHour() > hour)
            .map(oh -> oh.getInstructor())
            .forEach(name -> instructors.add(name));
        return instructors.stream()    
            .reduce(("Instructors who are available on " + campus + " at " + hour), (acc, name) -> acc + name);
    }
    
    
    /**
     * Add the office hours recorded in the given filename to the schedule.
     * @param filename A CSV file of OfficeHour records.
     */
    private void addOfficeHoursFromFile(String filename)
    {
        OfficeHourReader reader = new OfficeHourReader();
        officeHours.addAll(reader.getOfficeHours(filename));
    }

    /**
     * Prints for all office hours the corresponding info
     */
    public void printAllOfficeHours()
    {
        for (OfficeHour officeh : officeHours)
        {
            officeh.printInfo();
            System.out.println();
        }
    }
}