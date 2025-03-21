/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 *
 * 1. Hour 18 is the busiest time of the day
 *
 * 2. Person[] people;
 * 
 * 3. boolean[] vacant;
 * 
 * 5. int[] counts;
 *    boolean[] occupied = new boolean[5000];
 *    
 * 6. readings = new double[60];  
 *    urls = new String[90];
 *    machines = new TicketMachine[5];
 * 
 * 7. 20
 * 
 * 8. double[] prices = new double[50];
 * 
 * 9. it causes an ArrayIndexOutOfBoundsException
 * 
 * 11. public void printGreater(double[] marks, double mean) {
 *       for(int index = 0; index <= marks.length; index++) {
 *          if(marks[index] > mean) {
 *              System.out.println(marks[index]); }}}
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }
    
    public void printGreater(double mean)
    {
       double[] marks = {1.1, 5.7, 6.6, 9.2, 10.4};
       for(int index = 0; index < marks.length; index++) {
        if(marks[index] > mean) {
              System.out.println(marks[index]);
           }
       }
    }
    
    public int numberOfAccesses()
    {
       int total = 0;
       for(int index = 0; index < hourCounts.length; index++){
           total += hourCounts[index];
       }
       return total;
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0; 
        while (hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
