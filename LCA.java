public class LCA {
	
	  
	    class Node { 
	        int key; 
	        Node left, right; 
	  
	        public Node(int item) { 
	            key = item; 
	            left = right = null; 
	        } 
	    } 
	  
	    // Root of BST 
	    Node root; 
	  
	    // Constructor 
	    LCA() {  
	        root = null;  
	    } 
	  
	    // This method mainly calls insertRec() 
	    void insert(int key) { 
	       root = insertRec(root, key); 
	    } 
	      
	    /* A recursive function to insert a new key in BST */
	    Node insertRec(Node root, int key) { 
	  
	        /* If the tree is empty, return a new node */
	        if (root == null) { 
	            root = new Node(key); 
	            return root; 
	        } 
	  
	        /* Otherwise, recur down the tree */
	        if (key < root.key) 
	            root.left = insertRec(root.left, key); 
	        else if (key > root.key) 
	            root.right = insertRec(root.right, key); 
	  
	        /* return the (unchanged) node pointer */
	        return root; 
	    } 
	    
	    Node lca(Node node, int n1, int n2)  
	    { 
	        if (node == null || !search(root,n1) || !search(root,n2)) 
	            return null; 
	   
	        // If both n1 and n2 are smaller than root, then LCA lies in left 
	        if (node.key > n1 && node.key > n2) 
	            return lca(node.left, n1, n2); 
	   
	        // If both n1 and n2 are greater than root, then LCA lies in right 
	        if (node.key < n1 && node.key < n2)  
	            return lca(node.right, n1, n2); 
	   
	        return node; 
	    } 
	    
	    public boolean search (Node root, int n1) {
	    	
	    	if(root.key==n1) return true;
	    	if(root.key>n1) {
	    		if(root.left == null) return false;
	    		else return search(root.left,n1);
	    	}
	    	if(root.key<n1) {
	    		if(root.right == null) return false;
	    		return search(root.right, n1);
	    	}
	    	else return false;	
	    }
	  
	   
	    public static void main(String[] args) { 
	        LCA tree = new LCA(); 
	  
	        /* Let us create following BST 
	              50 
	           /     \ 
	          30      70 
	         /  \    /  \ 
	       20   40  60   80 
	        
	        int n1 = 60;
	        int n2 = 80;
	        tree.insert(50); 
	        tree.insert(30); 
	        tree.insert(20); 
	        tree.insert(40); 
	        tree.insert(70); 
	        tree.insert(n1); 
	        tree.insert(n2); */
	        
	        int n1 = 5;
	        int n2 = 14;
	        tree.insert(15); 
	        tree.insert(10); 
	        tree.insert(20); 
	        tree.insert(13); 
	        tree.insert(12); 
	        tree.insert(n1); 
	        tree.insert(n2); 
	  
	        Node t = tree.lca(tree.root, n1, n2); 
	        System.out.println("LCA of " + n1 + " and " + n2 + " is " + t.key);
	        
	    } 
	} 
	



