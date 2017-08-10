import java.util.Scanner;

public class Assignment0{
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input_l, input;
        int line, number1,number2, add_r, mul_r;
        
        //get the first line number
        input_l = scan.nextLine();
        line = Integer.parseInt(input_l);
        
        // Loop over the Scanner
        for (int i=0;i<line;i++){
        	// Split each line into 2 numbers
        	input = scan.nextLine();
        	String[] numbers = input.split(" ");
        	number1 = Integer.parseInt(numbers[0]);
        	number2 = Integer.parseInt(numbers[1]);

            //get the results of add and multiply
            add_r = number1 + number2;
            mul_r = number1 * number2;
            System.out.print(add_r+" "+mul_r+"\n");
        }
        scan.close();

    }
}