package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
import java.util.Collections;

/**
 * A partial implementation of Graph containing elements common to
 * directed and undirected graphs.
 *
 * @author John Schulz
 */
abstract class GraphObj extends Graph {
    /**
     * The vertices represented in our graphObj.
     */
    protected ArrayList<Integer> _vertices;

    /**
     * Returns the vertices ArrayList.
     */
    protected ArrayList<Integer> getVertices() {
        return _vertices;
    }

    /**
     * The edges of our graphObj represented
     * as 2 element arrays.
     */
    protected ArrayList<int[]> _edges;

    /**
     * Returns the edges of our graphObj.
     */
    protected ArrayList<int[]> getEdges() {
        return _edges;
    }
    /**
     * A new, empty Graph.
     */
    GraphObj() {
        _vertices = new ArrayList<>();
        _edges = new ArrayList<>();

    }

    @Override
    public int vertexSize() {
        return _vertices.size();
    }

    @Override
    public int maxVertex() {

        if (_vertices.size() == 0) {
            return 0;
        }
        int m = Integer.MIN_VALUE;
        for (Integer x : _vertices) {
            if (x > m) {
                m = x;
            }
        }
        return m;
    }

    @Override
    public int edgeSize() {
        return _edges.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!this.contains(v)) {
            return 0;
        } else {
            int count = 0;
            for (int[] x : _edges) {
                if (x[0] == v || (!isDirected() && x[1] == v)) {
                    count += 1;
                }
            }
            return count;
        }
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        for (int x : _vertices) {
            if (x == u) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(int u, int v) {

        if (contains(u) && contains(v)) {
            for (int[] edge : _edges) {
                if (edge[0] == u && edge[1] == v
                        || (!isDirected() && edge[1] == u && edge[0] == v)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public int add() {
        int check = 1;
        while (contains(check)) {
            check += 1;
        }
        _vertices.add(check);
        return check;
    }

    @Override
    public int add(int u, int v) {
        if (contains(u, v)) {
            return edgeId(u, v);
        }
        checkMyVertex(u);
        checkMyVertex(v);
        if (isDirected()) {
            int[] addd = {u, v};
            _edges.add(addd);
            return (((u + v) * (u + v + 1)) / 2 + v);
        }
        if (u > v) {
            int[] addd = {v, u};
            _edges.add(addd);
            return (((u + v) * (u + v + 1)) / 2 + v);
        }
        int[] addd = {u, v};
        _edges.add(addd);
        return (((v + u) * (v + u + 1)) / 2 + u);
    }

    @Override
    public void remove(int v) {
        if (!contains(v)) {
            return;
        }

        ArrayList<int[]> needToBeRemovedEdges = new ArrayList<>();
        for (int[] x : _edges) {
            if (x[0] == v || x[1] == v) {
                needToBeRemovedEdges.add(x);
            }
        }
        for (int[] x : needToBeRemovedEdges) {
            _edges.remove(x);
        }
        _vertices.remove((Integer) v);

    }

    @Override
    public void remove(int u, int v) {
        if (!contains(u, v)) {
            return;
        } else {
            int[] rem = {u, v};
            if (!isDirected() && u > v) {
                int[] remm = {v, u};
                rem = remm;
            }
            ArrayList<int[]> needToBeRemovedEdges = new ArrayList<>();
            for (int[] x : _edges) {
                if ((x[0] == rem[0] && x[1] == rem[1])) {
                    needToBeRemovedEdges.add(x);
                }
            }
            for (int[] x : needToBeRemovedEdges) {
                _edges.remove(x);
            }


        }


    }

    @Override
    public Iteration<Integer> vertices() {
        ArrayList<Integer> a = new ArrayList<>(_vertices);
        ArrayList<Integer> ret = new ArrayList<>();
        Collections.sort(a);
        return Iteration.iteration(a);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> succ = new ArrayList<>();
        for (int[] edges : _edges) {
            if (edges[0] == v) {
                succ.add(edges[1]);
            } else if (!isDirected() && edges[1] == v) {
                succ.add(edges[0]);
            } else {
                continue;
            }
        }
        Collections.sort(succ);
        return Iteration.iteration(succ);

    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {

        return Iteration.iteration(_edges);
    }


    @Override
    protected int edgeId(int u, int v) {
        if (!contains(u, v)) {
            return 0;
        }
        if (isDirected()) {
            return (((u + v) * (u + v + 1)) / 2 + v);
        }
        if (u > v) {
            return (((u + v) * (u + v + 1)) / 2 + v);
        }
        return (((v + u) * (v + u + 1)) / 2 + u);
    }


}
