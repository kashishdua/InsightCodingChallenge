package insightCodingChallenge;

import important.MaintainTopElements;

import java.io.*;
import java.util.*;

/**
 * Created by kashishdua on 4/5/17.
 */
public class TopActiveHosts {
    private class Host implements Comparable<Host>{
        private String host;
        private int count;

        public Host(String host, int count){
            this.host = host;
            this.count = count;
        }
        public int compareTo(Host otherHost){
            if(this.count>otherHost.count)
                return 1;
            else if(this.count<otherHost.count)
                return -1;
            else
                return 0;
        }
        public String toString(){
            return ""+host+", "+count;
        }
    }
    private int totalNumberOfLines;
    private int N;
    private String inputFile;
    private String outputFile;
    private Map<String, Integer> dataMap;
    private List<Host> topHosts;
    private MaintainTopElements<Host> maintainTopElements;

    public TopActiveHosts(int N, String inputFile, String outputFile){
        this.N = N;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        totalNumberOfLines = 0;
        dataMap = new HashMap<>();
        topHosts = new ArrayList<>();
        maintainTopElements = new MaintainTopElements<>(N);
        process();
    }

    private void process(){
        try {
            File file = new File(inputFile);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] components = line.split(" ");
                int count;
//                for(String s:components)
//                    System.out.println(s);
                String host = components[0];
                if(dataMap.containsKey(host)){
                    count = dataMap.get(host)+1;
                }
                else
                    count = 1;

                dataMap.put(host, count);
                totalNumberOfLines++;
            }
            fileReader.close();
            Iterator<String> iterator = dataMap.keySet().iterator();
            while(iterator.hasNext()){
                String host = iterator.next();
//                maintainTopElements.add(new Host(host, dataMap.get(host)));
                topHosts.add(new Host(host, dataMap.get(host)));
            }
            Collections.sort(topHosts, Collections.reverseOrder());
//            topHosts = maintainTopElements.toList();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostActiveHosts(){
        int count;
        System.out.println("Top "+N+" active hosts are: ");
        if(N<topHosts.size())
            count = N;
        else
            count = topHosts.size();


        BufferedWriter bw = null;
        FileWriter fw = null;
        PrintWriter out = null;
        try{
            File file = new File(outputFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            for(int i=0; i<count; i++){
                System.out.println(topHosts.get(i));
                out.println(topHosts.get(i).toString());
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
