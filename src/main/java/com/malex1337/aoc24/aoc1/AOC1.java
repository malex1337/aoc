package com.malex1337.aoc24.aoc1;

import com.malex1337.Day;

import java.util.Arrays;

public class AOC1 extends Day {

    public AOC1(String fileName) {
        super(fileName);
    }

    @Override
    public void part1() {
        String[] lines = input.split("\n");
        int[] left = new int[lines.length];
        int[] right = new int[lines.length];

        // fill arrays
        int idx = 0;
        for (String line : lines) {
            String[] splitted = line.split(";");
            left[idx] = Integer.parseInt(splitted[0]);
            right[idx] = Integer.parseInt(splitted[1]);
            idx++;
        }

        // sort
        Arrays.sort(left);
        Arrays.sort(right);

        // diffs
        int sum = 0;
        for (int i = 0; i < left.length; i++) {
            int v = left[i] > right[i] ? left[i] - right[i] : right[i] - left[i];
            sum += v;
        }

        System.out.println("List with size " + lines.length + " resulted in " + sum);
    }

    @Override
    public void part2() {

    }


    public static void main(String[] args) {
        AOC1 aoc1 = new AOC1("aoc1_input");
        aoc1.run();
    }
}