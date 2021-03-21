package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.*;

public class World {
    int Width; // World width
    int Height; // World Height
    long seed = 0;
    long limit = Long.MAX_VALUE;
    TETile[][] worldFrame;
    Random r;
    double percentageLimit = 0.4;
    double filled = 0;
    double size;
    List<Room2> rooms2;
    List<Position> roomPositions;
    List<Position> hallPositions;

    public World(String input) {
        input = input.toLowerCase();
        if (input.charAt(0) == 'n') {
            for (int i = 1; i < input.length() && input.charAt(i) != 's'; i++) {
                int lastNum =  Character.getNumericValue(input.charAt(i));
                if (this.seed > limit / 10 || (this.seed == limit / 10 && lastNum > 7)) {
                    throw new IllegalArgumentException();
                }
                this.seed *= 10;
                this.seed += lastNum;
            }
            r = new Random(seed);

            roomPositions = new ArrayList<>();
            rooms2 = new ArrayList<>();
            hallPositions = new ArrayList<>();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Generates world including rooms and hallways (separate methods)
     */
    public void generateWorld(int w, int h) {
        this.Width = w;
        this.Height = h;

        worldFrame = new TETile[Width][Height];
        for (int x = 0; x < Width; x += 1) {
            for (int y = 0; y < Height; y += 1) {
                worldFrame[x][y] = Tileset.NOTHING;
            }
        }
        size = w * h;
    }

    private void generateWalls() {
        for (Position position : roomPositions) {
            if (position.getX() - 1 >= 0 && position.getX() - 1 < Width &&
                    position.getY() >= 0 && position.getY() < Height &&
                    worldFrame[position.getX() - 1][position.getY()].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() - 1][position.getY()] = Tileset.WALL;
            }

            if (position.getX() + 1 >= 0 && position.getX() + 1 < Width &&
                    position.getY() >= 0 && position.getY() < Height &&
                    worldFrame[position.getX() + 1][position.getY()].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() + 1][position.getY()] = Tileset.WALL;
            }

            if (position.getX() >= 0 && position.getX() < Width &&
                    position.getY() + 1 >= 0 && position.getY() + 1 < Height &&
                    worldFrame[position.getX()][position.getY() + 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX()][position.getY() + 1] = Tileset.WALL;
            }

            if (position.getX() >= 0 && position.getX() < Width &&
                    position.getY() - 1 >= 0 && position.getY() - 1 < Height &&
                    worldFrame[position.getX()][position.getY() - 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX()][position.getY() - 1] = Tileset.WALL;
            }
            if (position.getX() - 1 >= 0 && position.getX() - 1 < Width &&
                    position.getY() - 1 >= 0 && position.getY() - 1 < Height &&
                    worldFrame[position.getX() - 1][position.getY() - 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() - 1][position.getY() - 1] = Tileset.WALL;
            }
            if (position.getX() + 1 >= 0 && position.getX() + 1 < Width &&
                    position.getY() + 1 >= 0 && position.getY() +  1 < Height &&
                    worldFrame[position.getX() + 1][position.getY() + 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() + 1][position.getY() + 1] = Tileset.WALL;
            }
            if (position.getX() - 1 >= 0 && position.getX() - 1 < Width &&
                    position.getY() + 1 >= 0 && position.getY() + 1 < Height &&
                    worldFrame[position.getX() - 1][position.getY() + 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() - 1][position.getY() + 1] = Tileset.WALL;
            }
            if (position.getX() + 1 >= 0 && position.getX() + 1< Width &&
                    position.getY() - 1 >= 0 && position.getY() - 1 < Height &&
                    worldFrame[position.getX() + 1][position.getY() - 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() + 1][position.getY() - 1] = Tileset.WALL;
            }
        }

        for (Position position : hallPositions) {
            if (position.getX() - 1 >= 0 && position.getX() - 1 < Width &&
                    position.getY() >= 0 && position.getY() < Height &&
                    worldFrame[position.getX() - 1][position.getY()].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() - 1][position.getY()] = Tileset.WALL;
            }

            if (position.getX() + 1 >= 0 && position.getX() + 1 < Width &&
                    position.getY() >= 0 && position.getY() < Height &&
                    worldFrame[position.getX() + 1][position.getY()].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() + 1][position.getY()] = Tileset.WALL;
            }

            if (position.getX() >= 0 && position.getX() < Width &&
                    position.getY() + 1 >= 0 && position.getY() + 1 < Height &&
                    worldFrame[position.getX()][position.getY() + 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX()][position.getY() + 1] = Tileset.WALL;
            }

            if (position.getX() >= 0 && position.getX() < Width &&
                    position.getY() - 1 >= 0 && position.getY() - 1 < Height &&
                    worldFrame[position.getX()][position.getY() - 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX()][position.getY() - 1] = Tileset.WALL;
            }
            if (position.getX() - 1 >= 0 && position.getX() - 1 < Width &&
                    position.getY() - 1 >= 0 && position.getY() - 1 < Height &&
                    worldFrame[position.getX() - 1][position.getY() - 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() - 1][position.getY() - 1] = Tileset.WALL;
            }
            if (position.getX() + 1 >= 0 && position.getX() + 1 < Width &&
                    position.getY() + 1 >= 0 && position.getY() +  1 < Height &&
                    worldFrame[position.getX() + 1][position.getY() + 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() + 1][position.getY() + 1] = Tileset.WALL;
            }
            if (position.getX() - 1 >= 0 && position.getX() - 1 < Width &&
                    position.getY() + 1 >= 0 && position.getY() + 1 < Height &&
                    worldFrame[position.getX() - 1][position.getY() + 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() - 1][position.getY() + 1] = Tileset.WALL;
            }
            if (position.getX() + 1 >= 0 && position.getX() + 1< Width &&
                    position.getY() - 1 >= 0 && position.getY() - 1 < Height &&
                    worldFrame[position.getX() + 1][position.getY() - 1].equals(Tileset.NOTHING)) {
                worldFrame[position.getX() + 1][position.getY() - 1] = Tileset.WALL;
            }
        }
    }

    public boolean checkRoomCollision(List<Position> positions) {
        for (Position position : positions) {
            if (roomPositions.contains(position)) {
                return true;
            } else if (roomPositions.contains(new Position(position, 1, 0))) {
                return true;
            } else if ((roomPositions.contains(new Position(position, -1, 0)))) {
                return true;
            } else if ((roomPositions.contains(new Position(position, 0, 1)))) {
                return true;
            } else if ((roomPositions.contains(new Position(position, 0, -1)))) {
                return true;
            } else if ((roomPositions.contains(new Position(position, -1, -1)))) {
                return true;
            } else if ((roomPositions.contains(new Position(position, 1, 1)))) {
                return true;
            } else if ((roomPositions.contains(new Position(position, -1, 1)))) {
                return true;
            } else if ((roomPositions.contains(new Position(position, 1, -1)))) {
                return true;
            }
        }
        return false;
    }

    private Room2 generateRoom(Position pos, int width, int height, int pot) {
        Room2 room = new Room2(pos.getX(), pos.getY(), height, width);

        if (pos.getY() < 1) {
            return null;
        }
        if (pos.getX() < 1) {
            return null;
        }
        if (pos.getY() >= Height - 1 || pos.getY() + height >= Height - 1) {
            return null;
        }
        if (pos.getX() >= Width - 1 || pos.getX() + width >= Width - 1) {
            return null;
        }

        if (pot == 0) { // north
            int xUpperLeft = RandomUtils.uniform(r, pos.getX() - width, pos.getX() + 1);
            room = new Room2(xUpperLeft, pos.getY()- 1, height, width);
        } else if (pot == 1) { // east
            int xUpperLeft = pos.getX() - width + 1;
            int yUpperLeft = RandomUtils.uniform(r, pos.getY(), pos.getY() + height);
            room = new Room2(xUpperLeft, yUpperLeft, height, width);
        } else if (pot == 2) { // south
            int xUpperLeft = RandomUtils.uniform(r, pos.getX() - width, pos.getX() + 1);
            int yUpperLeft = pos.getY() + height - 1;
            room = new Room2(xUpperLeft, yUpperLeft + 1, height, width);
        } else if (pot == 3) { // west
            int yUpperLeft = RandomUtils.uniform(r, pos.getY(), pos.getY() + height);
            room = new Room2(pos.getX() + 1, yUpperLeft, height, width);
        }

        List<Position> positions = room.getRoomPositions();
        if (checkRoomCollision(positions)) {
            return null;
        }
        for (Position position : positions) {
            if (position.getX() < 1 || position.getX() >= Width - 1) {
                return null;
            }
            if (position.getY() < 1 || position.getY() >= Height - 1) {
                return null;
            }
        }


        for (Position position : positions) {
            roomPositions.add(position);
            filled += 1;
            worldFrame[position.getX()][position.getY()] = Tileset.FLOOR;
        }

        rooms2.add(room);
        return room;
    }

    private void generateHallandRoom() {
        // Make sure the width and height of the room is between 2 and 6

        int thisWidth = RandomUtils.uniform(r, 2, 6);
        int thisHeight = RandomUtils.uniform(r, 2, 6);

        int length;

        Hall hall = new Hall(worldFrame, Width, Height, hallPositions);
        Position p = hall.addHorizontal(r, new Position(1, Height / 2), 7);
        Room2 end = this.generateRoom(p, thisWidth, thisHeight, RandomUtils.uniform(r, 0, 4));

        filled += 7;

        while ((filled / size) < percentageLimit) {
            int pot = RandomUtils.uniform(r, 0, 3);
            if (pot == 0) {
                if (end == null && p != null) { // if room returns null i.e collision
                    length = RandomUtils.uniform(r, 15, 20);
                    p = hall.drawCorner(r, p, length);
                    end = this.generateRoom(p, thisWidth, thisHeight, RandomUtils.uniform(r, 0, 4));
                    filled += length;
                } else {
                    length = RandomUtils.uniform(r, 15, 20);
                    p = hall.drawCorner(r, end.getRandomPos(r), length);
                    thisWidth = RandomUtils.uniform(r, 3, 6);
                    thisHeight = RandomUtils.uniform(r, 3, 6);
                    end = this.generateRoom(p, thisWidth, thisHeight, RandomUtils.uniform(r, 0, 4));
                    filled += length;
                }
            } else if (pot == 1) {
                if (end == null && p != null) {
                    length = RandomUtils.uniform(r, 5, 10);
                    p = hall.addHorizontal(r, p, 2);
                    end = this.generateRoom(p, thisWidth, thisHeight, RandomUtils.uniform(r, 0, 4));
                    filled += 2;
                } else {
                    length = RandomUtils.uniform(r, 5, 10);
                    p = hall.addHorizontal(r, end.getRandomPos(r), length);
                    thisWidth = RandomUtils.uniform(r, 3, 6);
                    thisHeight = RandomUtils.uniform(r, 3, 6);
                    end = this.generateRoom(p, thisWidth, thisHeight, RandomUtils.uniform(r, 0, 4));
                    filled += length;
                }
            } else if (pot == 2) {
                if (end == null && p != null) {
                    length = RandomUtils.uniform(r, 10, 15);
                    p = hall.addVertical(r, p, 2);
                    end = this.generateRoom(p, thisWidth, thisHeight, RandomUtils.uniform(r, 0, 4));
                    filled += 2;
                } else {
                    length = RandomUtils.uniform(r, 5, 10);
                    p = hall.addVertical(r, end.getRandomPos(r), length);
                    thisWidth = RandomUtils.uniform(r, 3, 6);
                    thisHeight = RandomUtils.uniform(r, 3, 6);
                    end = this.generateRoom(p, thisWidth, thisHeight, RandomUtils.uniform(r, 0, 4));
                    filled += length;
                }
            }
        }
        this.generateWalls();
    }

    public TETile[][] returnWorld() {
        generateHallandRoom();
        return worldFrame;
    }

    public static void main(String[] args) {
        World world = new World("n69420");
        world.generateWorld(80, 30);
        TERenderer ter = new TERenderer();
        ter.initialize(80, 30);

        world.returnWorld();
        ter.renderFrame(world.worldFrame);
    }
}
