package dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Dungeon class implements DungeonModel interface. A dungeon is a represented by height, width,
 * level of interconnectivity, and if the dungeon is wrapping or not.
 * A dungeon has caves and tunnels which are connected
 * to each other. A player moves in the dungeon from starting point, collects treasure and
 * reaches the destination.
 */

public class Dungeon implements DungeonModel {
  private int height;
  private int width;
  private int otyughNumber;
  private int percent;
  private List<Edge> edgesList1;
  private List<Edge> edgesList;
  private int interconnectivity;
  private boolean isWrapping;
  private boolean gameFinish;
  private int diamondCount;
  private int sapphireCount;
  private int rubiesCount;
  private int arrowCount;
  private boolean pit;
  Location start;
  Location destination;
  Player player;
  Weapon arrow;
  RandomInteger random = new TrueRandom();
  List<Edge> result;
  Location[][] location;

  /**
   * This is a constructor for the dungeon.
   *
   * @param height            height of the dungeon
   * @param width             width of the dungeon
   * @param interconnectivity the degree of interconnectivity of the dungeon.
   * @param isWrapping        wrapping dungeon or non wrapping
   */
  public Dungeon(int height, int width, int interconnectivity, boolean isWrapping,
                 int otyughNumber, int percent,
                 RandomInteger random) throws IllegalArgumentException {
    if (height < 4 || width < 4) {
      throw new IllegalArgumentException("Height or width less than 4");
    }
    if (otyughNumber < 0 || percent < 0 || interconnectivity < 0) {
      throw new IllegalArgumentException("Enter valid value");
    }

    this.height = height;
    this.width = width;
    this.interconnectivity = interconnectivity;
    this.isWrapping = isWrapping;
    this.otyughNumber = otyughNumber;
    this.percent = percent;
    this.diamondCount = 0;
    this.rubiesCount = 0;
    this.sapphireCount = 0;
    this.arrowCount = 3;
    edgesList = new ArrayList<>();
    edgesList1 = new ArrayList<>();
    this.location = new Cave[this.height][this.width];
    this.player = new GamePlayer();
    this.arrow = new Arrows();
    this.random = random;
    createDungeon();
    tunnel();
    getStartDestination();
    getPlayerStartLocation();
    addTreasure();
    addArrows();
    addMonster();
    addPit();
    indicator();
    setStrongSmell();
  }

  /**
   * Default constructor.
   */
  public Dungeon() {
    //This is the default constructor.
  }

  /**
   * This is a copy constructor.
   * @param d dungeon object
   */
  public Dungeon(Dungeon d) {
    this.height = d.height;
    this.width = d.width;
    this.otyughNumber = d.otyughNumber;
    this.percent = d.percent;
    this.interconnectivity = d.interconnectivity;
    this.isWrapping = d.isWrapping;
    this.gameFinish = d.gameFinish;
    this.diamondCount = d.diamondCount;
    this.sapphireCount = d.sapphireCount;
    this.rubiesCount = d.rubiesCount;
    this.arrowCount = d.arrowCount;
    this.start = d.start;
    this.destination = d.destination;
    this.player = d.player;
    this.arrow = d.arrow;
    this.random = d.random;
    this.result = d.result;
    this.location = d.location;
    this.edgesList = d.edgesList;
    this.edgesList1 = d.edgesList1;
  }

  private int getVertices() {
    return height * width;
  }


  @Override
  public List<Edge> getEdgesList() {
    for (int i = 0; i < getVertices(); i++) {
      int north = i - width;
      int east = i + 1;
      int west = i - 1;
      if (north >= 0) {
        Edge ed1 = new Edge(i, north);
        if (!(edgesList.contains(ed1))) {
          edgesList.add(ed1);
        }
      }
      if (east % width != 0) {
        Edge ed2 = new Edge(i, east);
        if (!(edgesList.contains(ed2))) {
          edgesList.add(ed2);
        }
      }
      if (west >= 0 && i % width != 0) {
        Edge ed3 = new Edge(i, west);
        if (!(edgesList.contains(ed3))) {
          edgesList.add(ed3);
        }
      }
      int south = i + width;
      if (south < height * width) {
        Edge ed4 = new Edge(i, south);
        if (!(edgesList.contains(ed4))) {
          edgesList.add(ed4);
        }
      }
    }
    return edgesList;
  }

  @Override
  public List<Edge> getWrappingEdges() {
    for (int i = 0; i < getVertices(); i++) {
      int north = i - width;
      int east = i + 1;

      int west = i - 1;
      int ns = height * width - width;
      int ew = width - 1;
      int north1 = i + ns;
      int south1 = i - ns;
      int west1 = i + ew;
      int east1 = i - ew;
      Edge ed1;
      Edge ed2;
      Edge ed3;
      Edge ed4;
      Edge ed5;
      Edge ed6;
      Edge ed7;
      Edge ed8;
      if (north >= 0) {
        ed1 = new Edge(i, north);
        if (!(edgesList1.contains(ed1))) {
          edgesList1.add(ed1);
        }
      }
      if (north < 0) {
        ed5 = new Edge(i, north1);
        if (!(edgesList1.contains(ed5))) {
          edgesList1.add(ed5);
        }
      }

      if (east % width != 0) {
        ed2 = new Edge(i, east);
        if (!(edgesList1.contains(ed2))) {
          edgesList1.add(ed2);
        }
      }
      if (east % width == 0) {
        ed6 = new Edge(i, east1);
        if (!(edgesList1.contains(ed6))) {
          edgesList1.add(ed6);
        }
      }

      if (i % width != 0) {
        ed3 = new Edge(i, west);
        if (!(edgesList1.contains(ed3))) {
          edgesList1.add(ed3);
        }
      }
      if (i % width == 0) {
        ed7 = new Edge(i, west1);
        if (!(edgesList1.contains(ed7))) {
          edgesList1.add(ed7);
        }
      }
      int south = i + width;

      if (south < height * width) {
        ed4 = new Edge(i, south);
        if (!(edgesList1.contains(ed4))) {
          edgesList1.add(ed4);
        }
      }
      if (south > height * width) {
        ed8 = new Edge(i, south1);
        if (!(edgesList1.contains(ed8))) {
          edgesList1.add(ed8);
        }
      }
    }

    return edgesList1;
  }


  @Override
  public List<Edge> getResultEdge() {

    if (isWrapping) {
      Graph graph1 = new Graph(height, width, getVertices(), getWrappingEdges().size(),
          getWrappingEdges(), interconnectivity, random);
      result = graph1.kruskalMinSpT();
    } else {
      Graph graph = new Graph(height, width, getVertices(), getEdgesList().size(),
          getEdgesList(), interconnectivity, random);
      result = graph.kruskalMinSpT();
    }
    return result;

  }

  private void createDungeon() {

    int counter = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        location[i][j] = new Cave(counter);
        counter++;
      }
    }
    getResultEdge();
    for (Edge edge : result) {

      int src = Math.min(edge.getSource(), edge.getDestination());
      int dest = Math.max(edge.getSource(), edge.getDestination());
      int srcRow = (int) Math.floor(src / width);
      int srcCol = (int) Math.floor(src % width);
      int desRow = (int) Math.floor(dest / width);
      int desCol = (int) Math.floor(dest % width);

      if (Math.abs(dest - src) == 1) {
        location[srcRow][srcCol].setEast(true);
        location[desRow][desCol].setWest(true);
      }
      if ((Math.abs(dest - src) == width)) {
        location[srcRow][srcCol].setSouth(true);
        location[desRow][desCol].setNorth(true);
      }

      if (isWrapping) {
        if ((Math.abs(dest - src) == width - 1)) {
          location[srcRow][srcCol].setWest(true);
          location[desRow][desCol].setEast(true);
        }
        if ((Math.abs(dest - src) == height * width - width)) {
          location[srcRow][srcCol].setNorth(true);
          location[desRow][desCol].setSouth(true);
        }
      }
    }
  }

  @Override
  public int getStartDestination() {

    BreadthFirstSearch bfs = new BreadthFirstSearch();
    ArrayList<ArrayList<Integer>> adj =
        new ArrayList<ArrayList<Integer>>(getVertices());
    for (int j = 0; j < getVertices(); j++) {
      adj.add(new ArrayList<>());
    }

    for (Edge edge : result) {
      BreadthFirstSearch.addEdge(adj, edge.getSource(), edge.getDestination());
    }


    int distance = 0;
    while (distance < 5) {
      int randomint1 = random.nextInt(width);
      int randomint2 = random.nextInt(height);
      int randomint3 = random.nextInt(width);
      int randomint4 = random.nextInt(height);

      if (!location[randomint2][randomint1].isTunnel()
          && !location[randomint4][randomint3].isTunnel()) {

        start = location[randomint2][randomint1];
        destination = location[randomint4][randomint3];

        int source = start.getId();
        int dest = destination.getId();

        distance = BreadthFirstSearch.printShortestDistance(adj, source, dest, getVertices());

      }
    }
    return distance;
  }

  @Override
  public Location getStart() {
    return start;
  }

  @Override
  public Location getDestination() {
    return destination;
  }

  @Override
  public void tunnel() {

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int counter = 0;
        if (location[i][j].isNorth()) {
          counter++;
        }
        if (location[i][j].isSouth()) {
          counter++;
        }
        if (location[i][j].isEast()) {
          counter++;
        }
        if (location[i][j].isWest()) {
          counter++;
        }
        if (counter == 2) {
          location[i][j].setTunnel(true);
        }
      }
    }
  }


  private int addTreasure() {
    int caveCounter = caveCount();
    int cavesTreasure = (percent * caveCounter / 100);
    for (int i = 0; i < cavesTreasure; i++) {
      int randomInt1 = random.nextInt(height);
      int randomInt2 = random.nextInt(width);
      if (!location[randomInt1][randomInt2].isTunnel()) {
        int pick = random.nextInt(Treasure.values().length);
        location[randomInt1][randomInt2].setTreasure(Treasure.values()[pick]);
        location[randomInt1][randomInt2].setChest(true);
      } else if (location[randomInt1][randomInt2].isTunnel()) {
        i = i - 1;
      }
    }
    return cavesTreasure;
  }

  private int addArrows() {
    int caveCounter = caveCount();
    ArrayList<Location> visited = new ArrayList<>();
    int cavesTreasure = (percent * caveCounter / 100);
    for (int i = 0; i < cavesTreasure; i++) {
      int randomInt3 = random.nextInt(height);
      int randomInt4 = random.nextInt(width);
      if (!visited.contains(location[randomInt3][randomInt4])) {
        visited.add(location[randomInt3][randomInt4]);
        location[randomInt3][randomInt4].setArrow(true);
      } else {
        i = i - 1;
      }
    }
    return cavesTreasure;
  }


  private int caveCount() {
    int caveCounter = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (!location[i][j].isTunnel()) {
          caveCounter++;
        }
      }
    }
    return caveCounter;
  }

  private int addMonster() throws IllegalArgumentException {
    int caveCounter = caveCount();
    if (otyughNumber > caveCounter) {
      throw new IllegalArgumentException();
    }
    ArrayList<Location> visited = new ArrayList<>();
    getDestination().setOtyugh(true);
    getDestination().addMonster();
    getDestination().getMonster().isAlive();
    for (int i = 1; i < otyughNumber; i++) {
      int random1 = random.nextInt(height);
      int random2 = random.nextInt(width);
      if (!visited.contains(location[random1][random2])
          && !location[random1][random2].isTunnel()) {
        if (location[random1][random2].equals(getStart())) {
          location[random1][random2].setOtyugh(false);
          i = i - 1;
        } else if (location[random1][random2].equals(getDestination())) {
          i = i - 1;
        } else {
          visited.add(location[random1][random2]);
          location[random1][random2].setOtyugh(true);
          location[random1][random2].addMonster();
          location[random1][random2].getMonster().isAlive();

        }
      } else {
        i = i - 1;
      }
    }
    return otyughNumber;
  }

  @Override
  public int setDirection(String direction) throws IllegalArgumentException {
    if (gameFinish) {
      throw new IllegalArgumentException();
    }
    int h = height - 1;
    int w = width - 1;

    int id = player.getPlayerLocation().getId();
    int i = (int) Math.floor(id / width);
    int j = (int) Math.floor(id % width);

    if (isWrapping) {
      if (direction.equals("East") || direction.equals("east")) {
        if (!location[i][j].isEast()) {
          throw new IllegalStateException("There is no path here");
        }
        if (j + 1 >= width) {
          player.setPlayerLocation(location[i][j - w]);
        } else {
          player.setPlayerLocation(location[i][j + 1]);

        }
      }
      if (direction.equals("West") || direction.equals("west")) {
        if (!location[i][j].isWest()) {
          throw new IllegalStateException("There is no path here");
        }
        if (j - 1 < 0) {
          player.setPlayerLocation(location[i][j + w]);

        } else {
          player.setPlayerLocation(location[i][j - 1]);
        }
      }
      if (direction.equals("North") || direction.equals("north")) {
        if (!location[i][j].isNorth()) {
          throw new IllegalStateException("There is no path here");
        }
        if (i - 1 < 0) {
          player.setPlayerLocation(location[i + h][j]);

        } else {
          player.setPlayerLocation(location[i - 1][j]);
        }
      }
      if (direction.equals("South") || direction.equals("south")) {
        if (!location[i][j].isSouth()) {
          throw new IllegalStateException("There is no path here");
        }
        if (i + 1 >= height) {
          player.setPlayerLocation(location[i - h][j]);

        } else {
          player.setPlayerLocation(location[i + 1][j]);
        }
      }
    }
    if (!isWrapping) {
      if (direction.equals("East") || direction.equals("east")) {
        if (!location[i][j].isEast()) {
          throw new IllegalStateException("There is no path here");
        }
        player.setPlayerLocation(location[i][j + 1]);
      }
      if (direction.equals("West") || direction.equals("west")) {
        if (!location[i][j].isWest()) {
          throw new IllegalStateException("There is no path here");
        }
        player.setPlayerLocation(location[i][j - 1]);
      }
      if (direction.equals("North") || direction.equals("north")) {
        if (!location[i][j].isNorth()) {
          throw new IllegalStateException("There is no path here");
        }
        player.setPlayerLocation(location[i - 1][j]);
      }
      if (direction.equals("South") || direction.equals("south")) {
        if (!location[i][j].isSouth()) {
          throw new IllegalStateException("There is no path here");
        }
        player.setPlayerLocation(location[i + 1][j]);
      }
    }
    player.getPlayerLocation().setVisited(true);
    return player.getPlayerLocation().getId();
  }

  @Override
  public void collectTreasure() throws IllegalArgumentException {
    if (!player.getPlayerLocation().hasTreasure() && !player.getPlayerLocation().hasArrow()) {
      throw new IllegalArgumentException("No treasure found");
    }
    if (player.getPlayerLocation().getTreasure() == Treasure.DIAMONDS) {
      diamondCount++;
    }
    if (player.getPlayerLocation().getTreasure() == Treasure.RUBY) {
      rubiesCount++;
    }
    if (player.getPlayerLocation().getTreasure() == Treasure.SAPPHIRES) {
      sapphireCount++;
    }
    if (player.getPlayerLocation().hasArrow()) {
      arrowCount++;
    }
    player.getPlayerLocation().setTreasure(null);
    player.getPlayerLocation().setChest(false);
    player.getPlayerLocation().setArrow(false);
  }

  @Override
  public int getDiamondCount() {
    return diamondCount;
  }

  @Override
  public int getSapphireCount() {
    return sapphireCount;
  }

  @Override
  public int getRubiesCount() {
    return rubiesCount;
  }

  @Override
  public int getArrowCount() {
    return arrowCount;
  }

  @Override
  public Location arrowTravel(String direction, int distance) {
    while (distance > 0) {
      int id = arrow.getArrowLocation().getId();
      int h = height - 1;
      int w = width - 1;
      int i = (int) Math.floor(id / width);
      int j = (int) Math.floor(id % width);
      if (isWrapping) {
        if ((direction.equals("North") || direction.equals("north"))
            && arrow.getArrowLocation().isNorth()) {
          if (i - 1 < 0) {
            arrow.setArrowLocation(location[i + h][j]);
          } else {
            arrow.setArrowLocation(location[i - 1][j]);
          }
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isNorth()) {
              direction = "North";
            }
            if (arrow.getArrowLocation().isEast()) {
              direction = "East";
            }
            if (arrow.getArrowLocation().isWest()) {
              direction = "West";
            }
          }
        } else if ((direction.equals("South") || direction.equals("south"))
            && arrow.getArrowLocation().isSouth()) {
          if (i + 1 >= height) {
            arrow.setArrowLocation(location[i - h][j]);
          } else {
            arrow.setArrowLocation(location[i + 1][j]);
          }
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isSouth()) {
              direction = "South";
            }
            if (arrow.getArrowLocation().isEast()) {
              direction = "East";
            }
            if (arrow.getArrowLocation().isWest()) {
              direction = "West";
            }
          }
        } else if ((direction.equals("East") || direction.equals("east"))
            && arrow.getArrowLocation().isEast()) {
          if (j + 1 >= width) {
            arrow.setArrowLocation(location[i][j - w]);
          } else {
            arrow.setArrowLocation(location[i][j + 1]);
          }
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isSouth()) {
              direction = "South";
            }
            if (arrow.getArrowLocation().isEast()) {
              direction = "East";
            }
            if (arrow.getArrowLocation().isNorth()) {
              direction = "North";
            }
          }
        } else if ((direction.equals("West") || direction.equals("west"))
            && arrow.getArrowLocation().isWest()) {
          if (j - 1 < 0) {
            arrow.setArrowLocation(location[i][j + w]);
          } else {
            arrow.setArrowLocation(location[i][j - 1]);
          }
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isSouth()) {
              direction = "South";
            }
            if (arrow.getArrowLocation().isWest()) {
              direction = "West";
            }
            if (arrow.getArrowLocation().isNorth()) {
              direction = "North";
            }
          }
        } else {
          arrowCount--;
          return null;
        }
      }
      if (!isWrapping) {
        if ((direction.equals("North") || direction.equals("north"))
            && arrow.getArrowLocation().isNorth()) {
          arrow.setArrowLocation(location[i - 1][j]);
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isNorth()) {
              direction = "North";
            }
            if (arrow.getArrowLocation().isEast()) {
              direction = "East";
            }
            if (arrow.getArrowLocation().isWest()) {
              direction = "West";
            }
          }
        } else if ((direction.equals("South") || direction.equals("south"))
            && arrow.getArrowLocation().isSouth()) {
          arrow.setArrowLocation(location[i + 1][j]);
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isSouth()) {
              direction = "South";
            }
            if (arrow.getArrowLocation().isEast()) {
              direction = "East";
            }
            if (arrow.getArrowLocation().isWest()) {
              direction = "West";
            }
          }
        } else if (direction.equals("East") && arrow.getArrowLocation().isEast()) {
          arrow.setArrowLocation(location[i][j + 1]);
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isSouth()) {
              direction = "South";
            }
            if (arrow.getArrowLocation().isEast()) {
              direction = "East";
            }
            if (arrow.getArrowLocation().isNorth()) {
              direction = "North";
            }
          }
        } else if ((direction.equals("West") || direction.equals("west"))
            && arrow.getArrowLocation().isWest()) {
          arrow.setArrowLocation(location[i][j - 1]);
          if (!arrow.getArrowLocation().isTunnel()) {
            distance--;
          } else if (arrow.getArrowLocation().isTunnel()) {
            if (arrow.getArrowLocation().isSouth()) {
              direction = "South";
            }
            if (arrow.getArrowLocation().isWest()) {
              direction = "West";
            }
            if (arrow.getArrowLocation().isNorth()) {
              direction = "North";
            }
          }
        } else {
          arrowCount--;
          return null;
        }
      }
    }
    arrowCount--;
    return arrow.getArrowLocation();
  }


  @Override
  public String arrowMonster(Location location) {

    if (location.hasOtyugh()) {
      String result = location.getMonster().takeHit();
      return result;
    } else {
      return "Arrow disappeared in space";
    }
  }

  private void setStrongSmell() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (location[i][j].hasOtyugh()) {
          if (location[i][j].getMonster().isAlive()) {
            List<Location> visited = new ArrayList<>();
            if (isWrapping) {
              int h = height - 1;
              int w = width - 1;
              if (location[i][j].isNorth()) {
                if (i - 1 < 0) {
                  location[i + h][j].setSmell(2);
                  if (location[i + h][j].isNorth() && location[(i + h) - 1][j].hasSmell() != 2) {
                    if (!visited.contains(location[(i + h) - 1][j])) {
                      visited.add(location[(i + h) - 1][j]);
                      if (location[(i + h) - 1][j].hasSmell() == 1) {
                        location[(i + h) - 1][j].setSmell(2);
                      } else {
                        location[(i + h) - 1][j].setSmell(1);
                      }
                    }
                  }
                  if (location[i + h][j].isEast() && (j + 1 < width)
                      && location[i + h][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j + 1])) {
                      visited.add(location[i + h][j + 1]);
                      if (location[i + h][j + 1].hasSmell() == 1) {
                        location[i + h][j + 1].setSmell(2);
                      } else {
                        location[i + h][j + 1].setSmell(1);
                      }
                    }
                  } else if (location[i + h][j].isEast() && (j + 1 > width)
                      && location[i + h][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j - w])) {
                      visited.add(location[i + h][j - w]);
                      if (location[i + h][j - w].hasSmell() == 1) {
                        location[i + h][j - w].setSmell(2);
                      } else {
                        location[i + h][j - w].setSmell(1);
                      }
                    }
                  }
                  if (location[i + h][j].isWest() && (j - 1 >= 0)
                      && location[i + h][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j - 1])) {
                      visited.add(location[i + h][j - 1]);
                      if (location[i + h][j - 1].hasSmell() == 1) {
                        location[i + h][j - 1].setSmell(2);
                      } else {
                        location[i + h][j - 1].setSmell(1);
                      }
                    }
                  } else if (location[i + h][j].isWest() && (j - 1 < 0)
                      && location[i + h][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j + 1])) {
                      visited.add(location[i + h][j + 1]);
                      if (location[i + h][j + 1].hasSmell() == 1) {
                        location[i + h][j + 1].setSmell(2);
                      } else {
                        location[i + h][j + 1].setSmell(1);
                      }
                    }
                  }
                } else {
                  location[i - 1][j].setSmell(2);
                  if (location[i - 1][j].isNorth() && (i - 2 >= 0)
                      && location[i - 2][j].hasSmell() != 2) {
                    if (!visited.contains(location[i - 2][j])) {
                      visited.add(location[i - 2][j]);
                      if (location[i - 2][j].hasSmell() == 1) {
                        location[i - 2][j].setSmell(2);
                      } else {
                        location[i - 2][j].setSmell(1);
                      }
                    }
                  } else if (location[i - 1][j].isNorth()
                      && (i - 2 < 0) && location[(i - 1) + h][j].hasSmell() != 2) {
                    if (!visited.contains(location[(i - 1) + h][j])) {
                      visited.add(location[(i - 1) + h][j]);
                      if (location[(i - 1) + h][j].hasSmell() == 1) {
                        location[(i - 1) + h][j].setSmell(2);
                      } else {
                        location[(i - 1) + h][j].setSmell(1);
                      }
                    }
                  }
                  if (location[i - 1][j].isEast() && (j + 1 < width)
                      && location[i - 1][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j + 1])) {
                      visited.add(location[i - 1][j + 1]);
                      if (location[i - 1][j + 1].hasSmell() == 1) {
                        location[i - 1][j + 1].setSmell(2);
                      } else {
                        location[i - 1][j + 1].setSmell(1);
                      }
                    }
                  } else if (location[i - 1][j].isEast() && (j + 1 > width)
                      && location[i - 1][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j - w])) {
                      visited.add(location[i - 1][j - w]);
                      if (location[i - 1][j - w].hasSmell() == 1) {
                        location[i - 1][j - w].setSmell(2);
                      } else {
                        location[i - 1][j - w].setSmell(1);
                      }
                    }
                  }
                  if (location[i - 1][j].isWest() && (j - 1 >= 0)
                      && location[i - 1][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j - 1])) {
                      visited.add(location[i - 1][j - 1]);
                      if (location[i - 1][j - 1].hasSmell() == 1) {
                        location[i - 1][j - 1].setSmell(2);
                      } else {
                        location[i - 1][j - 1].setSmell(1);
                      }
                    }
                  } else if (location[i - 1][j].isWest() && (j - 1 < 0)
                      && location[i - 1][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j + w])) {
                      visited.add(location[i - 1][j + w]);
                      if (location[i - 1][j + w].hasSmell() == 1) {
                        location[i - 1][j + w].setSmell(2);
                      } else {
                        location[i - 1][j + w].setSmell(1);
                      }
                    }
                  }
                }
              }
              if (location[i][j].isSouth()) {
                if (i + 1 >= height) {
                  location[i - h][j].setSmell(2);
                  if (location[i - h][j].isSouth() && location[(i - h) + 1][j].hasSmell() != 2) {
                    if (!visited.contains(location[(i - h) + 1][j])) {
                      visited.add(location[(i - h) + 1][j]);
                      if (location[(i - h) + 1][j].hasSmell() == 1) {
                        location[(i - h) + 1][j].setSmell(2);
                      } else {
                        location[(i - h) + 1][j].setSmell(1);
                      }
                    }
                  }
                  if (location[i - h][j].isEast() && (j + 1 < width)
                      && location[i - h][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j + 1])) {
                      visited.add(location[i - h][j + 1]);
                      if (location[i - h][j + 1].hasSmell() == 1) {
                        location[i - h][j + 1].setSmell(2);
                      } else {
                        location[i - h][j + 1].setSmell(1);
                      }
                    }
                  } else if (location[i - h][j].isEast() && (j + 1 > width)
                      && location[i - h][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j - w])) {
                      visited.add(location[i - h][j - w]);
                      if (location[i - h][j - w].hasSmell() == 1) {
                        location[i - h][j - w].setSmell(2);
                      } else {
                        location[i - h][j - w].setSmell(1);
                      }
                    }
                  }
                  if (location[i - h][j].isWest() && (j - 1 >= 0)
                      && location[i - h][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j - 1])) {
                      visited.add(location[i - h][j - 1]);
                      if (location[i - h][j - 1].hasSmell() == 1) {
                        location[i - h][j - 1].setSmell(2);
                      } else {
                        location[i - h][j - 1].setSmell(1);
                      }
                    }
                  } else if (location[i - h][j].isWest()
                      && (j - 1 < 0) && location[i - h][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j + w])) {
                      visited.add(location[i - h][j + w]);
                      if (location[i - h][j + w].hasSmell() == 1) {
                        location[i - h][j + w].setSmell(2);
                      } else {
                        location[i - h][j + w].setSmell(1);
                      }
                    }
                  }
                } else {
                  location[i + 1][j].setSmell(2);
                  if (location[i + 1][j].isSouth() && (i + 2 < height)
                      && location[i + 2][j].hasSmell() != 2) {
                    if (!visited.contains(location[i + 2][j])) {
                      visited.add(location[i + 2][j]);
                      if (location[i + 2][j].hasSmell() == 1) {
                        location[i + 2][j].setSmell(2);
                      } else {
                        location[i + 2][j].setSmell(1);
                      }
                    }
                  } else if (location[i + 1][j].isSouth() && (i + 2 > height)
                      && location[(i + 1) - h][j].hasSmell() != 2) {
                    if (!visited.contains(location[(i + 1) - h][j])) {
                      visited.add(location[(i + 1) - h][j]);
                      if (location[(i + 1) - h][j].hasSmell() == 1) {
                        location[(i + 1) - h][j].setSmell(2);
                      } else {
                        location[(i + 1) - h][j].setSmell(1);
                      }
                    }
                  }
                  if (location[i + 1][j].isEast() && (j + 1 < width)
                      && location[i + 1][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j + 1])) {
                      visited.add(location[i + 1][j + 1]);
                      if (location[i + 1][j + 1].hasSmell() == 1) {
                        location[i + 1][j + 1].setSmell(2);
                      } else {
                        location[i + 1][j + 1].setSmell(1);
                      }
                    }
                  } else if (location[i + 1][j].isEast() && (j + 1 > width)
                      && location[i + 1][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j - w])) {
                      visited.add(location[i + 1][j - w]);
                      if (location[i + 1][j - w].hasSmell() == 1) {
                        location[i + 1][j - w].setSmell(2);
                      } else {
                        location[i + 1][j - w].setSmell(1);
                      }
                    }
                  }
                  if (location[i + 1][j].isWest() && (j - 1 >= 0)
                      && location[i + 1][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j - 1])) {
                      visited.add(location[i + 1][j - 1]);
                      if (location[i + 1][j - 1].hasSmell() == 1) {
                        location[i + 1][j - 1].setSmell(2);
                      } else {
                        location[i + 1][j - 1].setSmell(1);
                      }
                    }
                  } else if (location[i + 1][j].isWest() && (j - 1 < 0)
                      && location[i + 1][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j + w])) {
                      visited.add(location[i + 1][j + w]);
                      if (location[i + 1][j + w].hasSmell() == 1) {
                        location[i + 1][j + w].setSmell(2);
                      } else {
                        location[i + 1][j + w].setSmell(1);
                      }
                    }
                  }
                }
              }
              if (location[i][j].isEast()) {
                if (j + 1 >= width) {
                  location[i][j - w].setSmell(2);
                  if (location[i][j - w].isNorth() && (i - 1 >= 0)
                      && location[i - 1][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j - w])) {
                      visited.add(location[i - 1][j - w]);
                      if (location[i - 1][j - w].hasSmell() == 1) {
                        location[i - 1][j - w].setSmell(2);
                      } else {
                        location[i - 1][j - w].setSmell(1);
                      }
                    }
                  } else if (location[i][j - w].isNorth() && (i - 1 < 0)
                      && location[i + h][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j - w])) {
                      visited.add(location[i + h][j - w]);
                      if (location[i + h][j - w].hasSmell() == 1) {
                        location[i + h][j - w].setSmell(2);
                      } else {
                        location[i + h][j - w].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j - w].isSouth() && (i + 1 < height)
                      && location[i + 1][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j - w])) {
                      visited.add(location[i + 1][j - w]);
                      if (location[i + 1][j - w].hasSmell() == 1) {
                        location[i + 1][j - w].setSmell(2);
                      } else {
                        location[i + 1][j - w].setSmell(1);
                      }
                    }
                  } else if (location[i][j - w].isSouth() && (i + 1 > height)
                      && location[i - h][j - w].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j - w])) {
                      visited.add(location[i - h][j - w]);
                      if (location[i - h][j - w].hasSmell() == 1) {
                        location[i - h][j - w].setSmell(2);
                      } else {
                        location[i - h][j - w].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j - w].isEast() && location[i][(j - w) + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i][(j - w) + 1])) {
                      visited.add(location[i][(j - w) + 1]);
                      if (location[i][(j - w) + 1].hasSmell() == 1) {
                        location[i][(j - w) + 1].setSmell(2);
                      } else {
                        location[i][(j - w) + 1].setSmell(1);
                      }
                    }
                  }
                } else {
                  location[i][j + 1].setSmell(2);
                  if (location[i][j + 1].isNorth() && (i - 1 >= 0)
                      && location[i - 1][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j + 1])) {
                      visited.add(location[i - 1][j + 1]);
                      if (location[i - 1][j + 1].hasSmell() == 1) {
                        location[i - 1][j + 1].setSmell(2);
                      } else {
                        location[i - 1][j + 1].setSmell(1);
                      }
                    }
                  } else if (location[i][j + 1].isNorth() && (i - 1 < 0)
                      && location[i + h][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j + 1])) {
                      visited.add(location[i + h][j + 1]);
                      if (location[i + h][j + 1].hasSmell() == 1) {
                        location[i + h][j + 1].setSmell(2);
                      } else {
                        location[i + h][j + 1].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j + 1].isSouth() && (i + 1 < height)
                      && location[i + 1][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j + 1])) {
                      visited.add(location[i + 1][j + 1]);
                      if (location[i + 1][j + 1].hasSmell() == 1) {
                        location[i + 1][j + 1].setSmell(2);
                      } else {
                        location[i + 1][j + 1].setSmell(1);
                      }
                    }
                  } else if (location[i][j + 1].isSouth() && (i + 1 > height)
                      && location[i - h][j + 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j + 1])) {
                      visited.add(location[i - h][j + 1]);
                      if (location[i - h][j + 1].hasSmell() == 1) {
                        location[i - h][j + 1].setSmell(2);
                      } else {
                        location[i - h][j + 1].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j + 1].isEast() && (j + 2 < width)
                      && location[i][j + 2].hasSmell() != 2) {
                    if (!visited.contains(location[i][j + 2])) {
                      visited.add(location[i][j + 2]);
                      if (location[i][j + 2].hasSmell() == 1) {
                        location[i][j + 2].setSmell(2);
                      } else {
                        location[i][j + 2].setSmell(1);
                      }
                    }
                  } else if (location[i][j + 1].isEast() && (j + 2 > width)
                      && location[i][(j + 1) - w].hasSmell() != 2) {
                    if (!visited.contains(location[i][(j + 1) - w])) {
                      visited.add(location[i][(j + 1) - w]);
                      if (location[i][(j + 1) - w].hasSmell() == 1) {
                        location[i][(j + 1) - w].setSmell(2);
                      } else {
                        location[i][(j + 1) - w].setSmell(1);
                      }
                    }
                  }
                }
              }
              if (location[i][j].isWest()) {
                if (j - 1 < 0) {
                  location[i][j + w].setSmell(2);
                  if (location[i][j + w].isNorth() && (i - 1 >= 0)
                      && location[i - 1][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j + w])) {
                      visited.add(location[i - 1][j + w]);
                      if (location[i - 1][j + w].hasSmell() == 1) {
                        location[i - 1][j + w].setSmell(2);
                      } else {
                        location[i - 1][j + w].setSmell(1);
                      }
                    }
                  } else if (location[i][j + w].isNorth() && (i - 1 < 0)
                      && location[i + h][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j + w])) {
                      visited.add(location[i + h][j + w]);
                      if (location[i + h][j + w].hasSmell() == 1) {
                        location[i + h][j + w].setSmell(2);
                      } else {
                        location[i + h][j + w].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j + w].isSouth() && (i + 1 < height)
                      && location[i + 1][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j + w])) {
                      visited.add(location[i + 1][j + w]);
                      if (location[i + 1][j + w].hasSmell() == 1) {
                        location[i + 1][j + w].setSmell(2);
                      } else {
                        location[i + 1][j + w].setSmell(1);
                      }
                    }
                  } else if (location[i][j + w].isSouth() && (i + 1 > height)
                      && location[i - h][j + w].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j + w])) {
                      visited.add(location[i - h][j + w]);
                      if (location[i - h][j + w].hasSmell() == 1) {
                        location[i - h][j + w].setSmell(2);
                      } else {
                        location[i - h][j + w].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j + w].isWest() && location[i][(j + w) - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i][(j + w) - 1])) {
                      visited.add(location[i][(j + w) - 1]);
                      if (location[i][(j + w) - 1].hasSmell() == 1) {
                        location[i][(j + w) - 1].setSmell(2);
                      } else {
                        location[i][(j + w) - 1].setSmell(1);
                      }
                    }
                  }
                } else {
                  location[i][j - 1].setSmell(2);
                  if (location[i][j - 1].isNorth() && (i - 1 >= 0)
                      && location[i - 1][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - 1][j - 1])) {
                      visited.add(location[i - 1][j - 1]);
                      if (location[i - 1][j - 1].hasSmell() == 1) {
                        location[i - 1][j - 1].setSmell(2);
                      } else {
                        location[i - 1][j - 1].setSmell(1);
                      }
                    }
                  } else if (location[i][j - 1].isNorth() && (i - 1 < 0)
                      && location[i + h][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + h][j - 1])) {
                      visited.add(location[i + h][j - 1]);
                      if (location[i + h][j - 1].hasSmell() == 1) {
                        location[i + h][j - 1].setSmell(2);
                      } else {
                        location[i + h][j - 1].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j - 1].isSouth() && (i + 1 < height)
                      && location[i + 1][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i + 1][j - 1])) {
                      visited.add(location[i + 1][j - 1]);
                      if (location[i + 1][j - 1].hasSmell() == 1) {
                        location[i + 1][j - 1].setSmell(2);
                      } else {
                        location[i + 1][j - 1].setSmell(1);
                      }
                    }
                  } else if (location[i][j - 1].isSouth() && (i + 1 > height)
                      && location[i - h][j - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i - h][j - 1])) {
                      visited.add(location[i - h][j - 1]);
                      if (location[i - h][j - 1].hasSmell() == 1) {
                        location[i - h][j - 1].setSmell(2);
                      } else {
                        location[i - h][j - 1].setSmell(1);
                      }
                    }
                  }
                  if (location[i][j - 1].isWest() && j - 2 >= 0
                      && location[i][j - 2].hasSmell() != 2) {
                    if (!visited.contains(location[i][j - 2])) {
                      visited.add(location[i][j - 2]);
                      if (location[i][j - 2].hasSmell() == 1) {
                        location[i][j - 2].setSmell(2);
                      } else {
                        location[i][j - 2].setSmell(1);
                      }
                    }
                  } else if (location[i][j - 1].isWest() && j - 2 < 0
                      && location[i][(j + w) - 1].hasSmell() != 2) {
                    if (!visited.contains(location[i][(j + w) - 1])) {
                      visited.add(location[i][(j + w) - 1]);
                      if (location[i][(j + w) - 1].hasSmell() == 1) {
                        location[i][(j + w) - 1].setSmell(2);
                      } else {
                        location[i][(j + w) - 1].setSmell(1);
                      }
                    }
                  }
                }
              }
            }
            if (!isWrapping) {
              if (location[i][j].isNorth()) {
                location[i - 1][j].setSmell(2);
                if (location[i - 1][j].isNorth() && location[i - 2][j].hasSmell() != 2) {
                  if (!visited.contains(location[i - 2][j])) {
                    visited.add(location[i - 2][j]);
                    if (location[i - 2][j].hasSmell() == 1) {
                      location[i - 2][j].setSmell(2);
                    } else {
                      location[i - 2][j].setSmell(1);
                    }
                  }
                }
                if (location[i - 1][j].isEast() && location[i - 1][j + 1].hasSmell() != 2) {
                  if (!visited.contains(location[i - 1][j + 1])) {
                    visited.add(location[i - 1][j + 1]);
                    if (location[i - 1][j + 1].hasSmell() == 1) {
                      location[i - 1][j + 1].setSmell(2);
                    } else {
                      location[i - 1][j + 1].setSmell(1);
                    }
                  }
                }
                if (location[i - 1][j].isWest() && location[i - 1][j - 1].hasSmell() != 2) {
                  if (!visited.contains(location[i - 1][j - 1])) {
                    visited.add(location[i - 1][j - 1]);
                    if (location[i - 1][j - 1].hasSmell() == 1) {
                      location[i - 1][j - 1].setSmell(2);
                    } else {
                      location[i - 1][j - 1].setSmell(1);
                    }
                  }
                }
              }
              if (location[i][j].isSouth()) {
                location[i + 1][j].setSmell(2);
                if (location[i + 1][j].isSouth() && location[i + 2][j].hasSmell() != 2) {
                  if (!visited.contains(location[i + 2][j])) {
                    visited.add(location[i + 2][j]);
                    if (location[i + 2][j].hasSmell() == 1) {
                      location[i + 2][j].setSmell(2);
                    } else {
                      location[i + 2][j].setSmell(1);
                    }
                  }
                }
                if (location[i + 1][j].isEast() && location[i + 1][j + 1].hasSmell() != 2) {
                  if (!visited.contains(location[i + 1][j + 1])) {
                    visited.add(location[i + 1][j + 1]);
                    if (location[i + 1][j + 1].hasSmell() == 1) {
                      location[i + 1][j + 1].setSmell(2);
                    } else {
                      location[i + 1][j + 1].setSmell(1);
                    }
                  }
                }
                if (location[i + 1][j].isWest() && location[i + 1][j - 1].hasSmell() != 2) {
                  if (!visited.contains(location[i + 1][j - 1])) {
                    visited.add(location[i + 1][j - 1]);
                    if (location[i + 1][j - 1].hasSmell() == 1) {
                      location[i + 1][j - 1].setSmell(2);
                    } else {
                      location[i + 1][j - 1].setSmell(1);
                    }
                  }
                }
              }
              if (location[i][j].isEast()) {
                location[i][j + 1].setSmell(2);
                if (location[i][j + 1].isNorth() && location[i - 1][j + 1].hasSmell() != 2) {
                  if (!visited.contains(location[i - 1][j + 1])) {
                    visited.add(location[i - 1][j + 1]);
                    if (location[i - 1][j + 1].hasSmell() == 1) {
                      location[i - 1][j + 1].setSmell(2);
                    } else {
                      location[i - 1][j + 1].setSmell(1);
                    }
                  }
                }
                if (location[i][j + 1].isSouth() && location[i + 1][j + 1].hasSmell() != 2) {
                  if (!visited.contains(location[i + 1][j + 1])) {
                    visited.add(location[i + 1][j + 1]);
                    if (location[i + 1][j + 1].hasSmell() == 1) {
                      location[i + 1][j + 1].setSmell(2);
                    } else {
                      location[i + 1][j + 1].setSmell(1);
                    }
                  }
                }
                if (location[i][j + 1].isEast() && location[i][j + 2].hasSmell() != 2) {
                  if (!visited.contains(location[i][j + 2])) {
                    visited.add(location[i][j + 2]);
                    if (location[i][j + 2].hasSmell() == 1) {
                      location[i][j + 2].setSmell(2);
                    } else {
                      location[i][j + 2].setSmell(1);
                    }
                  }
                }
              }
              if (location[i][j].isWest()) {
                location[i][j - 1].setSmell(2);
                if (location[i][j - 1].isNorth() && location[i - 1][j - 1].hasSmell() != 2) {
                  if (!visited.contains(location[i - 1][j - 1])) {
                    visited.add(location[i - 1][j - 1]);
                    if (location[i - 1][j - 1].hasSmell() == 1) {
                      location[i - 1][j - 1].setSmell(2);
                    } else {
                      location[i - 1][j - 1].setSmell(1);
                    }
                  }
                }
                if (location[i][j - 1].isSouth() && location[i + 1][j - 1].hasSmell() != 2) {
                  if (!visited.contains(location[i + 1][j - 1])) {
                    visited.add(location[i + 1][j - 1]);
                    if (location[i + 1][j - 1].hasSmell() == 1) {
                      location[i + 1][j - 1].setSmell(2);
                    } else {
                      location[i + 1][j - 1].setSmell(1);
                    }
                  }
                }
                if (location[i][j - 1].isWest() && location[i][j - 2].hasSmell() != 2) {
                  if (!visited.contains(location[i][j - 2])) {
                    visited.add(location[i][j - 2]);
                    if (location[i][j - 2].hasSmell() == 1) {
                      location[i][j - 2].setSmell(2);
                    } else {
                      location[i][j - 2].setSmell(1);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  @Override
  public int getPlayerStartLocation() {
    player.setPlayerLocation(getStart());
    getStart().setVisited(true);
    return player.getPlayerLocation().getId();
  }

  @Override
  public String getTreasureAtLocation() {
    StringBuilder sc1 = new StringBuilder();
    if (player.getPlayerLocation().getTreasure() != null) {
      sc1.append("Player found ").append(player.getPlayerLocation().getTreasure());
      sc1.append("\n");
    } else {
      sc1.append("No treasure in this cave");
      sc1.append("\n");
    }
    if (player.getPlayerLocation().hasArrow()) {
      sc1.append("Player found an arrow!");
      sc1.append("\n");
    } else {
      sc1.append("No Arrow in this cave");
    }
    return sc1.toString();
  }

  @Override
  public void collectStartTreasure() throws IllegalArgumentException {
    if (player.getPlayerLocation().getTreasure() == Treasure.DIAMONDS) {
      diamondCount++;
    }
    if (player.getPlayerLocation().getTreasure() == Treasure.SAPPHIRES) {
      sapphireCount++;
    }
    if (player.getPlayerLocation().getTreasure() == Treasure.RUBY) {
      rubiesCount++;
    }
    if (player.getPlayerLocation().hasArrow()) {
      arrowCount++;
    }
    getStart().setTreasure(null);
    getStart().setChest(false);
    getStart().setArrow(false);
  }

  @Override
  public String getPaths() {
    StringBuilder sc = new StringBuilder();

    sc.append("\n********Doors available******\n");
    if (player.getPlayerLocation().isEast()) {
      sc.append("There is a door to your East\n");
    }
    if (player.getPlayerLocation().isWest()) {
      sc.append("There is a door to your West\n");
    }
    if (player.getPlayerLocation().isNorth()) {
      sc.append("There is a path to your North\n");
    }
    if (player.getPlayerLocation().isSouth()) {
      sc.append("There is a path to your South\n");
    }
    return sc.toString();
  }

  @Override
  public Location getLocation() {
    return player.getPlayerLocation();
  }

  @Override
  public int getPlayerX() {
    int id = getLocation().getId();
    return (int) Math.floor(id / width);
  }

  @Override
  public int getPlayerY() {
    int id = getLocation().getId();
    return (int) Math.floor(id % width);
  }

  @Override
  public String giveDirection(int i, int j) {
    String direction = "";
    if (j == getPlayerX() && i < getPlayerY()) {
      direction = "West";
    } else if (j == getPlayerX() && i > getPlayerY()) {
      direction = "East";
    } else if (j < getPlayerX() && i == getPlayerY()) {
      direction = "North";
    } else if (j > getPlayerX() && i == getPlayerY()) {
      direction = "South";
    }
    return direction;
  }

  @Override
  public void setArrow() {
    arrow.setArrowLocation(player.getPlayerLocation());
  }

  @Override
  public int playerSurvival() {
    int life = random.nextInt(2);
    return life;
  }

  @Override
  public Location getCaveLoc(int i, int j) {
    return location[i][j];
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }


  @Override
  public String arrowHit(String direction, int distance) throws IllegalArgumentException {
    StringBuilder sc = new StringBuilder();
    if (arrowCount > 0) {
      setArrow();
      Location arrowLocation = arrowTravel(direction,
          distance);
      if (arrowLocation != null) {
        sc.append(arrowMonster(arrowLocation));
        if (arrowLocation.hasOtyugh() && !arrowLocation.getMonster().isAlive()) {
          setZero1();
          setStrongSmell();
        }
      } else {
        sc.append("Arrow disappeared in space");
      }
    } else {
      sc.append("You are out of arrows!");
    }
    return sc.toString();
  }

  void setZero1() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        location[i][j].setSmell(0);
      }
    }
  }


  @Override
  public String playerMonster() {
    StringBuilder sc = new StringBuilder();
    if (getLocation().hasPit()) {
      sc.append("Oh no! Player fell in the pit!");
      gameFinish = true;
    }
    if (getLocation().hasOtyugh()) {
      if (getLocation().getMonster().halfInjured()
          && getLocation().getMonster().isAlive()) {
        int life = playerSurvival();
        if (life == 1) {
          if (getLocation().equals(getDestination())) {
            sc.append("Wohoo! You beat the Otyugh! You won");
          } else {
            sc.append("Wohoo! You beat the injured Otyugh!");
          }
        } else {
          sc.append("Oh no! The injured Otyugh killed you!");
          gameFinish = true;
        }
      } else if (!getLocation().getMonster().halfInjured()
          && getLocation().getMonster().isAlive()) {
        sc.append("Oh no! The Otyugh killed you!");
        gameFinish = true;
      } else if (getLocation().equals(getDestination())
          && !getDestination().getMonster().isAlive()) {
        sc.append("You won!");
        gameFinish = true;
      }
    }
    return sc.toString();
  }

  private void addPit() {
    ArrayList<Location> visited = new ArrayList<>();
    for (int i = 0; i < otyughNumber / 2; i++) {
      int randomInt3 = random.nextInt(height);
      int randomInt4 = random.nextInt(width);
      if (!visited.contains(location[randomInt3][randomInt4])) {
        if (location[randomInt3][randomInt4].equals(getStart())) {
          i = i - 1;
        } else if (location[randomInt3][randomInt4].equals(getDestination())) {
          i = i - 1;
        }
        else {
          visited.add(location[randomInt3][randomInt4]);
          location[randomInt3][randomInt4].setPit(true);
        }
      } else {
        i = i - 1;
      }
    }
  }

  @Override
  public String playerPit() {
    StringBuilder sc = new StringBuilder();
    sc.append("Oh no! Player fell in the pit!");
    return sc.toString();
  }

  private void indicator() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (location[i][j].hasPit()) {
          if (isWrapping) {
            int h = height - 1;
            int w = width - 1;
            if (location[i][j].isNorth()) {
              if (i - 1 < 0) {
                location[i + h][j].setWind(true);
              } else {
                location[i - 1][j].setWind(true);
              }
            }
            if (location[i][j].isSouth()) {
              if (i + 1 >= height) {
                location[i - h][j].setWind(true);
              } else {
                location[i + 1][j].setWind(true);
              }
            }
            if (location[i][j].isEast()) {
              if (j + 1 >= width) {
                location[i][j - w].setWind(true);
              } else {
                location[i][j + 1].setWind(true);
              }
            }
            if (location[i][j].isWest()) {
              if (j - 1 < 0) {
                location[i][j + w].setWind(true);
              } else {
                location[i][j - 1].setWind(true);
              }
            }
          }
          if (!isWrapping) {
            if (location[i][j].isNorth()) {
              location[i - 1][j].setWind(true);
            }
            if (location[i][j].isSouth()) {
              location[i + 1][j].setWind(true);
            }
            if (location[i][j].isEast()) {
              location[i][j + 1].setWind(true);
            }
            if (location[i][j].isWest()) {
              location[i][j - 1].setWind(true);
            }
          }
        }
      }
    }
  }
}


