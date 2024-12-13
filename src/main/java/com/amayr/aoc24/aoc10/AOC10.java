package com.amayr.aoc24.aoc10;

import com.amayr.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AOC10 extends Day {
    private static final int START = 0;
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // up right down left
    int[][] grid;
    List<Coord> possibleStarts = new ArrayList<>();

    protected AOC10(String fileName) {
        super(fileName);
        initGrid();
    }

    @Override
    public void part1() {
        int sum = 0;
        for (Coord coord : possibleStarts) {
            sum += travelCoords(coord, 0, new HashSet<>());
        }

        System.out.println(sum);
    }


    @Override
    public void part2() {
    }

    private int travelCoords(Coord coord, int current, Set<Coord> visited) {
        visited.add(coord);
        if (current == 9) {
            return 1;
        }

        int score = 0;
        for (int[] direction : DIRECTIONS) {
            var newDir = coord.addDirection(direction);
            if (isInGrid(newDir) && grid[newDir.i][newDir.j] == current + 1 && !visited.contains(newDir)) {
                score += travelCoords(newDir, current + 1, visited);
            }
        }

        return score;
    }

    boolean isInGrid(Coord coord) {
        return coord.i >= 0 && coord.i < grid.length
                && coord.j >= 0 && coord.j < grid[0].length;
    }

    private void initGrid() {
        var lines = input.split("\n");
        grid = new int[lines.length][lines[0].length()];

        int i = 0;
        for (String line : lines) {
            for (int j = 0; j < line.length(); j++) {
                var intVal = Character.getNumericValue(line.charAt(j));
                grid[i][j] = intVal;
                if (intVal == START) {
                    possibleStarts.add(new Coord(i, j));
                }
            }
            i++;
        }
    }

    public static void main(String[] args) {
        AOC10 aoc10 = new AOC10("aoc10_input");
        aoc10.run();
    }

    record Coord(int i, int j) {
        Coord addDirection(int[] dir) {
            return new Coord(this.i + dir[0], this.j + dir[1]);
        }
    }
}