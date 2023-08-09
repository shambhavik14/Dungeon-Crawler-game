package dungeon;

import java.util.Objects;

/**
 * This class is represented by the source and destination of a particular edge.
 */

public class Edge {

  int source;
  int destination;

  Edge(int source, int destination) {
    this.source = source;
    this.destination = destination;
  }

  int getSource() {
    return source;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)  {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Edge edge = (Edge) o;
    return source == edge.source && destination == edge.destination
        || destination == edge.source && source == edge.destination;
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, destination);
  }


  int getDestination() {
    return destination;
  }
}


