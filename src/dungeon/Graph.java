package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Kruskal algorithm and gives back minimum spanning tree.
 */

public class Graph {

  private final List<Edge> leftOverEdges;
  private final List<Edge> maxEdges;
  RandomInteger random = new TrueRandom();
  int interconnectivity;
  int vertices;


  class Subset {
    int parent;
    int rank;
  }

  /**
   * This is the constructor of the Graph class.
   *
   * @param vertices          vertices
   * @param e                 size
   * @param maxEdges          Arraylist of the potential edges
   * @param interconnectivity interconnectivity
   */
  public Graph(int height, int width, int vertices, int e,
               List<Edge> maxEdges, int interconnectivity, RandomInteger random) {
    this.vertices = vertices;
    this.interconnectivity = interconnectivity;
    this.maxEdges = maxEdges;
    e = maxEdges.size();
    leftOverEdges = new ArrayList<>();
    this.random = random;
  }


  int find(Subset[] subsets, int i) {
    if (subsets[i].parent != i) {
      subsets[i].parent
          = find(subsets, subsets[i].parent);
    }

    return subsets[i].parent;
  }

  void union(Subset[] subsets, int x, int y) {
    int xroot = find(subsets, x);
    int yroot = find(subsets, y);

    if (subsets[xroot].rank
        < subsets[yroot].rank) {
      subsets[xroot].parent = yroot;
    } else if (subsets[xroot].rank
        > subsets[yroot].rank) {
      subsets[yroot].parent = xroot;
    } else {
      subsets[yroot].parent = xroot;
      subsets[xroot].rank++;
    }
  }

  /**
   * Implements the Kruskal algorithm.
   *
   * @return list of edges with minimum spanning tree
   */

  public List<Edge> kruskalMinSpT() {

    List<Edge> result = new ArrayList<>();

    int i = 0;

    Subset[] subsets = new Subset[vertices];
    for (i = 0; i < vertices; ++i) {
      subsets[i] = new Subset();
    }


    for (int v = 0; v < vertices; ++v) {
      subsets[v].parent = v;
      subsets[v].rank = 0;
    }
    i = 0;
    int e = 0;
    while (e < vertices - 1) {
      Edge next_edge = maxEdges.get(i++);

      int x = find(subsets, next_edge.getSource());
      int y = find(subsets, next_edge.getDestination());

      if (x != y) {
        result.add(next_edge);
        union(subsets, x, y);
        e++;
      } else {
        if (!(result.contains(next_edge))) {
          leftOverEdges.add(next_edge);
        }
      }
    }
    if (interconnectivity > 0) {
      List<Edge> edge = new ArrayList<>();
      for (i = 0; i < interconnectivity; i++) {
        int l = random.nextInt(leftOverEdges.size());
        Edge ed = leftOverEdges.get(l);
        if (!edge.contains(ed)) {
          edge.add(ed);
          result.add(ed);
        }
      }
    }

    return result;
  }
}
