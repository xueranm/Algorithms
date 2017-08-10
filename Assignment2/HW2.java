import java.util.*;


public class HW2{
	public static void main(String[] args){
		// get the file
		Scanner scan = new Scanner(System.in);
		//number of graph
		int gra_num;

        String a = scan.nextLine();
      

		gra_num = Integer.parseInt(a);
		//if number of graph is 0 or less, return
		if (gra_num<=0){
			return;
		}
		//create a list to store the number of node and edge of each graph
		int[] node_num = new int[gra_num];
		int[] edge_num = new int[gra_num];
		//(int[][])[] adj_matr = new (int[][])[gra_num];

		//get seperate graph's information and store them
		for (int i= 0;i <gra_num;i++){
			String b = scan.nextLine();
			
			node_num[i] = Integer.parseInt(b);
		
            edge_num[i] = Integer.parseInt(scan.nextLine());
          
            
            // a 2d array to store a adjacency matrix
		    int[][] matr = new int[node_num[i]][node_num[i]];

		    //initialize all the distace in adjacency matrix as -1
		    //which means that there is no path between p and q
            for (int p=0;p<node_num[i];p++){
                for (int q=0;q<node_num[i];q++){
                	//distance itself is 0
                	if (p==q){
                		matr[p][q]=0;
                	}
            		matr[p][q]=-1;
                }
            }

            //pass in the edges 
            for (int j=0;j<edge_num[i];j++){
                String input = scan.nextLine();
                String[] phrases = input.split(" ");
                int from = Integer.parseInt(phrases[0]);
                int to = Integer.parseInt(phrases[1]);
            
                matr[from-1][to-1] = 1;
            }

            int short_len = shortest_path(matr,node_num[i]);
            int long_len = longest_path(matr,node_num[i]);
            int paths_num = path_num(matr, node_num[i]);
            System.out.print("graph number: "+(i+1)+"\n");
            System.out.print("Shortest path: "+ short_len+"\n");
            System.out.print("Longtest path: "+ long_len+"\n");
            System.out.print("Number of paths: "+ paths_num+"\n");

            System.out.print("\n");


		}
		scan.close();
        


	}

	private static int shortest_path(int[][] matr, int n){
        
        //dist[index] is the shortest distance from 1 to index
        int[] dist= new int[n];
        //index 0,1,2,3...n-1 mean vertex 1,2,3,4,...n
        //initialization
        dist[0] = 0;
        for (int j=1;j<n;j++){
            dist[j] = -1;//
        }
        ArrayList<Integer> node = new ArrayList<Integer>();
        node.add(0); 
        while(node.size()!=0){
        	int re = node.size()-1;
        	int v = node.get(re);
        	node.remove(re);
        
        	//find the adjacency verteies of 0
            for (int i =0;i<n;i++){
            	//matr[v][i]>0 means that there exits a edge from v to i
            	//dist = -1 means that vertex i has not been visited
            	if (matr[v][i]>0&&dist[i]==-1){
            		dist[i]=dist[v]+1;
            		node.add(i);
            	}
            }
        
            
        }
        int shortest_length = dist[n-1];
        
	    
    return shortest_length;

    }
    private static int longest_path(int[][] matr, int n){
    	//initialization
    	int[] long_len = new int[n];
    	for (int i =0; i <n;i++){
            long_len[i] = -1;
    	}
    	long_len[0] = 0;
    	for (int j = 0; j <n; j++){
    		for (int p = 0; p<n;p++){
    			//find all the adjacency vertex of j
    			if (matr[j][p]>0){
            		if(long_len[p]<(long_len[j]+1)){
            			long_len[p] = long_len[j]+1;
            		}
            		
            	}
    		}
    	}



        
    return long_len[n-1];
    }

    private static int path_num(int[][] matr, int n){

        int[] paths = new int[n];
        for (int i =0; i<n;i++){
        	paths[i] = 0;
        }
        for (int j = 0;j<n;j++){
        	if (matr[0][j]>0){
        		paths[0] = paths[0]+dfs(matr,j, paths, n);
        	}
        }
    	return paths[0];
    }

    private static int dfs(int[][] matr,int u, int[] paths, int n){
        if (u == n-1){
        	return 1;
        }
        else{
        	if (paths[u] ==0){
        		for (int p=0;p<n;p++){
        			//find all the adjacency vertex of u
        			if (matr[u][p]>0){
        				paths[u] = paths[u]+dfs(matr,p,paths,n);
        			}
        		}
        	}
        	return paths[u];
        }
    }
}
