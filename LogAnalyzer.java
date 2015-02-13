/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    private int[] dayCounts;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
        dayCounts = new int[31];
    }

    public LogAnalyzer(String log)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(log);
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
        //System.out.println("Hr: Count");
        //for(int hour = 0; hour < hourCounts.length; hour++) {
        //  System.out.println(hour + ": " + hourCounts[hour]);

        int horas = 0;
        while (horas < hourCounts.length)
        {
            System.out.println(horas + ": " + hourCounts[horas]);
            horas++;
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    public int  numberOfAccesses()
    {
        int accesses = 0;
        for(int cont = 0; cont < hourCounts.length ; cont++)
        {
            accesses += hourCounts[cont];
        }
        return accesses;
    }

    public int busiestHour()
    {
        int horaPunta = 0;
        for(int cont = 0; cont < hourCounts.length ; cont++)
        {
            if(hourCounts[cont] > hourCounts[horaPunta])
            {
                horaPunta = cont;
            }
        }
        return horaPunta;
    }

    public int quietestHour()
    {
        int horaTranquila = 0; 
        for(int cont = 0; cont < hourCounts.length ; cont++)
        {
            if(hourCounts[cont] < hourCounts[horaTranquila])
            {
                horaTranquila = cont;
            }
        }
        return horaTranquila;
    }

    public int twoBusiestHour()
    {
        int horaPunta = 0;
        for(int cont = 0; cont < hourCounts.length -1 ; cont++)
        {
            if((hourCounts[cont] + hourCounts[cont + 1]) > (hourCounts[horaPunta] + hourCounts[horaPunta + 1]))
            {
                horaPunta = cont;
            }
        }
        return horaPunta;
    }
    
    /** 
    * Analyze the hourly accesses in the given date
    *
    * @param day     The given day
    * @param month The given month
    * @param year  The given year
    */
   public void analyzeHoutly(int day, int month, int year)
   {
      LogEntry entry = reader.next();
       if(day == entry.getDay()&& month == entry.getMonth() && year == entry.getYear())
      {
          int hour = entry.getHour();
          hourCounts[hour]++;
      }
   }
   public void analyzeDailyData()
   {
       while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
   }
   
   public void printDailyCounts()
   {
       for(int cont = 0; cont < dayCounts.length; cont++)
       {
           System.out.println(dayCounts[cont]);
       }
   }
}
