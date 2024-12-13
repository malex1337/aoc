package com.amayr.aoc24.aoc2;

import com.amayr.Day;

public class AOC2 extends Day {
    private static final int minDistance = 1;
    private static final int maxDistance = 3;

    private int safes = 0;
    private int unsafes = 0;

    public AOC2(String fileName) {
        super(fileName);
    }

    @Override
    public void part1() {
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] levels = line.split(" ");
            boolean ltr = Integer.parseInt(levels[0]) > Integer.parseInt(levels[1]);
            boolean lineSafe = true;

            for (int i = 0; i < levels.length - 1 && lineSafe; i++) {
                int left = Integer.parseInt(levels[i]);
                int right = Integer.parseInt(levels[i + 1]);

                int diff = ltr ? left - right : right - left;
                if (!(diff >= minDistance && diff <= maxDistance)) {
                    lineSafe = false;
                }
            }

            if (lineSafe) {
                safes++;
            } else {
                unsafes++;
            }
        }

        System.out.println("safes: " + safes + " unsafes: " + unsafes);
    }

    @Override
    public void part2() {

    }

    public static void main(String[] args) {
        AOC2 aoc2 = new AOC2("aoc2_input");
        aoc2.run();
    }
}