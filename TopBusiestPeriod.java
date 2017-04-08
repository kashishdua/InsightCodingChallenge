package insightCodingChallenge;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.*;

public class TopBusiestPeriod{
    private class PeriodByHour implements Comparable<PeriodByHour>{
        private String minHourPeriod;
        private Date date;
        private int aggregateCount;

        public PeriodByHour(String period, int count){
            this.minHourPeriod = period;
            this.aggregateCount = count;
            String[] split = period.split(":");
            String tempDate = split[0]+":"+split[1]+":"+split[2]+":"+split[3].substring(0,2);
            SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss");
            try{

                date = sd.parse(tempDate);
//                System.out.println(date.toString());
            }
            catch (ParseException e){
                e.printStackTrace();
            }
        }

        public int compareTo(PeriodByHour other){
            if(this.aggregateCount >other.aggregateCount)
                return 1;
            else if(this.aggregateCount <other.aggregateCount)
                return -1;
            else
                return 0;
        }

        public void update(String period, int count){
            this.aggregateCount += count;
            Date myTempDate;
            String[] split = period.split(":");
            String tempDate = split[0]+":"+split[1]+":"+split[2]+":"+split[3].substring(0,2);
            SimpleDateFormat sd = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss");
            try{

                myTempDate = sd.parse(tempDate);
//                System.out.println(date.toString());
                //update minimum date in an hour
                if(this.date.compareTo(myTempDate)>0){
//                if(this.date.getTime()-myTempDate.getTime()>0){
                    this.date = myTempDate;
                    this.minHourPeriod = period;
                    System.out.println("testing");
                }
            }
            catch (ParseException e){
                e.printStackTrace();
            }
        }

        public String toString(){
            return ""+minHourPeriod+", "+aggregateCount;
        }
    }

    private int totalNumberOfLines;
    private int N;
    private String inputFile;
    private String outputFile;
    private Map<String, PeriodByHour> dataMap;
    private List<PeriodByHour> periodByHourList;

    public TopBusiestPeriod(int N, String inputFile, String outputFile){
        this.N = N;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        totalNumberOfLines = 0;
        dataMap = new HashMap<>();
        periodByHourList = new ArrayList<>();
        process();
    }

    public void process(){
        try {
            File file = new File(inputFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] components = line.split(" ");
                int count;
                //this would be key for hour Hash map data
                String[] hourSplitComponents = components[3].split(":");
                String hour = hourSplitComponents[0].substring(1)+":"+hourSplitComponents[1];

                //this would be inserted in the object
                String time = (components[3]+components[4]);
                String timeForObject = time.substring(1, time.length()-1);

                if(dataMap.containsKey(hour)){
                    PeriodByHour temp = dataMap.get(hour);
                    temp.update(timeForObject, 1);
                    dataMap.put(hour, temp);
                }
                else{
                    dataMap.put(hour, new PeriodByHour(timeForObject, 1));
                }
                totalNumberOfLines++;
            }

            fileReader.close();
            Iterator<String> iterator = dataMap.keySet().iterator();
            while(iterator.hasNext()){
                String hour = iterator.next();
                periodByHourList.add(dataMap.get(hour));
            }
            Collections.sort(periodByHourList, Collections.reverseOrder());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mostActivePeriod(){

        if(periodByHourList.size()==0){
            System.out.println("Size = 0, cannot update.");
            return;
        }

        int count;
        System.out.println("Top "+N+" active resources are: ");
        if(N<periodByHourList.size())
            count = N;
        else
            count = periodByHourList.size();

        BufferedWriter bw = null;
        FileWriter fw = null;
        PrintWriter out = null;
        try{
            File file = new File(outputFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            for(int i=0; i<count; i++){
                System.out.println(periodByHourList.get(i).toString());
                out.println(periodByHourList.get(i).toString());
            }
            System.out.println();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if(out != null)
                    out.close();

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}