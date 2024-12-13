package com.amayr;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Day {
    private static final ClassLoader CL = Thread.currentThread().getContextClassLoader();

    protected String input;

    protected Day(String fileName) {
        try {
            input = new String(Files.readAllBytes(Paths.get(CL.getResource(fileName).toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void part1();
    public abstract void part2();

    public void run() {
        long startTime = System.currentTimeMillis();
        part1();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time part 1: " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        part2();
        endTime = System.currentTimeMillis();
        System.out.println("Execution time part 2: " + (endTime - startTime) + " ms");
    }
}