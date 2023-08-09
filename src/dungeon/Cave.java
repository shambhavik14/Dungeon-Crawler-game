package dungeon;


/**
 * This class implements the Location interface. A player moves from one position to another
 * in the dungeon.
 */

class Cave implements Location {

  private boolean north;
  private boolean south;
  private boolean east;
  private boolean west;
  private boolean tunnel;
  private boolean visited;
  private boolean pit;
  private boolean wind;
  private Treasure treasure;
  private boolean chest;
  private Monster otyugh;
  private boolean arrow;
  private boolean monster;
  private int smell = 0;
  int id;

  Cave(int id) {
    this.id = id;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setEast(boolean east) {
    this.east = east;
  }

  @Override
  public void setWest(boolean west) {
    this.west = west;
  }

  @Override
  public void setNorth(boolean north) {
    this.north = north;
  }

  @Override
  public void setSouth(boolean south) {
    this.south = south;
  }

  @Override
  public boolean isNorth() {
    return north;
  }

  @Override
  public boolean isSouth() {
    return south;
  }

  @Override
  public boolean isEast() {
    return east;
  }

  @Override
  public boolean isWest() {
    return west;
  }

  @Override
  public void setTunnel(boolean tunnel) {
    this.tunnel = tunnel;
  }

  @Override
  public boolean isTunnel() {
    return tunnel;
  }

  @Override
  public void setTreasure(Treasure treasure) {
    this.treasure = treasure;
  }

  @Override
  public Treasure getTreasure() {
    return treasure;
  }


  @Override
  public void setArrow(boolean arrow) {
    this.arrow = arrow;
  }

  @Override
  public boolean hasArrow() {
    return arrow;
  }

  @Override
  public void setOtyugh(boolean monster) {
    this.monster = monster;
  }

  @Override
  public boolean hasOtyugh() {
    return monster;
  }

  @Override
  public Monster getMonster() {
    return otyugh;
  }

  @Override
  public void addMonster() {
    otyugh = new Otyugh();
  }

  @Override
  public void setSmell(int smell) {
    this.smell = smell;
  }

  @Override
  public int hasSmell() {
    return smell;
  }

  @Override
  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  @Override
  public boolean getVisited() {
    return visited;
  }


  @Override
  public void setChest(boolean chest) {
    this.chest = chest;
  }

  @Override
  public boolean hasTreasure() {
    return chest;
  }

  @Override
  public void setPit(boolean pit) {
    this.pit = pit;
  }

  @Override
  public boolean hasPit() {
    return pit;
  }

  @Override
  public void setWind(boolean wind) {
    this.wind = wind;
  }

  @Override
  public boolean getWind() {
    return wind;
  }
}
