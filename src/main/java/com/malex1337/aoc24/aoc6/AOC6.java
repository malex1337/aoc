package com.malex1337.aoc24.aoc6;

import com.malex1337.Day;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.malex1337.aoc24.aoc6.AOC6.Direction.UP;

public class AOC6 extends Day {
    private static final char OBSTACLE = '#';
    private static final Set<Position> visitedPoints = new HashSet<>();

    Position position;
    char[][] grid;

    protected AOC6(String fileName) {
        super(fileName);
        fillGrid(input.split("\n"));
    }

    @Override
    public void part1() {
        visitedPoints.add(position);
        Position nextPosition = nextPosition();

        while (isInGrid(nextPosition.i, nextPosition.j)) {
            visitedPoints.add(nextPosition);
            position = nextPosition;
            nextPosition = nextPosition();
        }

        System.out.println("Visited " + visitedPoints.size() + " unique locations");
    }

    @Override
    public void part2() {

    }

    private Position nextPosition() {
        int nextI = position.nextI();
        int nextJ = position.nextJ();

        if (!isInGrid(nextI, nextJ)) {
            return Position.INVALID;
        }

        if (grid[nextI][nextJ] == OBSTACLE) {
            Direction d = getCollidingDirection(position.direction);
            nextI = position.i + d.coordinates[0];
            nextJ = position.j + d.coordinates[1];

            return new Position(nextI, nextJ, d);
        }
        return new Position(nextI, nextJ, position.direction);
    }

    private boolean isInGrid(int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
    }

    private Direction getCollidingDirection(Direction direction) {
        return switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> UP;
        };
    }

    private void fillGrid(String[] split) {
        var length = split[0].length();
        var height = split.length;

        grid = new char[height][length];

        int idx = 0;
        for (String line : split) {
            if (line.indexOf(UP.ch) != -1) {
                position = new Position(idx, line.indexOf(UP.ch));
            }
            grid[idx++] = line.toCharArray();
        }
    }

    public static void main(String[] args) {
        AOC6 aoc6 = new AOC6("aoc6_input");
        aoc6.run();
    }

    enum Direction {
        UP('^', new int[]{-1, 0}),
        DOWN('v', new int[]{1, 0}),
        RIGHT('>', new int[]{0, 1}),
        LEFT('<', new int[]{0, -1});

        final char ch;
        final int[] coordinates;

        Direction(char ch, int[] coordinates) {
            this.ch = ch;
            this.coordinates = coordinates;
        }
    }

    static class Position {
        static final Position INVALID = new Position(-1, -1);

        int i;
        int j;
        Direction direction;

        public Position(int i, int j) {
            this(i, j, UP);
        }

        public Position(int i, int j, Direction direction) {
            this.i = i;
            this.j = j;
            this.direction = direction;
        }

        int nextI() {
            return i + direction.coordinates[0];
        }

        int nextJ() {
            return j + direction.coordinates[1];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return i == position.i && j == position.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}