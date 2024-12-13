package com.amayr.aoc24.aoc3;

import com.amayr.Day;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AOC3 extends Day {
    private final Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
    private int matches = 0;
    private int sum = 0;

    public AOC3(String fileName) {
        super(fileName);
    }

    @Override
    public void part1() {
        String[] lines = input.split("\n");
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                sum += x * y;
                matches++;
            }
        }

        System.out.println("Found " + matches + " matches. Sum is : " + sum);
    }

    @Override
    public void part2() {

    }

    public static void main(String[] args) {
        AOC3 aoc3 = new AOC3("aoc3_input");
        aoc3.run();
    }
}