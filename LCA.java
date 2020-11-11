import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;

//code added that deals with DAG aswell as Binary tree

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
	    
	    // check if it is a DAG
	    public static boolean hasCycles(DirectedGraph g) {
	    	boolean[] visited = new boolean[g.V];
			boolean[] stack = new boolean[g.V];
			for (int i = 0; i < g.V; i++) {
				if (hasCycles(i, visited, stack, g)) {
					return true;
				}
			}
			return false;
	    	
	    }

		private static boolean hasCycles(int i, boolean[] visited, boolean[] stack, DirectedGraph graph) {
			if (stack[i]) {
				return true;
			}
			if (visited[i]) {
				return false;
			}
			visited[i] = true;
			stack[i] = true;
			List<Integer> children = graph.adj.get(i);
			for (Integer c : children) {
				if (hasCycles(c, visited, stack, graph)) {
					return true;
				}
			}
			stack[i] = false;
			return false;
		}


		// if LCA does not exist return max int
		public static int findLCA(DirectedGraph graph, int v, int w) {
			if (graph.E != 0 && graph.isVertexValid(v) && graph.isVertexValid(w)) {
				if (!hasCycles(graph)) {		// make sure its a DAG
					int r = findRoot(graph);		// find the root of the graph where the indegree is 0
					List<Integer> first = vertexAncestors(graph, r, v); // first set of ancestors relating to v
					List<Integer> second= vertexAncestors(graph, r, w); // second set of ancestors relating to W
					int[] firstArray = first.stream().mapToInt(i -> (int) i).toArray(); // change list to array
					int[] secondArray = second.stream().mapToInt(i -> (int) i).toArray(); // change list to array
					int j, k, temp, depth;
					List<Integer> common = new ArrayList<Integer>(); // common ancestor that v and w have 

					for (j = 0; j < firstArray.length; j++) {
						for (k = 0; k < secondArray.length; k++) {
							if (firstArray[j] == secondArray[k]) {
								common.add(firstArray[j]);							// common ancestor found
							}
						}
					}
					int[] commonArray = common.stream().mapToInt(i -> (int) i).toArray();
					for (j = 1; j < commonArray.length; j++) {
						temp = commonArray[j];
						depth = depth(graph, r, commonArray[j]);
						for (k = j - 1; k >= 0; k--) {
							if (depth > depth(graph, r, commonArray[k])) {	
								commonArray[k + 1] = commonArray[k];
								commonArray[k] = temp;
							}
						}
					}
					return commonArray[0];		// vertex with the greatest distance from root is stored here so return 
				} 
			}
			return Integer.MAX_VALUE;
			}
				
		// Find vertex with indegree = 0 i.e the root
		public static int findRoot(DirectedGraph graph) {
			int vertex = 0;
			for (int i = 0; i < graph.V; i++) {
				if (graph.indegree(i) == 0 && !graph.adj.get(i).isEmpty()) {
					vertex = i;
				}
			}
			return vertex;
		}

		// Find how far from the root a vertex is
		public static int depth(DirectedGraph graph, int root, int vertex) {
			Stack<Integer> visited = new Stack<Integer>();
			int depth = 0;
			return depth(graph, root, vertex, depth, visited);
		}

		private static int depth(DirectedGraph graph, int current, int target, int depth,
				Stack<Integer> visitedVertices) {
			if (current == target) {
				visitedVertices.push(current);
				return visitedVertices.size() - 1;		//number of vertices - current is the depth 
			}

			visitedVertices.push(current);
			Iterator<Integer> i = graph.adj.get(current).listIterator();
			while (i.hasNext()) {
				int newVertex = i.next();
				if (!visitedVertices.contains(newVertex)) {
					depth = depth(graph, newVertex, target, depth, visitedVertices);
					if (!visitedVertices.empty()) {
						visitedVertices.pop();
					}
				}
			}
			return depth;
		}

		// Method which returns a list of vertices which are the ancestors
		public static List<Integer> vertexAncestors(DirectedGraph graph, int root, int vertex) {
			List<Integer> visited = new ArrayList<Integer>();
			List<Integer> ancestors = new ArrayList<Integer>();
			DirectedGraph reversedGraph = graph.reverse();			// reverse the graph to get the ancestor of each node
			ancestors.addAll(vertexAncestors(reversedGraph, vertex, visited));
			return ancestors;
		}

		private static List<Integer> vertexAncestors(DirectedGraph graph, int currentVertex,
				List<Integer> visited) {
			visited.add(currentVertex);

			Iterator<Integer> i = graph.adj.get(currentVertex).listIterator();
			while (i.hasNext()) {
				int newVertex = i.next();
				if (!visited.contains(newVertex)) {
					vertexAncestors(graph, newVertex, visited);
				}
			}

			return visited;
		}

 
	} 
	



