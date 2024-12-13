package com.malex1337.aoc24.aoc9;

import com.malex1337.Day;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class AOC9 extends Day {
    private static final int FREE = -1;
    int[] values;
    Queue<Integer> freeIdx = new LinkedBlockingQueue<>();
    int lastValueIdx;

    protected AOC9(String fileName) {
        super(fileName);
        fillArrays();
        lastValueIdx = values.length - 1;
    }

    @Override
    public void part1() {
        updateLastValueIdx();

        while (lastValueIdx > freeIdx.element()) {
            values[freeIdx.poll()] = values[lastValueIdx];
            values[lastValueIdx] = FREE;
            updateLastValueIdx();
        }

        long sum = 0;
        for (int i = 0; values[i] != FREE; i++) {
            sum += (long) values[i] * i;
        }

        System.out.println(Arrays.toString(values));
        System.out.println(sum);
    }

    @Override
    public void part2() {

    }

    private void updateLastValueIdx() {
        while (values[lastValueIdx] == FREE) {
            lastValueIdx--;
        }
    }

    private void fillArrays() {
        var chars = input.toCharArray();
        int sum = 0;
        for (char c : chars) {
            sum += Character.getNumericValue(c);
        }
        values = new int[sum];

        int id = 0;
        int lastIdx = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < Character.getNumericValue(chars[i]); j++) {
                    values[lastIdx++] = id;
                }
                id++;
            } else {
                for (int j = 0; j < Character.getNumericValue(chars[i]); j++) {
                    values[lastIdx] = FREE;
                    freeIdx.add(lastIdx);
                    lastIdx++;
                }
            }
        }
    }

    public static void main(String[] args) {
        AOC9 aoc9 = new AOC9("aoc9_input");
        aoc9.run();
    }
}