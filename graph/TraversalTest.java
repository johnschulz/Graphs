package graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class TraversalTest {
    public Graph contruct() {
        Graph a = new DirectedGraph();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add(1, 2);
        a.add(2, 4);
        a.add(4, 3);
        a.add(4, 5);
        a.add(1, 5);
        a.add(1, 6);
        a.add(6, 5);
        return a;
    }

    @Test
    public void testBFSTraversalNormalSuccessors() {
        Graph a = contruct();
        Traversal t = new BreadthFirstTraversal(a) {
            @Override
            protected boolean visit(int v) {
                _markedP.add(v);
                return true;
            }
        };
        t.traverse(1);
        Iterator<Integer> marks = Iteration.iteration(t._markedP);
        assertEquals(1, (int) marks.next());
        assertEquals(2, (int) marks.next());
        assertEquals(5, (int) marks.next());
        assertEquals(6, (int) marks.next());
        assertEquals(4, (int) marks.next());
        assertEquals(3, (int) marks.next());
    }

    @Test
    public void testDFSPostOrder() {
        ArrayList markedPost = new ArrayList();
        Graph a = contruct();
        Traversal t = new DepthFirstTraversal(a) {
            @Override
            protected boolean shouldPostVisit(int v) {
                return true;
            }

            @Override
            protected boolean postVisit(int v) {
                _markedP.add(v);
                return true;
            }
        };
        t.traverse(1);
        Iteration<Integer> d = Iteration.iteration(t._markedP);
        assertEquals(3, (int) d.next());
        assertEquals(5, (int) d.next());
        assertEquals(4, (int) d.next());
        assertEquals(2, (int) d.next());
        assertEquals(6, (int) d.next());
        assertEquals(1, (int) d.next());
    }

    @Test
    public void testDFSPreOrder() {
        ArrayList markedPost = new ArrayList();
        Graph a = contruct();
        Traversal t = new DepthFirstTraversal(a) {
            @Override
            protected boolean visit(int v) {
                _markedP.add(v);
                return true;
            }
        };
        t.traverse(1);
        Iteration<Integer> d = Iteration.iteration(t._markedP);
        assertEquals(1, (int) d.next());
        assertEquals(2, (int) d.next());
        assertEquals(4, (int) d.next());
        assertEquals(3, (int) d.next());
        assertEquals(5, (int) d.next());
        assertEquals(6, (int) d.next());
    }

}
