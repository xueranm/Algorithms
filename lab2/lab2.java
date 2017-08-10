import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        String task;
        int line,i;
        BST tree = new BST();
        // Loop over the Scanner
       
        scan.nextLine();
        
        while(scan.hasNext()){
        //for (i = 0;i<line+1;i++){
        // Split each line into the task and the corresponding number (if one exists)
            input = scan.nextLine();
            String[] phrases = input.split(" ");
        // Depending on what the input task was, preform the corresponding function
            task = phrases[0];
            switch(task) {
                case "insert":
                    tree.insert(Integer.parseInt(phrases[1]));
                    break;
                case "delete":
                    
                    tree.delete(Integer.parseInt(phrases[1]));
                    break;
                case "inorder":
                    tree.traverse("inorder", tree.getRoot());
                    System.out.print("\n");
                    break;
                case "preorder":
                    tree.traverse("preorder", tree.getRoot());
                    System.out.print("\n");
                    break;
                case "postorder":
                    tree.traverse("postorder", tree.getRoot());
                    System.out.print("\n");
                    break;
            }
        }
        
        scan.close();

        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
        
    }
}