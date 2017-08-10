public class RBT<E extends Comparable<E>> {
    private Node<E> root;

    public RBT(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void setRoot(Node<E> x){
        root = x;
    }

    public void insert(E data){
        // Perform a regular insert
        boolean done = false;
        Node<E> temp = root;
        Node<E> new_node = new Node<E>(data);
        while(!done){
            if (root == null) {
                root = new_node;
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                if (temp.getLeftChild() == null){
                    temp.setLeftChild(new_node);
                    temp.getLeftChild().setParent(temp);
                    done = true;
                }
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) <= 0){
                if (temp.getRightChild() == null){
                    temp.setRightChild(new_node);
                    temp.getRightChild().setParent(temp);
                    done = true;
                }
                temp = temp.getRightChild();
            }
        }
        new_node.setColor('r');
        
        insert_fixup(new_node);
    }
    private void insert_fixup(Node<E> new_node){


        Node<E> temp1;
        // Check to make sure the tree remains an RBT tree

        while(new_node.getParent()!=null &&new_node.getParent().getColor()=='r'){
            if (new_node.getParent()==new_node.getParent().getParent().getLeftChild()){
                temp1 = new_node.getParent().getParent().getRightChild();
                

                if (temp1 != null&&temp1.getColor()=='r' ){
                    new_node.getParent().setColor('b');
                    temp1.setColor('b');
                    
                    new_node.getParent().getParent().setColor('r');
                    new_node = new_node.getParent().getParent();
                }else{ 
                    if(new_node==new_node.getParent().getRightChild()){
                        new_node=new_node.getParent();
                        leftRotate(new_node);
                    }
                    new_node.getParent().setColor('b');
                    new_node.getParent().getParent().setColor('r');
                    rightRotate(new_node.getParent().getParent());
                }
                
            }else{
                temp1 = new_node.getParent().getParent().getLeftChild();
              
                if (temp1 != null&&temp1.getColor()=='r'){
                
                    new_node.getParent().setColor('b');
                    temp1.setColor('b');
                    new_node.getParent().getParent().setColor('r');
                    new_node = new_node.getParent().getParent();
                }else{ 
                    if(new_node==new_node.getParent().getLeftChild()){
                        new_node=new_node.getParent();
                        rightRotate(new_node);
                    }
                    new_node.getParent().setColor('b');
                    new_node.getParent().getParent().setColor('r');
                    leftRotate(new_node.getParent().getParent());
                }
                
            }
        }
        root.setColor('b');
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree
        boolean done = false;
        Node<E> temp = root;


        while(!done){
            if (temp == null){
                return null;
            }
            if(temp.getData().compareTo(data) == 0){
                done = true;
            } else if (temp.getData().compareTo(data) > 0){
                temp = temp.getLeftChild();
            } else if (temp.getData().compareTo(data) < 0){
                temp = temp.getRightChild();
            }
        }
        return temp;
    }

    private Node<E> getMin(Node<E> top){
        boolean done = false;
        Node<E> temp = top;
        while(!done) {
            if (temp.getLeftChild() == null) {
                done = true;
            } else {
                temp = temp.getLeftChild();
            }
        }
        return temp;
    }

    public void delete(E data){
    	// Preform a regular delete
        Node<E> temp = search(data);
        if (temp == null){
            return;
        }
        Node<E> replacement = new Node(null);

        boolean isRight;
        boolean isLeft;
        boolean isRoot;

        boolean hasChildren;
        boolean hasLeft;
        boolean hasRight;
        boolean hasBoth;

        // Find out if the node to be deleted is the root or if it is a left/right child
        if (temp == root){
            isRoot = true;
            isLeft = false;
            isRight = false;
        } else if (temp == temp.getParent().getLeftChild()){
            isRoot = false;
            isLeft = true;
            isRight = false;
        } else {
            isRoot = false;
            isLeft = false;
            isRight = true;
        }
        
        char replacement_original_color = temp.getColor();
        Node<E> x =new Node(null);
        x.setColor('b');
    
        // Find out if the node to be deleted has children
        // If it does, find out if it has a Right/Left Child or both
        if (temp.getLeftChild() == null && temp.getRightChild() == null){
            hasChildren = false;
            hasBoth = false;
            hasLeft = false;
            hasRight = false;


        } else if (temp.getLeftChild() != null && temp.getRightChild() != null){
            hasChildren = true;
            hasBoth = true;
            hasLeft = true;
            hasRight = true;
        } else if (temp.getLeftChild() == null){
            hasChildren = true;
            hasBoth = false;
            hasRight = true;
            hasLeft = false;
            x = temp.getRightChild();
        } else {
            hasChildren = true;
            hasBoth = false;
            hasRight = false;
            hasLeft = true;
            x = temp.getLeftChild();
        }

        // Seperate cases if the node to be deleted is the root of the tree
        if (isRoot){
            // Seperate cases depending on the number of children the node to be deleted has
            if (!hasChildren){
                root = replacement;
                replacement_original_color = 'r';

            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                replacement_original_color = replacement.getColor();
                x = replacement.getRightChild();
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                    root = replacement;
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                    root = replacement;
                }
                replacement.setColor(temp.getColor());

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(null);
                root = temp.getLeftChild();
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(null);
                root = temp.getRightChild();
            }
        } else {
            // This is the case where it isn't the root node
            if (!hasChildren){
                
                if (isLeft){
                    if (replacement_original_color=='b'&& temp.getParent().getRightChild().getColor()=='b'){
                        temp.getParent().getRightChild().setColor('r');
                        x = temp.getParent();
                    }else if (replacement_original_color=='b'&& temp.getParent().getRightChild().getColor()=='r'){
                        temp.getParent().setColor('r');
                        temp.getParent().getRightChild().setColor('b');
                        leftRotate(temp.getParent());
                        temp.getParent().setColor('b');
                        if (temp.getParent().getRightChild()!=null){
                            temp.getParent().getRightChild().setColor('r');
                        }
                        replacement_original_color ='r';
                        
                    }
                    temp.getParent().setLeftChild(null);
                } else if (isRight){
                    if (replacement_original_color=='b'&& temp.getParent().getLeftChild().getColor()=='b'){
                        temp.getParent().getLeftChild().setColor('r');
                        x = temp.getParent();
                    }else if (replacement_original_color=='b'&& temp.getParent().getLeftChild().getColor()=='r'){
                        temp.getParent().setColor('r');
                        temp.getParent().getLeftChild().setColor('b');
                        rightRotate(temp.getParent());
                        temp.getParent().setColor('b');
                        if (temp.getParent().getLeftChild()!=null){
                            temp.getParent().getLeftChild().setColor('r');
                        }
                        replacement_original_color ='r';
                        
                    }
                    temp.getParent().setRightChild(null);
                }
            } else if (hasBoth){
                replacement = getMin(temp.getRightChild());
                replacement_original_color = replacement.getColor();
                x = replacement.getRightChild();
                if (replacement == temp.getRightChild()){
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setParent(temp.getParent());
                    replacement.getLeftChild().setParent(replacement);
                } else {
                    if (replacement.getParent().getLeftChild() == replacement){
                        replacement.getParent().setLeftChild(null);
                    } else if (replacement.getParent().getRightChild() == replacement){
                        replacement.getParent().setRightChild(null);
                    }
                    replacement.setParent(temp.getParent());
                    replacement.setLeftChild(temp.getLeftChild());
                    replacement.setRightChild(temp.getRightChild());
                    temp.getLeftChild().setParent(replacement);
                    temp.getRightChild().setParent(replacement);
                }
                if(isLeft){
                    temp.getParent().setLeftChild(replacement);
                } else if (isRight){
                    temp.getParent().setRightChild(replacement);
                }
                replacement.setColor(temp.getColor());

            } else if (hasLeft && !hasRight){
                temp.getLeftChild().setParent(temp.getParent());
                temp.getParent().setLeftChild(temp.getLeftChild());
            } else if (hasRight && !hasLeft) {
                temp.getRightChild().setParent(temp.getParent());
                temp.getParent().setRightChild(temp.getRightChild());
            }
        }
    	// Check to make sure the tree remains an RBT tree
        if (replacement_original_color=='b'){
            delete_fixup(x);
        }
        
    }

    private void delete_fixup(Node<E> x){
        if (x ==root){
            if (x.getRightChild()==null){
                x.setColor('r');
                x.getLeftChild().setColor('b');
                rightRotate(x);
                x = x.getParent();
            }else if (x.getLeftChild()==null){
                x.setColor('r');
                x.getRightChild().setColor('b');
                leftRotate(x);
                x = x.getParent();

            }
        }
       
        while (x!= root && x.getColor()=='b'){
            if (x == x.getParent().getLeftChild()){
                x.getParent().setLeftChild(null);
                Node<E> w = x.getParent().getRightChild();
                if (w.getColor()=='r'){
                    w.setColor('b');
                    x.getParent().setColor('r');
                    leftRotate(x.getParent());
                    w = x.getParent().getRightChild();
                }
                if ((w.getLeftChild()==null &&w.getRightChild()==null)||(w.getLeftChild().getColor()=='b' && w.getRightChild().getColor()=='b')){
                    System.out.print("w is:"+w.getData());
                    w.setColor('r');
                    System.out.print("w color:"+w.getColor());
                    x = x.getParent();
                }else{
                    if (w.getRightChild().getColor()=='b'){
                        w.getLeftChild().setColor('b');
                        w.setColor('r');
                        rightRotate(w);
                        w = x.getParent().getRightChild();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor('b');
                    w.getRightChild().setColor('b');
                    leftRotate(x.getParent());
                    x = root;
                }
            }else{
                Node<E> w = x.getParent().getLeftChild();
                if (w.getColor()=='r'){
                    w.setColor('b');
                    x.getParent().setColor('r');
                    rightRotate(x.getParent());
                    w = x.getParent().getLeftChild();
                }
                if (w.getLeftChild().getColor()=='b' && w.getRightChild().getColor()=='b'){
                    w.setColor('r');
                    x = x.getParent();
                }else{
                    if (w.getLeftChild().getColor()=='b'){
                        w.getRightChild().setColor('b');
                        w.setColor('r');
                        leftRotate(w);
                        w = x.getParent().getLeftChild();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor('b');
                    w.getLeftChild().setColor('b');
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }

        x.setColor('b');
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
        if (top != null){
            switch (order) {
                case "preorder":
                    if (top.getData() != null) {
                        System.out.print(top.getData().toString() + " ");
                        traverse("preorder", top.getLeftChild());
                        traverse("preorder", top.getRightChild());
                    }
                    break;
            }

        }
    }


    public void rightRotate(Node<E> x){

        /*
        If y is the root of the tree to rotate with right child subtree T3 and left child x, 
        where T1 and T2 are the left and right children of x:
            y becomes right child of x and T1 as its left child of x
            T2 becomes left child of y and T3 becomes right child of y
        */
    	Node<E> y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if (y.getRightChild()!=null){
            y.getRightChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent()==null){
            root = y;
        }else if(x ==x.getParent().getRightChild()){
            x.getParent().setRightChild(y);
        }else{
            x.getParent().setLeftChild(y);
        }
        y.setRightChild(x);
        x.setParent(y);
    	
    }

    public void leftRotate(Node<E> x){
        /*
        If x is the root of the tree to rotate with left child subtree T1 and right child y, 
        where T2 and T3 are the left and right children of y:
            x becomes left child of y and T3 as its right child of y
            T1 becomes left child of x and T2 becomes right child of x
        */
    	Node<E> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if (y.getLeftChild()!=null){
            y.getLeftChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent()==null){
            root = y;
        }else if(x ==x.getParent().getLeftChild()){
            x.getParent().setLeftChild(y);
        }else{
            x.getParent().setRightChild(y);
        }
        y.setLeftChild(x);
        x.setParent(y);
		
    }

    public boolean isRBT(){
    	//check if root is black
    	if (root ==null){
    		return true;
    	}
    	if (root.getColor()=='r'){
    		return false;
    	}
        //check if the black heights of each path are equal
        if (b_height_euqal(root)==0){
        	return false;
        }
    	//check if the children of red nodes are all black
        if (correct_children_color(root)==false)
        	return false;

        return true;
    }

    //check if the children of red nodes are all black
    private boolean correct_children_color(Node<E> x){
        if (x ==null){
        	return true;
        }
        boolean left = correct_children_color(x.getLeftChild());
        if (left==false){
        	return false;
        }
        boolean right = correct_children_color(x.getRightChild());
        if (right == false){
        	return false;
        }
        if (x.getColor()=='r'){
        	if (x.getLeftChild()!=null && x.getRightChild()!=null){
        		if (x.getRightChild().getColor()=='r'||x.getLeftChild().getColor()=='r'){
        			return false;
        		}
        	}else if (x.getLeftChild()==null &&x.getRightChild()!=null){
        		if (x.getRightChild().getColor()=='r'){
        			return false;
        		}
        	}else if (x.getRightChild()==null&&x.getLeftChild()!=null){
        		if (x.getLeftChild().getColor()=='r'){
        			return false;
        		}
        	}
        }
        return true;
    }

    //check if the black heights of each path are equal
    //if the height is 0, tree is invalid
    //credit: learn from http://stackoverflow.com/questions/13848011/how-to-check-the-black-height-of-a-node-for-all-paths-to-its-descendent-leaves
    private int b_height_euqal(Node<E> x){
    	if (x ==null){
    		return 1;
    	}
    	int left = b_height_euqal(x.getLeftChild());
        if (left ==0){
        	return left;
        }
    	int right = b_height_euqal(x.getRightChild());
    	if (right ==0){
    		return right;
    	}

    	if (left != right){
    		return 0;
    	}else{
    		if (x.getColor()=='b'){
    			return left +1;
    		}else{
    			return left;
    		}
    	}
        
    }

    
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
}
