import java.util.Scanner;
import java.lang.*;

public class lab3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        String task;
        String first;
    	// Loop over the Scanner
        first = scan.nextLine();
        int line = Integer.parseInt(first);
        pQueue myqueue = new pQueue<>(line);
        // Split each line into the task and the corresponding number (if one exists)
        while(scan.hasNext()){
            input = scan.nextLine();
            String[] phrases = input.split(" ");
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, maximum, extractMax, isEmpty, or print
        // Hint: Use a switch-case statement
            task = phrases[0];
            switch(task){
            	case "insert":
            	    myqueue.insert(Integer.parseInt(phrases[1]));
            	    break;
            	case "maximum":
            	    System.out.print(myqueue.maximum()+"\n");
            	    break;
            	case "extractMax":
            	    System.out.print(myqueue.extractMax()+"\n");
            	    break;
            	case "isEmpty":
            	    if (myqueue.isEmpty()){
            	    	System.out.print("Empty"+"\n");
            	    }else{
            	    	System.out.print("Not Empty"+"\n");
            	    }
            	    break;
            	case "print":
            	    myqueue.print();
            	    break;
                case "build":

                    String temp0 = phrases[1].replace("[","");
                    String temp1 = temp0.replace("]","");
                  
              
                    String[] s_arr = temp1.split(",");
                 
                    Integer[] new_array = new Integer[s_arr.length+1];
                    new_array[0] = null;
                    for (int j =1;j<new_array.length;j++){
                        new_array[j] = Integer.parseInt(s_arr[j-1]);
                    }     
                    myqueue.build(new_array);
            }
        }
        // Don't forget to close your Scanner!
        scan.close();
    }
}
