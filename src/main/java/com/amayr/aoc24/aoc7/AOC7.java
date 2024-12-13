package com.amayr.aoc24.aoc7;

import com.amayr.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AOC7 extends Day {
    protected AOC7(String fileName) {
        super(fileName);
    }

    @Override
    public void part1() {
        long sum = 0;
        for (String line : input.split("\n")) {
            String[] l = line.split(":");
            long result = Long.parseLong(l[0]);
            long[] terms = Arrays.stream(l[1].trim().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            List<String> operations = new ArrayList();
            if (backtrack(result, terms, 1, terms[0], "" + terms[0], operations)) {
                System.out.println(line + " => " + operations.get(0));
                sum += result;
            } else {
                System.out.println(line + " => no solution found");
            }
        }

        System.out.println("Overall sum is " + sum);
    }

    @Override
    public void part2() {

    }

    boolean backtrack(long result, long[] terms, int idx, long currentResult, String currentOps, List<String> operations) {
        if (idx == terms.length) {
            if (currentResult == result) {
                operations.add(currentOps);
                return true;
            }
            return false;
        }

        if (backtrack(result, terms, idx + 1, currentResult + terms[idx], currentOps + " + " + terms[idx], operations)) {
            return true;
        }

        if (backtrack(result, terms, idx + 1, currentResult * terms[idx], currentOps + " * " + terms[idx], operations)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        AOC7 aoc7 = new AOC7("aoc7_input");
        aoc7.run();
    }

}