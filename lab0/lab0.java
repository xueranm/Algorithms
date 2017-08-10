import java.util.Scanner;

public class lab0 {
 public static void main(String[] args){
  Scanner scanner = new Scanner(System.in);
  int numProblems = scanner.nextInt();
  for(int i = 0; i < numProblems; ++i){
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    int g = gcd(a,b);
    int l = lcm(a,b);
    System.out.println(g + " " + l);
  }
 }

 public static int gcd(int a, int b){
  // Find the greatest common divisor of a and b
  // Hint: Use Euclids Algorithm
	 int r1;
	 int r2;
	 int r3;
	 if (a>=b){
		 r1=a;
		 r2=b;
	 }else{
		 r1=b;
		 r2=a;
	 }
	 if (r2==0){
		 return r1;
	 }else{
		 r3 = r1%r2;
		 return gcd(r2,r3);
	 }
	 
 }

 public static int lcm(int a, int b){
  // Find the least common multiple of a and b
  // Hint: Use the gcd of a and b
 	int number;
 	number = a*b/gcd(a,b);
 	return number;
 }
}
