import java.util.Scanner;

public class TreeCompare {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input1,input2;
        String task1, task2;
        int line1, line2,i;
        BST tree1 = new BST();
        BST tree2 = new BST();
        // Loop over the Scanner
       
        line1 = scan.nextInt();
        
        //while(scan.hasNext()){
        for (i = 0;i<line1+1;i++){
        // Split each line into the task and the corresponding number (if one exists)
            input1 = scan.nextLine();
            String[] phrases1 = input1.split(" ");
        // Depending on what the input task was, preform the corresponding function
            task1 = phrases1[0];
            switch(task1) {
                case "insert":
                    tree1.insert(Integer.parseInt(phrases1[1]));
                    break;
            }
        }
        line2 = scan.nextInt();
        for (i = 0;i<line2+1;i++){
        // Split each line into the task and the corresponding number (if one exists)
            input2 = scan.nextLine();
            String[] phrases2 = input2.split(" ");
        // Depending on what the input task was, preform the corresponding function
            task2 = phrases2[0];
            switch(task2) {
                case "insert":
                    tree2.insert(Integer.parseInt(phrases2[1]));
                    break;
            }

        }
        if (equalTrees(tree1.getRoot(),tree2.getRoot())){
            System.out.print("The trees have the same shape.\n");
        }else{
            System.out.print("The trees do not have the same shape.\n");
        }
        
        scan.close();     
    }
    //for the extra credit
    public static boolean equalTrees(Node a, Node b){
        //both empty
        if(a==null && b ==null){
            return true;
        }
        //both are not empty
        if(a!=null && b!=null){
            return (a.getData()==b.getData()&&equalTrees(a.getLeftChild(),b.getLeftChild())&&equalTrees(a.getRightChild(),b.getRightChild()));
        }
        //if something is not equal
        return false;
    }

}