import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class to read CSV-style records of office hours .
 * 
 */
public class OfficeHourReader
{
    // How many fields are expected.
    private static final int NUMBER_OF_FIELDS = 8;
    // Index values for the fields in each record.
    private static final int INSTRUCTOR = 0,
                             DAY = 1,
                             HOUR = 2,
                             MINUTE = 3,
                             DURATION = 4,
                             CAMPUS = 5,
                             OFFICE = 6,
                             COURSE = 7;
                             
    /**
     * Create an OfficeHourReader.
     */
    public OfficeHourReader()
    {
        
    }
    
    /**
     * Read office hours in CSV format from the given file.
     * Return an ArrayList of OfficeHour objects created from
     * the information in the file.
     * 
     * @param filename The file to be read - should be in CSV format.
     * @return A list of OfficeHour.
     */
    public ArrayList<OfficeHour> getOfficeHours(String filename)
    {
        // Create an OfficeHour from a CSV input line.
        Function<String, OfficeHour> createOfficeHour = 
            record -> {
                           String[] parts = record.split(",");
                           if(parts.length == NUMBER_OF_FIELDS) {
                               try {
                                   String instructor = parts[INSTRUCTOR].trim();
                                   String day = parts[DAY].trim();
                                   int hour = Integer.parseInt(parts[HOUR].trim());
                                   int minute = Integer.parseInt(parts[MINUTE].trim());
                                   int duration = Integer.parseInt(parts[DURATION].trim());
                                   String campus = parts[CAMPUS].trim();
                                   String office = parts[OFFICE].trim();
                                   String course = parts[COURSE].trim();
                                   return new OfficeHour(instructor, day, hour, minute, duration,
                                                         campus, office, course);
                               }
                               catch(NumberFormatException e) {
                                   System.out.println("Office Hour record has a malformed integer: " + record);
                                   return null;
                               }
                           }
                           else {
                               System.out.println("Office Hour has the wrong number of fields: " + record);
                               return null;
                           }
                       };
        
        ArrayList<OfficeHour> officeHours;
        
        try {
            officeHours = Files.lines(Paths.get(filename))
                             .filter(record -> record.length() > 0 && record.charAt(0) != '#')
                             .map(createOfficeHour)
                             .filter(officeHour -> officeHour != null)
                             .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open " + filename);
            officeHours = new ArrayList<>();
        }
        return officeHours;
    }
}