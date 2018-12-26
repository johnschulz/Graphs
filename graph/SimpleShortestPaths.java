package graph;

/* See restrictions in Graph.java. */

/**
 * A partial implementation of ShortestPaths that contains the weights of
 * the vertices and the predecessor edges.   The client needs to
 * supply only the two-argument getWeight method.
 *
 * @author John Schulz
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /**
     * The shortest paths in G from SOURCE.
     */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /**
     * A shortest path in G from SOURCE to DEST.
     */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
        _weights = new double[G.vertexSize() + 1];
        _predecessors = new int[G.vertexSize() + 1];
    }

    /**
     * Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     * not in the graph, returns positive infinity.
     */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        if (v < 0 || v > _weights.length) {
            return Double.MAX_VALUE;
        }
        return _weights[v];
    }

    @Override
    protected void setWeight(int v, double w) {

        _weights[v] = w;
    }

    @Override
    public int getPredecessor(int v) {
        if (v < 0 || v > _predecessors.length) {
            return 0;
        }
        return _predecessors[v];
    }

    @Override
    protected void setPredecessor(int v, int u) {
        _predecessors[v] = u;
    }

    /**
     * The weights at each of our vertices.
     * @return the weights.
     */
    public double[] getWeights() {
        return _weights;
    }

    /**
     * The weights of each of our vertices.
     */
    protected  double[] _weights;

    /**
     * The previous vertices of each vertex.
     */
    protected int[] _predecessors;

}
