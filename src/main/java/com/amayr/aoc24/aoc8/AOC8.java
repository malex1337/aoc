package com.amayr.aoc24.aoc8;

import com.amayr.Day;

import java.util.*;

public class AOC8 extends Day {
    private static final char FREE = '.';
    static int gridLengthI;
    static int gridLengthJ;
    Map<Character, Frequency> frequencies = new HashMap<>();

    protected AOC8(String fileName) {
        super(fileName);
        fillFrequencies();
    }

    @Override
    public void part1() {
        fillFrequencies();
        frequencies.values().forEach(Frequency::calcAntinodes);

        Set<Position> uniqueAntinodes = new HashSet<>();
        frequencies.values().forEach(v -> uniqueAntinodes.addAll(v.antinode));

        System.out.println("Unique antinodes: " + uniqueAntinodes.size());
    }

    private void fillFrequencies() {
        String[] lines = input.split("\n");
        gridLengthI = lines.length;

        for (int i = 0; i < lines.length; i++) {
            final int idxi = i;
            var chars = lines[i].toCharArray();
            gridLengthJ = chars.length;
            for (int j = 0; j < chars.length; j++) {
                final int idxj = j;
                if (chars[j] != FREE) {
                    frequencies.compute(chars[j], (k, v) -> {
                        if (v == null) {
                            return new Frequency(idxi, idxj);
                        } else {
                            v.positions.add(new Position(idxi, idxj));
                            return v;
                        }
                    });
                }
            }
        }

        frequencies.forEach((k, v) -> {
            if (!v.hasMultiplePositions()) {
                frequencies.remove(k);
            }
        });
    }

    @Override
    public void part2() {

    }

    public static void main(String[] args) {
        AOC8 aoc8 = new AOC8("aoc8_input");
        aoc8.run();
    }

    static class Frequency {
        private final List<Position> positions;
        private final List<Position> antinode;

        Frequency(int i, int j) {
            positions = new ArrayList<>();
            positions.add(new Position(i, j));
            antinode = new ArrayList<>();
        }

        boolean hasMultiplePositions() {
            return positions.size() > 1;
        }

        void calcAntinodes() {
            for (Position posA : positions) {
                for (Position posB : positions) {
                    if (posA.equals(posB)) {
                        continue;
                    }

                    int diffI = posA.i - posB.i;
                    int diffJ = posA.j - posB.j;

                    var antinodePosition = new Position(posA.i + diffI, posA.j + diffJ);
                    if (antinodePosition.i < gridLengthI && antinodePosition.j < gridLengthJ && !antinodePosition.equals(posB)
                            && antinodePosition.i >= 0 && antinodePosition.j >= 0) {
                        antinode.add(antinodePosition);
                    }

                    antinodePosition = new Position(posB.i - diffI, posB.j - diffJ);
                    if (antinodePosition.i < gridLengthI && antinodePosition.j < gridLengthJ &&
                            antinodePosition.i >= 0 && antinodePosition.j >= 0 && !antinodePosition.equals(posA)) {
                        antinode.add(antinodePosition);
                    }
                }
            }

        }
    }

    record Position(int i, int j) {
    }
}