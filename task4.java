package org.example.task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -cp path/to/classes org.example.task4.Task4 test.txt");
            return;
        }

        String inputFile = args[0];
        List<Integer> nums = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int num = Integer.parseInt(line);
                nums.add(num);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        int moves = minMovesToSameNumber(nums);
        System.out.println(moves);
    }

    private static int minMovesToSameNumber(List<Integer> nums) {
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);
        int moves = 0;

        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }
}
