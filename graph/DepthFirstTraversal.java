package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Implements a depth-first traversal of a graph.  Generally, the
 * client will extend this class, overriding the visit and
 * postVisit methods, as desired (by default, they do nothing).
 *
 * @author John Schulz
 */
public class DepthFirstTraversal extends Traversal {

    /**
     * Keep track of visited and posted visited vertices in my testing.
     */
    protected Iterator<Integer> markedPost;

    /**
     * An accessor method.
     * @return the markedPost Iterator.
     */
    protected Iterator<Integer> getMarkedPost() {
        return markedPost;
    }

    /**
     * A depth-first Traversal of G.
     */
    protected DepthFirstTraversal(Graph G) {
        super(G, new MyAD());
    }

    @Override
    protected boolean visit(int v) {
        return super.visit(v);
    }

    @Override
    protected boolean postVisit(int v) {
        return super.postVisit(v);
    }


    @Override
    protected boolean reverseSuccessors(int v) {
        return true;
    }

    @Override
    protected void processSuccessors(int u) {
        Iteration<Integer> iter = getG().successors(u);
        ArrayList<Integer> reverse = new ArrayList<>();
        while (iter.hasNext()) {
            reverse.add(iter.next());
        }
        Collections.reverse(reverse);
        iter = Iteration.iteration(reverse);
        for (int v : iter) {
            if (processSuccessor(u, v)) {
                if (!marked(v)) {
                    if (getFringe().contains(v)) {
                        _fringe.remove(v);
                    }
                    _fringe.add(v);
                }
            }
        }
    }

    /**
     * A class that extends Arrray deque and allows us to
     * remove the last element instead of the head.
     * Follows the same implementation for peek.
     */
    private static class MyAD extends ArrayDeque<Integer> {
        @Override
        public Integer remove() {
            return removeLast();
        }

        @Override
        public Integer peek() {
            return peekLast();
        }
    }
}
