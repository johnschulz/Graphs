package graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Graph class.
 *
 * @author John Schulz
 */
public class GraphTest {


    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    public GraphObj contruct() {
        GraphObj a = new DirectedGraph();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add(7, 2);
        a.add(7, 1);
        a.add(2, 5);
        a.add(2, 3);
        a.add(2, 4);
        return a;
    }

    public GraphObj contructUndirected() {
        GraphObj a = new UndirectedGraph();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add(7, 2);
        a.add(7, 1);
        a.add(2, 5);
        a.add(2, 3);
        a.add(2, 4);
        return a;
    }

    public GraphObj contruct1() {
        GraphObj a = new DirectedGraph();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add();
        a.add(7, 2);
        a.add(7, 1);
        a.add(2, 5);
        a.add(2, 3);
        a.add(2, 4);
        a.add(7, 5);
        return a;
    }

    @Test
    public void testVerticesSize() {
        GraphObj a = contruct();
        assertEquals(7, a.vertexSize());
    }

    @Test
    public void testMaxVertex() {
        GraphObj a = contruct();
        assertEquals(7, a.maxVertex());
    }

    @Test
    public void testEdgeSize() {
        GraphObj a = contruct();
        assertEquals(5, a.edgeSize());
        a.add();
        assertEquals(5, a.edgeSize());
    }

    @Test
    public void testOutDegree() {
        GraphObj a = contruct();
        assertEquals(2, a.outDegree(7));
        assertEquals(0, a.outDegree(1));
        assertEquals(3, a.outDegree(2));
        assertEquals(0, a.outDegree(5));
        assertEquals(0, a.outDegree(3));
        assertEquals(0, a.outDegree(4));
        assertEquals(0, a.outDegree(6));
    }

    @Test
    public void testContains() {
        GraphObj a = contruct();
        assertTrue(a.contains(7));
        assertTrue(a.contains(1));
        assertTrue(a.contains(2));
        assertTrue(a.contains(3));
        assertTrue(a.contains(4));
        assertTrue(a.contains(5));
        assertTrue(a.contains(6));
        assertFalse(a.contains(8));

    }

    @Test
    public void testContainsEdgeIncluded() {
        GraphObj a = contructUndirected();
        assertTrue(a.contains(7, 1));
        assertTrue(a.contains(1, 7));
        assertTrue(a.contains(2, 7));
        assertTrue(a.contains(2, 5));
        assertTrue(a.contains(2, 3));
        assertTrue(a.contains(2, 4));
        assertTrue(a.contains(4, 2));
        assertTrue(a.contains(3, 2));
        assertTrue(a.contains(5, 2));
        assertTrue(a.contains(7, 2));
        assertFalse(a.contains(4, 3));
        assertFalse(a.contains(7, 3));
        assertFalse(a.contains(3, 5));
    }

    @Test
    public void testAdd() {
        GraphObj a = contruct();
        a.remove(6);
        int x = a.add();
        assertEquals(6, x);
        GraphObj b = new DirectedGraph();
        int y = b.add();
        assertEquals(1, y);
    }

    @Test
    public void testAddWithEdge() {
        GraphObj a = contruct();
        assertEquals(39, a.add(5, 3));
    }

    @Test
    public void testRemove() {
        GraphObj a = contructUndirected();
        a.remove(7);
        assertFalse(a.contains(7));
        assertEquals(0, a.outDegree(1));
        assertEquals(3, a.outDegree(2));
        assertEquals(1, a.outDegree(5));
        assertEquals(1, a.outDegree(3));
        assertEquals(1, a.outDegree(4));
        GraphObj b = contructUndirected();
        b.remove(2);
        assertFalse(b.contains(2));
        assertEquals(1, b.outDegree(7));
        assertEquals(1, b.outDegree(1));
        assertEquals(0, b.outDegree(5));
        assertEquals(0, b.outDegree(3));
        assertEquals(0, b.outDegree(4));
        GraphObj c = contructUndirected();
        c.remove(5);
        assertFalse(c.contains(5));
        assertEquals(2, c.outDegree(7));
        assertEquals(1, c.outDegree(1));
        assertEquals(3, c.outDegree(2));
        assertEquals(1, c.outDegree(3));
        assertEquals(1, c.outDegree(4));
    }

    @Test
    public void testRemoveDirected() {
        GraphObj a = contruct();
        a.remove(7);
        assertFalse(a.contains(7));
        assertEquals(0, a.outDegree(1));
        assertEquals(3, a.outDegree(2));
        assertEquals(0, a.outDegree(5));
        assertEquals(0, a.outDegree(3));
        assertEquals(0, a.outDegree(4));
        GraphObj b = contruct();
        b.remove(2);
        assertFalse(b.contains(2));
        assertEquals(1, b.outDegree(7));
        assertEquals(0, b.outDegree(1));
        assertEquals(0, b.outDegree(5));
        assertEquals(0, b.outDegree(3));
        assertEquals(0, b.outDegree(4));
        GraphObj c = contruct();
        c.remove(5);
        assertFalse(c.contains(5));
        assertEquals(2, c.outDegree(7));
        assertEquals(0, c.outDegree(1));
        assertEquals(2, c.outDegree(2));
        assertEquals(0, c.outDegree(3));
        assertEquals(0, c.outDegree(4));
    }

    @Test
    public void testRemoveEdge() {
        GraphObj a = contructUndirected();
        a.remove(2, 5);
        assertEquals(0, a.outDegree(5));
        assertEquals(3, a.outDegree(2));
        assertEquals(2, a.outDegree(7));
        assertEquals(1, a.outDegree(1));
        assertEquals(1, a.outDegree(3));
        assertEquals(1, a.outDegree(4));
        assertEquals(7, a.vertexSize());
        GraphObj b = contructUndirected();
        b.remove(7, 5);
        Graph c = contructUndirected();
        c.remove(7, 2);
        assertEquals(1, c.outDegree(1));
        assertEquals(1, c.outDegree(7));
        assertEquals(3, c.outDegree(2));
        assertEquals(1, c.outDegree(5));
        assertEquals(1, c.outDegree(4));
        assertEquals(1, c.outDegree(3));
    }

    @Test
    public void testRemoveEdgeDirected() {
        GraphObj a = contruct();
        a.remove(2, 5);
        assertEquals(0, a.outDegree(5));
        assertEquals(2, a.outDegree(2));
        assertEquals(2, a.outDegree(7));
        assertEquals(0, a.outDegree(1));
        assertEquals(0, a.outDegree(3));
        assertEquals(0, a.outDegree(4));
        assertEquals(7, a.vertexSize());
        GraphObj b = contruct();
        b.remove(7, 5);
        Graph c = contruct();
        c.remove(7, 2);
        assertEquals(0, c.outDegree(1));
        assertEquals(1, c.outDegree(7));
        assertEquals(3, c.outDegree(2));
        assertEquals(0, c.outDegree(5));
        assertEquals(0, c.outDegree(4));
        assertEquals(0, c.outDegree(3));
    }

    @Test
    public void testVertices() {
        Graph c = contruct();
        Iteration<Integer> a = c.vertices();
        assertEquals(1, (int) a.next());
        assertEquals(2, (int) a.next());
        assertEquals(3, (int) a.next());
        assertEquals(4, (int) a.next());
        assertEquals(5, (int) a.next());
        assertEquals(6, (int) a.next());
        assertEquals(7, (int) a.next());
        assertFalse(a.hasNext());
        Graph b = new DirectedGraph();
        Iteration<Integer> d = b.vertices();
        assertFalse(d.hasNext());
    }

    @Test
    public void testSuccessors() {
        Graph c = contruct();
        Iteration<Integer> a = c.successors(2);
        assertEquals(3, (int) a.next());
        assertEquals(4, (int) a.next());
        assertEquals(5, (int) a.next());
        assertFalse(a.hasNext());
        Iteration<Integer> b = c.successors(7);
        assertEquals(1, (int) b.next());
        assertEquals(2, (int) b.next());
    }

    @Test
    public void testSuccessorsUndirected() {
        Graph c = contructUndirected();
        Iteration<Integer> a = c.successors(2);
        assertEquals(3, (int) a.next());
        assertEquals(4, (int) a.next());
        assertEquals(5, (int) a.next());
        assertEquals(7, (int) a.next());
        assertFalse(a.hasNext());
        Iteration<Integer> b = c.successors(7);
        assertEquals(1, (int) b.next());
        assertEquals(2, (int) b.next());
    }

    @Test
    public void testEdges() {
        Graph a = contruct();
        a.add(1, 7);
        Iteration<int[]> b = a.edges();
        int[] a1 = {7, 2};
        int[] b1 = b.next();
        assertEquals(a1[0], b1[0]);
        assertEquals(a1[1], b1[1]);
        int[] a3 = {7, 1};
        int[] b3 = b.next();
        assertEquals(a3[0], b3[0]);
        assertEquals(a3[1], b3[1]);
        int[] a5 = {2, 5};
        int[] b5 = b.next();
        assertEquals(a5[0], b5[0]);
        assertEquals(a5[1], b5[1]);
        int[] a7 = {2, 3};
        int[] b7 = b.next();
        assertEquals(a7[0], b7[0]);
        assertEquals(a7[1], b7[1]);
        int[] a9 = {2, 4};
        int[] b9 = b.next();
        assertEquals(a9[0], b9[0]);
        assertEquals(a9[1], b9[1]);
        int[] a10 = {1, 7};
        int[] b10 = b.next();
        assertEquals(a10[0], b10[0]);
        assertEquals(a10[1], b10[1]);
        assertFalse(b.hasNext());
    }

    @Test
    public void testEdgeID() {
        Graph a = contruct();
        assertEquals(47, a.edgeId(7, 2));
        assertEquals(0, a.edgeId(2, 7));
        a.add(2, 7);
        assertEquals(52, a.edgeId(2, 7));
        assertEquals(37, a.edgeId(7, 1));
        assertEquals(0, a.edgeId(1, 7));
        a.add(2, 6);
        assertEquals(42, a.edgeId(2, 6));
    }

    @Test
    public void testEdgeIDUndirected() {
        Graph a = contructUndirected();
        assertEquals(47, a.edgeId(7, 2));
        assertEquals(47, a.edgeId(2, 7));
        assertEquals(37, a.edgeId(7, 1));
        assertEquals(37, a.edgeId(1, 7));
        a.add(2, 6);
        assertEquals(38, a.edgeId(2, 6));
    }

    @Test
    public void testInDegreeDirected() {
        GraphObj a = contruct1();
        assertEquals(0, a.inDegree(7));
        assertEquals(1, a.inDegree(1));
        assertEquals(1, a.inDegree(2));
        assertEquals(1, a.inDegree(3));
        assertEquals(1, a.inDegree(4));
        assertEquals(2, a.inDegree(5));
    }

    @Test
    public void testInDegreeUnDirected() {
        GraphObj a = contructUndirected();
        assertEquals(2, a.inDegree(7));
        assertEquals(1, a.inDegree(1));
        assertEquals(4, a.inDegree(2));
        assertEquals(1, a.inDegree(3));
        assertEquals(1, a.inDegree(4));
        assertEquals(1, a.inDegree(5));
    }

    @Test
    public void testRemoveWithEdges() {
        GraphObj a = contructUndirected();
        a.remove(7);
        Iteration<int[]> b = a.edges();
        int[] a1 = {2, 5};
        assertArrayEquals(a1, b.next());
        int[] a2 = {2, 3};
        assertArrayEquals(a2, b.next());
        int[] a3 = {2, 4};
        assertArrayEquals(a3, b.next());
        assertFalse(b.hasNext());
        Graph c = contruct();
        c.remove(1, 7);
        Iteration<int[]> c1 = c.edges();
        int[] b12 = {7, 2};
        assertArrayEquals(b12, c1.next());
        int[] b123 = {7, 1};
        assertArrayEquals(b123, c1.next());
        int[] b1 = {2, 5};
        assertArrayEquals(b1, c1.next());
        int[] b2 = {2, 3};
        assertArrayEquals(b2, c1.next());
        int[] b3 = {2, 4};
        assertArrayEquals(b3, c1.next());
        assertFalse(c1.hasNext());
        Graph c2 = contructUndirected();
        c.remove(1, 7);
        Iteration<int[]> c3 = c.edges();
        int[] d12 = {7, 2};
        assertArrayEquals(d12, c3.next());
        int[] d123 = {7, 1};
        assertArrayEquals(d123, c3.next());
        int[] d1 = {2, 5};
        assertArrayEquals(d1, c3.next());
        int[] d2 = {2, 3};
        assertArrayEquals(d2, c3.next());
        int[] d3 = {2, 4};
        assertArrayEquals(d3, c3.next());
        assertFalse(c1.hasNext());
    }

    @Test
    public void testPreceddessors() {
        GraphObj a = contruct();
        Iteration b = a.predecessors(1);
        assertEquals(7, b.next());
        assertFalse(b.hasNext());
        Iteration b1 = a.predecessors(7);
        assertFalse(b1.hasNext());
        a.add(7, 5);
        Iteration b2 = a.predecessors(5);
        assertEquals(2, b2.next());
        assertEquals(7, b2.next());
        assertFalse(b2.hasNext());
    }
}
