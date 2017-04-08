package insightCodingChallenge;

import important.MaintainTopElements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kashishdua on 4/5/17.
 */
class MyWindow implements Comparable<MyWindow>{
    public int startTime;
    public int aggCount;
    public MyWindow(int startTime, int aggCount){
        this.aggCount = aggCount;
        this.startTime = startTime;
    }
    public String toString(){
        return ""+startTime+", "+aggCount;
    }

    public int compareTo(MyWindow other){
        if(this.aggCount>other.aggCount)
            return 1;
        else if(this.aggCount<other.aggCount)
            return -1;
        else
            return 0;
    }
}
class Tester implements Comparable<Tester>{
    public int time;
    public int count;
    public Tester(int time, int count){
        this.time = time;
        this.count = count;
    }

    public int compareTo(Tester other){
        if(this.count>other.count)
            return 1;
        else if(this.count<other.count)
            return -1;
        else
            return 0;
    }

    public String toString(){
        return ""+time+", "+count;
    }
}

public class TestInsight {

    public static void main(String[] args){

//        TopActiveHosts topActiveHosts = new TopActiveHosts(10, "log.txt", "hosts.txt");
//        topActiveHosts.mostActiveHosts();

//        long time1 = System.nanoTime();
//        TopResources topResources = new TopResources(10, "test.txt", "resources.txt");
//        topResources.mostActiveResources();
//        long time2 = System.nanoTime();
//        System.out.println("Time taken: "+(time2-time1)+"ns");
        TopBusiestPeriod topBusiestPeriod = new TopBusiestPeriod(10, "test.txt", "hours.txt");
        topBusiestPeriod.mostActivePeriod();
//
//        try {
//            File file = new File("test.txt");
//            FileReader fileReader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            StringBuffer stringBuffer = new StringBuffer();
//            String line;
//            int stop = 1;
//
//            while ((line = bufferedReader.readLine()) != null && stop<2) {
//                String[] components = line.split(" ");
//                int count;
//                for(String s:components)
//                    System.out.println(s);
//                System.out.println();
//                String[] hourSplitComponents = components[3].split(":");
//                String hour = hourSplitComponents[0].substring(1)+":"+hourSplitComponents[1];
//                System.out.println(hour);
//                stop++;
//                String tempTime = (components[3]+components[4]);
//
//                String timeForObject = tempTime.substring(1, tempTime.length()-1);
//                String[] split = timeForObject.split(":");
//                String time = split[0]+":"+split[1]+":"+split[2]+":"+split[3].substring(0,2);
//                System.out.println(timeForObject);
//                System.out.println(time);
//                stop++;
//            }
//            fileReader.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        MaintainTopElements<Integer> maintainTopElements = new MaintainTopElements<>(3);
//        maintainTopElements.add(10);
//        maintainTopElements.display();
//        maintainTopElements.add(4);
//        maintainTopElements.display();
//        maintainTopElements.add(3);
//        maintainTopElements.display();
//        maintainTopElements.add(5);
//        maintainTopElements.add(6);
//        maintainTopElements.display();
//        maintainTopElements.add(11);
//        maintainTopElements.add(12);
//        maintainTopElements.display();
//        Sliding window code
//        int slidingWindow = 3;
//        Integer prevSum = null;
//        int sum = 0;
//        Integer[] integers = {2,3,1,5,6,8};
//        System.out.println("Sliding windows");
//        for(int i=0;i<slidingWindow;i++){
//            sum = sum + integers[i];
//        }
//        System.out.println(sum);
//        for(int i=1; i<integers.length-slidingWindow+1;i++){
//            sum = sum - integers[i-1] + integers[i+slidingWindow-1];
//            System.out.println(sum);
//        }
//        for(int i=0; i<integers.length-slidingWindow+1;i++){
//            for(int j=i;j<i+slidingWindow;j++){
//                System.out.print(integers[j]+" ");
//            }
//
//
//            System.out.println();
//        }

//        TopBusiestPeriod topBusiestPeriod = new TopBusiestPeriod(10, "test.txt", "output.txt");
//        topBusiestPeriod.display();
//        List<Integer> list = new ArrayList<>();
//        int[] integer = {1,2,4,7,64, 190, 220};
//        if(integer==null || integer.length==0)
//            throw new NullPointerException("Empty array");
//
//        if(integer.length==1){
//            list.add(integer[0]);
//            return;
//        }
//
//        int startIndex = 0;
//        int endIndex = 0;
//        int length = integer.length;
//        int prevSum = integer[startIndex];
//        int sum;
//        int initial = 1;
//        while(initial<length && integer[initial]-integer[startIndex]<=60){
//            prevSum = prevSum+integer[initial++];
//            endIndex++;
//        }
//        list.add(prevSum);
//        int prevStartIndex = startIndex;
//        int prevEndIndex = endIndex;
//        if(startIndex==endIndex){
//            startIndex++;
//            endIndex++;
//        }
//        else
//            startIndex++;
//
//
//        while(endIndex<length){
//            sum = 0;
//
//            int i = endIndex+1;
//
//            while(i<length && (integer[i]-integer[startIndex]<=60)){
//                sum += integer[i];
//
//                i++;
//            }
//            endIndex = i-1;
//            if(startIndex==endIndex){
//                if(prevEndIndex!=endIndex){
//                    list.add(sum);
//                }
//                startIndex++;
//                endIndex++;
//                prevStartIndex = 0;
//                prevEndIndex = endIndex;
//                prevSum = 0;
//            }
//            else{
//
//                if(prevEndIndex!=endIndex){
//                    sum = sum+prevSum-prevStartIndex;
//                    prevStartIndex = startIndex;
//
//                    list.add(sum);
//                }
//                prevSum = sum;
//                prevStartIndex = startIndex;
//                prevEndIndex = endIndex;
//                startIndex++;
//            }
//
//        }
//

//        List<Tester> listTester = new ArrayList<>();
//        listTester.add(new Tester(1, 12));
//        listTester.add(new Tester(3, 9));
//        listTester.add(new Tester(4, 13));
//        listTester.add(new Tester(7, 4));
//        listTester.add(new Tester(62, 22));
//        listTester.add(new Tester(63, 21));
//        listTester.add(new Tester(190, 13));
//        listTester.add(new Tester(220, 17));
//        listTester.add(new Tester(240, 11));
////        int[] integer = {1,2,4,7,64, 190, 220};
//        List<MyWindow> windlowList = new ArrayList<>();
//
//        for(Tester t: listTester)
//            System.out.println(t);
//        System.out.println();
//        System.out.println();
//        int size = listTester.size();
//        for(int i=0; i<size;i++){
//            int sum = 0;
//            for(int j=i; j<size && listTester.get(j).time-listTester.get(i).time<60; j++){
//                sum = sum + listTester.get(j).count;
//            }
//            windlowList.add(new MyWindow(listTester.get(i).time, sum));
//        }
//
//        Collections.sort(windlowList, Collections.reverseOrder());
//        for(MyWindow window: windlowList)
//            System.out.println(window);


    }

}
