import java.util.Scanner;

public class lab4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input;
        String task;
        int num;
        RBT<Integer> myRBT = new RBT<Integer>();
        WrongTree<Integer> myWrongTree = new WrongTree<Integer>(20); 
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            String[] phrases = input.split(" ");
            task = phrases[0];
            switch(task) {
                case "insert" :
                    num = Integer.parseInt(phrases[1]);
                    myRBT.insert(num);
                    break;
                case "delete" :
                    num = Integer.parseInt(phrases[1]);
                    myRBT.delete(num);
                    break;
                case "search" :
                    num = Integer.parseInt(phrases[1]);
                    Node<Integer> found = myRBT.search(num);
                    if (found == null){
                        System.out.println("Not Found");
                    } else {
                        System.out.println("Found");
                    }
                    break;
                case "traverse" :
                    myRBT.traverse("preorder", myRBT.getRoot());
                    System.out.println();
                    break;
                case "exit" :
                    System.out.print("Successful Exit");
                    while (sc.hasNextLine()){
                        sc.nextLine();
                    }
                    // TODO:
                    // Exit correctly
                    break;
                case "test" :
                    myRBT.setRoot(myWrongTree.getRoot());
                    System.out.print(myWrongTree.getTime()+" ");
                    if (myRBT.isRBT()){
                        System.out.print("True\n");
                    }else{
                        System.out.print("False\n");
                    }
                	// TODO:
                	// Implement for EC purposes
                	break;
                default:
                    break;
            }
        }
        sc.close();
    }
}