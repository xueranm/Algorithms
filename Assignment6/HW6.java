import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;


public class HW6{

    public static Set<String> dictionary = new HashSet<String>();
    public static String phrase;
    public static boolean[] split;    //subproblem split
    public static int[] pos;  //mark the position splitted
    public static int[] pos2;  //mark the position splitted

	public static void main(String[] args){
		// load the dicitionary
        loadDictionary("diction10k.txt");
		Scanner scan = new Scanner(System.in);
		//number of phrases
		int phr_num;
        String a = scan.nextLine();
		phr_num = Integer.parseInt(a);
		//if number of phrase is 0 or less, return
		if (phr_num<=0){
			return;
		}
        for (int i =0; i<phr_num;i++){
            phrase = scan.next();
            System.out.println("phrase number: "+ (i+1)+"\n"+phrase+"\n");
            split = new boolean[phrase.length()];
            pos = new int[phrase.length()];
            for(int j =0; j < phrase.length();j++){
                split[j] = false;
            }
            System.out.println("iterative attempt:");
            iterative();
            if(!split[0]){
                System.out.println("NO, cannot be split\n");
            }else{
                System.out.println("YES, can be split");
                toWords(0,pos[0]);
            }
            for(int j =0; j < phrase.length();j++){
                split[j] = false;
            }
            pos2 = new int[phrase.length()];
            System.out.println("memoized attempt:");
            recursive(0);
            if(!split[0]){
                System.out.println("NO, cannot be split\n");
            }else{
                System.out.println("YES, can be split");
                toWords2(0,pos2[0]);
            }
	    }

    }

    public static void iterative(){
        for (int i = phrase.length()-1;i>=0;i--){
            if (split[i] == false){
                for (int j = i; j < phrase.length(); j++){
                    if (dictionary.contains(phrase.substring(i,j+1))&&((j+1 == phrase.length())?true:split[j+1])){
                        split[i] = true;
                        pos[i] = j;
                    }
                }
            }
        }
        return;
    }

    public static boolean recursive(int index){
        if (index == phrase.length()){
            return true;
        }
        if (split[index] == false){
            for (int j = index;j <phrase.length(); j++){
                if (dictionary.contains(phrase.substring(index,j+1))&&recursive(j+1)){
                    split[index] = true;
                    pos2[index] = j;
                }
            }
        }
        return split[index];
    }

    public static void toWords(int a, int b){
        if (b +1 >= phrase.length()){
            System.out.println(phrase.substring(a,b+1)+"\n");
            return;
        }
        System.out.print(phrase.substring(a, b+1)+" ");
        toWords(b+1, pos[b+1]);
        return;
    }

    public static void toWords2(int a, int b){
        if (b +1 >= phrase.length()){
            System.out.println(phrase.substring(a,b+1)+"\n");
            return;
        }
        System.out.print(phrase.substring(a, b+1)+" ");
        toWords(b+1, pos2[b+1]);
        return;
    }

    public static void loadDictionary(String dictionaryFileName)
    {
        File inFile = new File(dictionaryFileName);
        try {
            Scanner scan = new Scanner(inFile);
            String line;
            while (scan.hasNext()) {
                line = scan.next();
                dictionary.add(line.trim());
            }//while
            scan.close();
        } //try block
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }//load dictionary
}
	