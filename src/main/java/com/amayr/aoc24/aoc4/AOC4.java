package com.amayr.aoc24.aoc4;

import com.amayr.Day;

public class AOC4 extends Day {
    final int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    final char[] xmas = {'X', 'M', 'A', 'S'};

    public AOC4(String fileName) {
        super(fileName);
    }

    @Override
    public void part1() {
        String[] lines = input.split("\n");

        char[][] grid = new char[lines.length][lines[0].length()];
        fillGrid(grid, lines);

        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] != xmas[0]) continue;
                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];
                    int idx = 1;
                    while (inGrid(newRow, newCol, grid) && idx < xmas.length && grid[newRow][newCol] == xmas[idx]) {
                        newRow += direction[0];
                        newCol += direction[1];
                        idx++;
                    }
                    count += idx == xmas.length ? 1 : 0;
                }
            }
        }

        System.out.println("Found " + count + " xmas");
    }

    @Override
    public void part2() {

    }

    private boolean inGrid(int row, int col, char[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    private void fillGrid(char[][] grid, String[] lines) {
        int i = 0;
        for (String line : lines) {
            grid[i++] = line.toCharArray();
        }
    }

    public static void main(String[] args) {
        AOC4 aoc4 = new AOC4("aoc4_input");
        aoc4.run();
    }
}