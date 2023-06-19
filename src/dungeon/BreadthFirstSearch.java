package dungeon;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This is a package private class used for implementing Breadth First Search.
 */

class BreadthFirstSearch {
  static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
    adj.get(i).add(j);
    adj.get(j).add(i);
  }

  static int printShortestDistance(ArrayList<ArrayList<Integer>> adj, int s, int dest, int v) {
    int[] pred = new int[v];
    int[] dist = new int[v];

    if (!breadFirstS(adj, s, dest, v, pred, dist)) {
      return 0;
    }

    LinkedList<Integer> path = new LinkedList<>();
    int crawl = dest;
    path.add(crawl);
    while (pred[crawl] != -1) {
      path.add(pred[crawl]);
      crawl = pred[crawl];
    }

    return dist[dest];
  }

  private static boolean breadFirstS(ArrayList<ArrayList<Integer>> adj, int src,
                                     int dest, int v, int[] pred, int[] dist) {

    boolean[] visited = new boolean[v];

    for (int i = 0; i < v; i++) {
      visited[i] = false;
      dist[i] = Integer.MAX_VALUE;
      pred[i] = -1;
    }

    LinkedList<Integer> queue = new LinkedList<>();
    visited[src] = true;
    dist[src] = 0;
    queue.add(src);

    while (!queue.isEmpty()) {
      int u = queue.remove();
      for (int i = 0; i < adj.get(u).size(); i++) {
        if (!visited[adj.get(u).get(i)]) {
          visited[adj.get(u).get(i)] = true;
          dist[adj.get(u).get(i)] = dist[u] + 1;
          pred[adj.get(u).get(i)] = u;
          queue.add(adj.get(u).get(i));

          if (adj.get(u).get(i) == dest) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
