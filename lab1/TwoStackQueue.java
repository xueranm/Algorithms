
public class TwoStackQueue<E> {
	private Stack<E> stack1;
	private Stack<E> stack2;
	
	public TwoStackQueue(){
		
		// We want to initialize our Queue to be empty
		// (ie) initialize stack1 and stack2 
        stack1 = new Stack<E>();
		stack2 = new Stack<E>();
	}
	
	public void enqueue(E newData){
	
		
		// push to stack2
		stack2.push(newData);
        
	}
	
	public Node<E> dequeue(){
	
		// check what's inside the stack1 and 2
		//if stack1 is not empty, return stack1.pop
		//else if stack2 is not empty, take one from stack2 to stack1
		if(!stack1.isEmpty()){
			return stack1.pop();
		}else{
			while(!stack2.isEmpty()){
				E o = stack2.pop().getData();
				stack1.push(o); 
			}
			return stack1.pop();
		}

	}
	
	public boolean isEmpty(){
	
		// Check if the Queue is empty
		return stack1.isEmpty()&&stack2.isEmpty();
			
	}
	
	public void printQueue(){

		// Loop through your queue and print each data
		if (stack2.isEmpty()){
            if (!stack1.isEmpty()){
            	stack1.printStack();
            }
		}else{
	        while(!stack2.isEmpty()){
	        	E p = stack2.pop().getData();
	        	stack1.push(p);
	        }
            stack1.printStack();
		}
	}
}
