package insightCodingChallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kashishdua on 4/7/17.
 */
public class DetectPatterns {
    private class LoginDate implements Comparable<LoginDate>{
        String host;
        private String period;
        private Date date;
        public LoginDate(String ip, String period){
            this.host = ip;
            this.period = period;
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
        public int compareTo(LoginDate other){
            return this.date.compareTo(other.date);
        }
    }
    private Map<String, MinHeapDynamic<LoginDate>> loginList;
    private int N;
    private String inputFile;
    private String outputFile;

    public DetectPatterns(String inputFile, String outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        loginList = new HashMap<>();
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
                String host = components[0];
                String time = (components[3]+components[4]);
                String timeForObject = time.substring(1, time.length()-1);
                LoginDate current = new LoginDate(host, timeForObject);
                if(loginList.containsKey(host)){
                    MinHeapDynamic<LoginDate> minHeapDynamic = loginList.get(host);
                    if(minHeapDynamic==null){
                        MinHeapDynamic<LoginDate> minHeapDynamic1 = new MinHeapDynamic<LoginDate>(6);
                        minHeapDynamic.insert(current);
                        loginList.put(host, minHeapDynamic1 );
                    }
                    else{
                        LoginDate min = minHeapDynamic.min();
                        if(current.date.getTime()-min.date.getTime()/1000>20){
                            logIntoFile(current);
                        }
                        else{
                            
                        }
                    }
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void logIntoFile(LoginDate loginDate){
        //put into the file
    }

}
