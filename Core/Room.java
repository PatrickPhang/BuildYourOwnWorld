package byow.Core;

public class Room {
    private int upperRightX; // Top right x
    private int upperRightY; // Top right y
    private int lowerRightX; // Bottom Left x
    private int lowerRightY; // Bottom Left y
    private int height;
    private int width;

    public Room(int x, int y, int w, int h) {
        lowerRightX = x;
        lowerRightY = y;
        upperRightX = x + w - 1;
        upperRightY = y + h - 1;
    }

    public int getTrX() {
        return upperRightX;
    }

    public int getTrY() {
        return upperRightY;
    }

    public int getBlX() {
        return lowerRightX;
    }

    public int getBlY() {
        return lowerRightY;
    }

    public boolean intersects(Room other) {
        return true;
        //if (!(other.getBlX() > trX || blX > other.getTrX()))
    }

}
