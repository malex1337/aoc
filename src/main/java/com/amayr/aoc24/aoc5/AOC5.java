package com.amayr.aoc24.aoc5;

import com.amayr.Day;

import java.util.*;

public class AOC5 extends Day {
    public AOC5(String fileName) {
        super(fileName);
    }

    @Override
    public void part1() {
        Map<Integer, Page> pages = new HashMap<>();
        List<int[]> updates = new ArrayList<>();

        fillData(pages, updates);

        int sumOfMiddles = 0;
        for (int[] update : updates) {
            boolean ordered = true;

            for (int updatePage = 0; updatePage < update.length; updatePage++) {
                // any before page is bigger
                for (int preUpdatePages = 0; preUpdatePages < updatePage; preUpdatePages++) {
                    if (pages.get(update[updatePage]).after.contains(update[preUpdatePages])) {
                        ordered = false;
                    }
                }

                // any after page is less
                for (int postUpdatePages = updatePage + 1; postUpdatePages < update.length; postUpdatePages++) {
                    if (pages.get(update[updatePage]).before.contains(update[postUpdatePages])) {
                        ordered = false;
                    }
                }
            }

            if (ordered) {
                sumOfMiddles += update[update.length / 2];
            }

        }

        System.out.println("Sum of middles: " + sumOfMiddles);
    }

    @Override
    public void part2() {

    }

    private void fillData(Map<Integer, Page> pages, List<int[]> updates) {
        var splitted = input.split("\n");

        boolean updatesReached = false;
        int idx = 0;
        while (idx < splitted.length) {
            if (splitted[idx].isEmpty()) {
                updatesReached = true;
                idx++;
            }

            if (!updatesReached) {
                var split = splitted[idx].split("\\|");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                if (!pages.containsKey(x)) {
                    pages.put(x, new Page(x));
                }
                if (!pages.containsKey(y)) {
                    pages.put(y, new Page(y));
                }
                pages.get(x).after.add(y);
                pages.get(y).before.add(x);
            } else {
                var split = splitted[idx].split(",");
                updates.add(Arrays.stream(split).mapToInt(Integer::parseInt).toArray());
            }
            idx++;
        }
    }

    public static void main(String[] args) {
        AOC5 aoc4 = new AOC5("aoc5_input");
        aoc4.run();
    }

    static class Page {
        int number;
        List<Integer> before = new ArrayList<>();
        List<Integer> after = new ArrayList<>();

        Page(int number) {
            this.number = number;
        }
    }
}