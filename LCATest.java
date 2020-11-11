import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Assert;

public class LCATest {					// TESTS BOTH DAG AND BST

	 
	
	
	
	@Test
	public void isAcyclic() {
		DirectedGraph test = new DirectedGraph(0);
		assertFalse("empty graph so should be no cycles", LCA.hasCycles(test));
		test = new DirectedGraph(1);
		test.addEdge(0, 0);
		assertTrue("Cycle - vertex has an edge that goes to itself", LCA.hasCycles(test));
		test = new DirectedGraph(4);
		test.addEdge(0, 1);
		test.addEdge(1, 2);
		test.addEdge(2, 3);
		assertFalse("Graph with no cycles", LCA.hasCycles(test) );
		test.addEdge(3, 0);
		assertTrue("Graph that now has a cycle", LCA.hasCycles(test));
		
	}
	
	@Test
	public void BSTLca () {			
		LCA tree = new LCA();
		assertSame("null entry", null,tree.lca(tree.root,4,5));
		tree.insert(15);
		assertSame("tree with only root", null,tree.lca(tree.root,4,5)); 
        tree.insert(10); 
        tree.insert(20); 
        tree.insert(13); 
        tree.insert(12); 
        tree.insert(5); 
        tree.insert(14);
        assertSame("tree with inputs 15,5", 10 ,tree.lca(tree.root,14,5).key);
        assertSame("parameter not in tree" , null ,tree.lca(tree.root,9,5));
        	
	}
	
	@Test
	public void BSTInsert() {
		LCA tree = new LCA();
		tree.insert(15); 
        tree.insert(10); 
        tree.insert(20); 
        tree.insert(13); 
        tree.insert(12); 
        tree.insert(5); 
        tree.insert(14);
        assertTrue("check if key is in tree-- 5 is", tree.search(tree.root,5));
        assertFalse("check if key is in tree -- 99  is not", tree.search(tree.root,99));
		
	}
	@Test 
	public void findRootOfDAG() {
		DirectedGraph d = new DirectedGraph(2);
		d.addEdge(0, 1);
		assertEquals("Tree with only 2 nodes", 0, LCA.findRoot(d));
		DirectedGraph dg = new DirectedGraph(5);
		dg.addEdge(0,1);
		dg.addEdge(0,2);
		dg.addEdge(1,2);
		dg.addEdge(2,3);
		dg.addEdge(2,4);
		assertEquals("0 has no indegrees so is the root", 0, LCA.findRoot(dg));
	}
	@Test
	public void DAGDept() {
		
		DirectedGraph dg = new DirectedGraph(4);
		dg.addEdge(0,1);
		assertEquals("DAG has depth of 0 when given root edge", 0, LCA.depth(dg, 0, 0));
		dg.addEdge(0,2);
		dg.addEdge(1,2);
		dg.addEdge(2,3);
		assertEquals("DAG has depth of 2 when given edge 3", 2, LCA.depth(dg, 0, 3));	
	}
	@Test
	public void ancestors() {
		DirectedGraph dg = new DirectedGraph(5);
		dg.addEdge(0,1);
		int[] result = new int[]{0};
		Assert.assertArrayEquals("Ancestors of root is root",  result ,LCA.vertexAncestors(dg, 0, 0).stream().mapToInt(i->i).toArray());
		dg.addEdge(0,2);
		dg.addEdge(1,2);
		dg.addEdge(2,3);
		dg.addEdge(2,4);
		int[] result1 = new int[]{4,2,0,1};
		Assert.assertArrayEquals("Ancestors of 4 are 4,2,0,1",  result1 ,LCA.vertexAncestors(dg, 0, 4).stream().mapToInt(i->i).toArray());
	}
	
	

}