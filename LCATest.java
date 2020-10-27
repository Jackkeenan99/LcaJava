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
	public void insert() {
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

}
