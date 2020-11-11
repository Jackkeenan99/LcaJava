import static org.junit.Assert.*;

import org.junit.Test;

public class DirectedGraphTest {

	@Test
	public void isVertexValidTest() {
		DirectedGraph g = new DirectedGraph(5);
		assertFalse("6 is greater than V",g.isVertexValid(6));
		assertTrue("4 is inbounds", g.isVertexValid(4));
		assertFalse("5 is equal to V", g.isVertexValid(5));
		assertFalse("-1 is less than 0", g.isVertexValid(-1));
		
	}
	@Test
	public void indegreeTest() {
		DirectedGraph dg = new DirectedGraph(5);
		dg.addEdge(0,1);
		dg.addEdge(0,2);
		dg.addEdge(1,2);
		dg.addEdge(2,3);
		dg.addEdge(2,4);
		assertEquals("root has no indegree",0,dg.indegree(0));
		assertEquals("4 only has one indegree",1,dg.indegree(4));
		assertEquals("2 has 2 indgrees",2,dg.indegree(2));
		
	}
	@Test
	public void reverseTest() {
		DirectedGraph dg = new DirectedGraph(5);
		dg.addEdge(0,1);
		dg.addEdge(0,2);
		dg.addEdge(1,2);
		dg.addEdge(2,3);
		dg.addEdge(2,4);
		DirectedGraph reverse = dg.reverse();
		assertEquals("changed from 2->3 to 3->2", reverse.adj.get(3).contains(2),true);
		assertEquals("changed from 2->4 to 4->2",reverse.adj.get(4).contains(2),true);
		assertNotEquals("0 in no longer the root",0,reverse.indegree(0));
		
	}
	@Test
	public void addTest() {
		DirectedGraph dg = new DirectedGraph(5);
		dg.addEdge(0,1);
		assertEquals("(0,1) added",dg.adj.get(0).contains(1),true);
		dg.addEdge(0,2);
		assertEquals("(0,2) added",dg.adj.get(0).contains(2),true);
		dg.addEdge(1,2);
		assertEquals("(1,2) added",dg.adj.get(1).contains(2),true);
		assertEquals("(4,2) not added to tree",dg.adj.get(4).contains(2),false);
			
	}
	
	
	
	
	
	
	

}
