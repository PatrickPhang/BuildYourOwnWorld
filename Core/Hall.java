package byow.Core;


import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hall {
    private TETile[][] world;
    private int Width;
    private int Height;
    private List<Position> positions;

    public Hall(TETile[][] world, int width, int height, List<Position> pos) {
        this.Width = width;
        this.Height = height;
        this.world = world;
        this.positions = pos;
    }

    public Position addHorizontal(Random r, Position p, int length) { // Hall starts at the given position

        int orientation = RandomUtils.uniform(r, 0, 2);
        if (orientation == 0 || p.getX() < Width / 3) {
            for (int i = 0; i < length; i += 1) {
                if (p.getX() >= Width - 1 || p.getX() <= 0) {
                    break;
                }
                if (p.getY() >= Height - 1|| p.getY() <= 0) {
                    break;
                }
                world[p.getX()][p.getY()] = Tileset.FLOOR;
                positions.add(p);
                p = new Position(p.getX() + 1, p.getY());
            }
            return new Position(p.getX() - 1, p.getY());
        } else {
            for (int i = 0; i < length; i += 1) {
                if (p.getX() >= Width - 1 || p.getX() <= 0) {
                    break;
                }
                if (p.getY() >= Height - 1|| p.getY() <= 0) {
                    break;
                }
                world[p.getX()][p.getY()] = Tileset.FLOOR;
                positions.add(p);
                p = new Position(p.getX() - 1, p.getY());
            }
            return new Position(p.getX() + 1, p.getY());
        }
    }

    public Position addVertical(Random r, Position p, int length) { // Hall starts at the given position
        int orientation = RandomUtils.uniform(r, 0, 2);
        if (orientation == 1 || p.getY() < Height / 3) {
            for (int i = 0; i < length; i += 1) {
                if (p.getX() >= Width - 1|| p.getX() <= 0) {
                    break;
                }
                if (p.getY() >= Height - 1|| p.getY() <= 0) {
                    break;
                }
                world[p.getX()][p.getY()] = Tileset.FLOOR;
                positions.add(p);
                p = new Position(p.getX(), p.getY() + 1);
            }
            return new Position(p.getX(), p.getY() - 1);
        } else {
            for (int i = 0; i < length; i += 1) {
                if (p.getX() >= Width - 1 || p.getX() <= 0) {
                    break;
                }
                if (p.getY() >= Height - 1 || p.getY() <= 0) {
                    break;
                }
                world[p.getX()][p.getY()] = Tileset.FLOOR;
                positions.add(p);
                p = new Position(p.getX(), p.getY() - 1);
            }
            return new Position(p.getX(), p.getY() + 1);
        }
    }

    public Position drawCorner(Random r, Position p, int length) {
        Position pos = addHorizontal(r, p, length / 2);
        pos = addVertical(r, pos, length / 2);
        return pos;
    }

    public Position drawCrossing(Random r, Position p, int length) {
        Position pos = addHorizontal(r, p, length / 2);
        pos = addVertical(r, new Position(pos.getX() - 2, pos.getY() - 2), length / 2);
        return pos;
    }

    public static void main(String[] args) {
        final int WIDTH = 60;
        final int HEIGHT = 30;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.FLOWER;
            }
        }

        Hall hall = new Hall(world, WIDTH, HEIGHT, new ArrayList<>());

        System.out.print(hall.drawCrossing(new Random(10), new Position(10, 10), 10));
        ter.renderFrame(world);

    }


}
