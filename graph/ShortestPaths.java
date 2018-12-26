package graph;

/* See restrictions in Graph.java. */



import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.AbstractQueue;
import java.util.Iterator;

/**
 * The shortest paths through an edge-weighted graph.
 * By overrriding methods getWeight, setWeight, getPredecessor, and
 * setPredecessor, the client can determine how to represent the weighting
 * and the search results.  By overriding estimatedDistance, clients
 * can search for paths to specific destinations using A* search.
 *
 * @author John Schulz
 */
public abstract class ShortestPaths {

    /**
     * The shortest paths in G from SOURCE.
     */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /**
     * A shortest path in G from SOURCE to DEST.
     */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _traversal = new OurTraversal(_G);
    }

    /**
     * Initialize the shortest paths.  Must be called before using
     * getWeight, getPredecessor, and pathTo.
     */
    public void setPaths() {
        ArrayList<Integer> vertices = new ArrayList<>();
        Iteration<Integer> ourVertices = _G.vertices();
        while (ourVertices.hasNext()) {
            int nextVertice = ourVertices.next();
            vertices.add(nextVertice);
            if (nextVertice == getSource()) {
                continue;
            } else {
                setWeight(nextVertice, Double.MAX_VALUE);
            }
        }
        setWeight(getSource(), 0.0);
        _traversal.traverse(vertices);

    }

    /**
     * Returns the starting vertex.
     */
    public int getSource() {
        return _source;
    }

    /**
     * Returns the target vertex, or 0 if there is none.
     */
    public int getDest() {
        return _dest;
    }

    /**
     * Returns the current weight of vertex V in the graph.  If V is
     * not in the graph, returns positive infinity.
     */
    public abstract double getWeight(int v);

    /**
     * Set getWeight(V) to W. Assumes V is in the graph.
     */
    protected abstract void setWeight(int v, double w);

    /**
     * Returns the current predecessor vertex of vertex V in the graph, or 0 if
     * V is not in the graph or has no predecessor.
     */
    public abstract int getPredecessor(int v);

    /**
     * Set getPredecessor(V) to U.
     */
    protected abstract void setPredecessor(int v, int u);

    /**
     * Returns an estimated heuristic weight of the shortest path from vertex
     * V to the destination vertex (if any).  This is assumed to be less
     * than the actual weight, and is 0 by default.
     */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /**
     * Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     * not in the graph, returns positive infinity.
     */
    protected abstract double getWeight(int u, int v);

    /**
     * Returns a list of vertices starting at _source and ending
     * at V that represents a shortest path to V.  Invalid if there is a
     * destination vertex other than V.
     */
    public List<Integer> pathTo(int v) {
        List<Integer> a = new ArrayList<>();
        int source = getSource();
        while (getPredecessor(v) != source) {
            if (getPredecessor(v) == 0) {
                break;
            } else {
                a.add(v);
                v = getPredecessor(v);
            }
        }
        a.add(v);
        a.add(source);
        Collections.reverse(a);
        return a;

    }

    /**
     * Returns a list of vertices starting at the source and ending at the
     * destination vertex. Invalid if the destination is not specified.
     */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }


    /**
     * The graph being searched.
     */
    protected final Graph _G;
    /**
     * The starting vertex.
     */
    private final int _source;
    /**
     * The target vertex.
     */
    private final int _dest;


    /**
     * The traversal of our shortest paths.
     */
    protected Traversal _traversal;

    /**
     * Accessor method.
     * @return the Traversal for our shortest paths.
     */
    protected Traversal getTraversal() {
        return _traversal;
    }


    /**
     * The comparator to be used to implement our treeset.
     * Takes in account weights and estimatedDistance of each int.
     */
    private class PQcompare implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            double dist1 = getWeight(o1) + estimatedDistance(o1);
            double dist2 = getWeight(o2) + estimatedDistance(o2);
            if (o1 == o2) {
                return 0;
            } else if (dist1 > dist2) {
                return 1;
            } else if (dist1 < dist2) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    /**
     * The tree class to be used to traverse our path.
     * Extends an abstractQueue with abstracted methods.
     */
    class OurTree extends AbstractQueue<Integer> {
        /**
         * Constructor for our tree class using
         * the priority queue comparator above.
         */
        OurTree() {
            _tree = new TreeSet<>(new PQcompare());
        }

        @Override
        public Iterator<Integer> iterator() {
            return _tree.iterator();
        }

        @Override
        public int size() {
            return _tree.size();
        }

        @Override
        public boolean offer(Integer integer) {
            return _tree.add(integer);
        }

        @Override
        public Integer poll() {
            return _tree.pollFirst();
        }

        @Override
        public Integer peek() {
            return _tree.first();
        }

        /**
         * The tree for this specific object.
         */
        private TreeSet<Integer> _tree;
    }

    /**
     * The traversal for our shortestPaths.
     */
    class OurTraversal extends Traversal {

        /**
         * Constructor for our traversal. Our Tree is
         * automatically passed into the super
         * contructor.
         * @param G is the graph to set up our traversal.
         */
        protected OurTraversal(Graph G) {
            super(G, new OurTree());
        }

        @Override
        protected void processSuccessors(int u) {
            for (int edge : _G.successors(u)) {
                double count = getWeight(u)
                         + getWeight(u, edge);
                if (count < getWeight(edge)) {
                    setWeight(edge, count);
                    setPredecessor(edge, u);
                    _fringe.remove(edge);
                    _fringe.add(edge);
                }
            }
        }
        @Override
        protected boolean visit(int u) {
            if (u == _dest) {
                return false;
            }
            return true;
        }
    }

}
