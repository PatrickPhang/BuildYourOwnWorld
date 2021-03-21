package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Room2 {
    private Position upperLeft; // upperLeft x and upperLeft y
    private Position bottomRight; // bottomRight x and bottomRight y
    private int height;
    private int width;

    public ArrayList<Integer> cHall;

    public Room2(int upperLeftX, int upperLeftY, int height, int width) {
        upperLeft = new Position(upperLeftX, upperLeftY);
        bottomRight = new Position(upperLeftX + width - 1, upperLeftY - height + 1);
        this.height = height;
        this.width = width;
        cHall = new ArrayList<>(); // North, East, South West
        cHall.add(0);  // North
        cHall.add(1); // East
        cHall.add(2); // South
        cHall.add(3); // West
    }

    public List<Position> getRoomPositions() {
        List<Position> positions = new ArrayList<>();
        for (int x = upperLeft.getX(); x < bottomRight.getX() + 1; x += 1) {
            for (int y = bottomRight.getY(); y < upperLeft.getY() + 1; y += 1) {
                positions.add(new Position(x, y));
            }
        }
        return positions;
    }

    public Position getUpperLeft() {
        return upperLeft;
    }

    public Position getBottomRight() {
        return bottomRight;
    }

    public Position getBottomLeft() {
        return new Position(upperLeft.getX(), bottomRight.getY());
    }

    public Position getUpperRight() {
        return new Position(bottomRight.getX(), upperLeft.getY());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * @source
     * https://www.geeksforgeeks.org/overriding-equals-method-in-java/
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Room2 c = (Room2) o;
        List<Position> otherPositions = c.getRoomPositions();

        for (Position p : this.getRoomPositions()) {
            if (!otherPositions.contains(p)) {
                return false;
            }
        }
        return true;
    }

    public Position getRandomPos(Random r) {
        int orientation = RandomUtils.uniform(r, 0, cHall.size());

        if (orientation == 0) { // north
            int x = RandomUtils.uniform(r, this.getUpperLeft().getX(), this.getBottomRight().getX() + 1);
            int y = this.getUpperLeft().getY() + 1;
            this.cHall.remove(orientation);
            return new Position(x, y);
        } else if (orientation == 1) { // east
            int x = this.getBottomRight().getX() + 1;
            int y = RandomUtils.uniform(r, this.getBottomRight().getY(), this.getUpperLeft().getY() + 1);
            this.cHall.remove(orientation);
            return new Position(x, y);
        } else if (orientation == 2) { // south
            int x = RandomUtils.uniform(r, this.getUpperLeft().getX(), this.getBottomRight().getX() + 1);
            int y = this.getBottomRight().getY() - 1;
            this.cHall.remove(orientation);
            return new Position(x, y);
        } else { // west
            int x = this.getUpperLeft().getX() - 1;
            int y = RandomUtils.uniform(r, this.getBottomRight().getY(), this.getUpperLeft().getY() + 1);
            this.cHall.remove(orientation);
            return new Position(x, y);
        }
    }
}
