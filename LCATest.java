import static org.junit.Assert.*;

import org.junit.Test;


public class LCATest {

	 LCA tree = new LCA(); 
	
	@Test
	public void lca () {
		LCA tree = new LCA();
		
		assertSame("null entry", null,tree.lca(tree.root,4,5));
		tree.insert(15);
		assertSame("tree with only root", null,tree.lca(tree.root,4,5));
        tree.insert(15); 
        tree.insert(10); 
        tree.insert(20); 
        tree.insert(13); 
        tree.insert(12); 
        tree.insert(5); 
        tree.insert(14);
        assertSame("tree with only root", 10 ,tree.lca(tree.root,14,5).key);
        	
	}

}
