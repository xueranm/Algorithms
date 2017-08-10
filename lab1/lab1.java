import java.util.Scanner;
public class lab1 {
	public static void main(String[] args){
	
		// Create a Scanner that reads system input
		Scanner scan = new Scanner(System.in);
		// Loop over the scanner's input
		String input;
		scan.nextLine();
		while (scan.hasNext()){
			input = scan.nextLine();
			if (isPalindrome(input)){
				System.out.print("This is a Palindrome.\n");
			}else{
				System.out.print("Not a Palindrome.\n");
			}
		}
		scan.close();
		// For each line of the input, send it to isPalindrome()
		// If isPalindrome returns true, print "This is a Palindrome." 
		// Otherwise print "Not a Palindrome."
		
		// Close the Scanner		
        
	}
	
	public static boolean isPalindrome(String s){
	    
		// Create a stack and a Queue of chars that represents the passed in string
		Stack<Character> stack = new Stack<Character>();
		Queue<Character> queue = new Queue<Character>();
		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		
		// Compare your Queue and Stack to see if the input String was a Palindrome or not
        for (int i = 0; i < s.length();i++){
            queue.enqueue(s.charAt(i));
            stack.push(s.charAt(i));
            
        }
        while (!queue.isEmpty()){
            if (!queue.dequeue().getData().equals(stack.pop().getData())){
            	return false;
            }
        }
        return true;
	}
	
	
}
