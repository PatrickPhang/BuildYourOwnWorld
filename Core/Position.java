package byow.Core;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position ref, int xOffset, int yOffset) {
        this.x = ref.getX() + xOffset;
        this.y = ref.getY() + yOffset;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

        Position c = (Position) o;

        if (this.getX() == c.getX() && this.getY() == c.getY()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
