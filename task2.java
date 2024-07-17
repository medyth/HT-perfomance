package org.example.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar task2.jar test1.txt test2.txt");
            return;
        }

        String circleDataFile = args[0];
        String pointsDataFile = args[1];

        double centerX, centerY, radius;

        try (BufferedReader circleReader = new BufferedReader(new FileReader(circleDataFile))) {
            String[] centerCoordinates = circleReader.readLine().split(" ");
            centerX = Double.parseDouble(centerCoordinates[0]);
            centerY = Double.parseDouble(centerCoordinates[1]);
            radius = Double.parseDouble(circleReader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading circle data file: " + e.getMessage());
            return;
        }

        try (BufferedReader pointsReader = new BufferedReader(new FileReader(pointsDataFile))) {
            String point;
            while ((point = pointsReader.readLine()) != null) {
                String[] pointCoordinates = point.split(" ");
                double pointX = Double.parseDouble(pointCoordinates[0]);
                double pointY = Double.parseDouble(pointCoordinates[1]);
                int position = getPointPosition(centerX, centerY, radius, pointX, pointY);
                System.out.println(position);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading points data file: " + e.getMessage());
        }
    }

    private static int getPointPosition(double centerX, double centerY, double radius,
                                        double pointX, double pointY) {
        double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

        if (Math.abs(distance - radius) < 1e-9) {
            return 0; // Точка лежит на окружности
        } else if (distance < radius) {
            return 1; // Точка внутри окружности
        } else {
            return 2; // Точка снаружи окружности
        }
    }
}
