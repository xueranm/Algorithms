
import java.lang.*;
public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){

        
        // Make sure to check if anything is in the tree
        //create the new node inserted
        // Hint: if a node n is null, calling n.getData() will cause an error
        //so the condition is root != null
        boolean flag = false;
        Node<E> x = this.root;

        while(!flag){
           
            
            if (this.root ==null){
                this.root= new Node<>(data);
                flag = true;
            }else if(x.getData().compareTo(data)>0||x.getData().compareTo(data)==0){
                
                if (x.getLeftChild()==null){
                   

                    x.setLeftChild(new Node<>(data));
                    

                    x.getLeftChild().setParent(x);
                    
                    flag = true;
                }else{
                    x = x.getLeftChild();
                }
            }else if(x.getData().compareTo(data)<0){
                if(x.getRightChild()==null){
                    x.setRightChild(new Node<>(data));
                    x.getRightChild().setParent(x);
                    flag = true;
                }else{
                    

                    x = x.getRightChild();
                }
            }
        }
        //
        //
        
    }
        

        

    public Node<E> find(E data){

        // Search the tree for a node whose data field is equal to data
        Node<E> x = this.root;
        while (x != null){
            if (x.getData().compareTo(data)>0){
                x = x.getLeftChild();
            }else if(x.getData().compareTo(data)<0){
                x = x.getRightChild();
            }else{
                return x;
            }
        } 
        return null;
    }

    public void delete(E data){
        // Find the right node to be deleted
        Node<E> x = find(data);
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.
        if (x ==null){
            return;
        }
        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        // If said node has one child, delete it by making its parent point to its child.
        if (x.getLeftChild()==null){
            Transplant(x,x.getRightChild());   
        }else if(x.getRightChild()==null){
            Transplant(x,x.getLeftChild());  
        }else{
        // If said node has two children, then replace it with its successor,
        // and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        //because x has two children, so the minimum of its right must be its successor
            Node<E> y = findMin(x.getRightChild());
            if(y.getParent().getData().compareTo(x.getData())!=0){
                Transplant(y,y.getRightChild());
                y.setRightChild(x.getRightChild());
                y.getRightChild().setParent(y);
            }
            Transplant(x,y);
            y.setLeftChild(x.getLeftChild());
            y.getLeftChild().setParent(y);
        }
        
       
        

        // Hint: You may want to implement a findMin() method to find the successor when there are two children
        
        
    }
    
    //transfer the positions of u and v without updating the left and right of v
    private void Transplant(Node<E> u, Node<E> v){
        if (u.getParent()==null){
            this.root = v;
        }else if(u == u.getParent().getLeftChild()){
            u.getParent().setLeftChild(v);
        }else{
            u.getParent().setRightChild(v);
        }
        if (v != null){
            v.setParent(u.getParent());
        }
    }

    //find the minimum of tree or subtree
    private Node<E> findMin(Node<E> x){
        while (x.getLeftChild() != null){
            x = x.getLeftChild();
        }
        return x;
    }
    
   



    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    
                    // data shows first then left child and then right child
                    System.out.print(top.getData()+" ");
                    traverse("preorder",top.getLeftChild());
                    traverse("preorder",top.getRightChild());
                    
                    break;
                case "inorder":
                    
                    traverse("inorder",top.getLeftChild());
                    
                    // order is: left child, data, right child
                    System.out.print(top.getData()+" ");
              
                    traverse("inorder",top.getRightChild());
                    
                    
                    
                    break;
                case "postorder":
                   
                    // order is: left child, right child, data
                    traverse("postorder",top.getLeftChild());
                    traverse("postorder",top.getRightChild());
                    System.out.print(top.getData()+" ");
                    
                    break;
            }
        }
    }
}
