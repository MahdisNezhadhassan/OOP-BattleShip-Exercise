public class Ship {
    private String name;
    private int size;
    private int hitCount;
    private Coordinate[] position;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hitCount = 0;
        this.position = new Coordinate[size];
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void hit() {
        hitCount++;
    }

    public boolean isSunk() {
        return hitCount >= size;
    }

    public int getHealth() {
        return size - hitCount;
    }

    public boolean isHit(Coordinate coordinate) {
        for (Coordinate pos : position) {
            if (pos != null && pos.equals(coordinate)) {
                return true;
            }
        }
        return false;
    }

    public void setPosition(Coordinate[] position) {
        this.position = position;
    }

    public String getStatus() {
        return name + "-Health: " + getHealth() + "/" + size;
    }
}