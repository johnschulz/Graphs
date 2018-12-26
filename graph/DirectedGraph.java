package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/**
 * Represents a general unlabeled directed graph whose vertices are denoted by
 * positive integers. Graphs may have self edges.
 *
 * @author John Schulz
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int count = 0;
        for (int[] x : _edges) {
            if (x[1] == v) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int[] edges : _edges) {
            if (edges[1] == v) {
                ret.add(edges[0]);
            }
        }
        return Iteration.iteration(ret);
    }

}
