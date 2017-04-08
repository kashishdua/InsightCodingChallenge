package insightCodingChallenge;

import java.io.*;
import java.util.*;

/**
 * Created by kashishdua on 4/5/17.
 */
public class TopResources {
    private class Resource implements Comparable<Resource>{
        private String resource;
        private long accessCount;

        public Resource(String resource, long accessCount){
            this.resource = resource;
            this.accessCount = accessCount;
        }
        public int compareTo(Resource otherResource){
            if(this.accessCount>otherResource.accessCount)
                return 1;
            else if (this.accessCount<otherResource.accessCount)
                return -1;
            else
                return 0;
        }

        public String toString(){
            return ""+resource;
        }
    }

    private int totalNumberOfLines;
    private int N;
    private String inputFile;
    private String outputFile;
    private Map<String, Long> dataMap;
    private List<Resource> topResources;

    public TopResources(int N, String inputFile, String outputFile){
        this.N = N;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        totalNumberOfLines = 0;
        dataMap = new HashMap<>();
        topResources = new ArrayList<>();
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
                long count;
                String host = components[6];
                long bytes;
                if(!components[components.length-1].equals("-"))
                    bytes = Long.parseLong(components[components.length-1]);
                else
                    bytes = 0;

                if(dataMap.containsKey(host)){
                    count = dataMap.get(host)+bytes;
                }
                else
                    count = bytes;

                dataMap.put(host, count);
                totalNumberOfLines++;
            }
            fileReader.close();
            Iterator<String> iterator = dataMap.keySet().iterator();
            while(iterator.hasNext()){
                String host = iterator.next();
                topResources.add(new Resource(host, dataMap.get(host)));
            }
            Collections.sort(topResources, Collections.reverseOrder());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostActiveResources(){
        int count;
        System.out.println("Top "+N+" active resources are: ");
        if(N<topResources.size())
            count = N;
        else
            count = topResources.size();


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
                System.out.println(topResources.get(i));
                out.println(topResources.get(i).toString());
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
