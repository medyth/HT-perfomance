package org.example.task1;

public class Task1 {
    public static int n;
    public static int m;
    public static String result;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar task1.jar 5 4");
            return;
        }

        n = Integer.parseInt(args[0]);
        m = Integer.parseInt(args[1]);

        if (n <= 0 || m <= 0) {
            System.out.println("Both n and m should be positive integers.");
            return;
        }

        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        StringBuilder path = new StringBuilder();
        int currentIndex = 0;

        for (int i = 0; i < n; i++) {
            currentIndex = (currentIndex + m - 1) % circularArray.length;
            path.append(circularArray[currentIndex]);
            circularArray = removeElement(circularArray, currentIndex);
        }

        result = path.toString();
        System.out.println("Круговой массив: " + arrayToString(circularArray));
        System.out.println("Полученный путь: " + result);
    }

    private static int[] removeElement(int[] array, int index) {
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        return newArray;
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }
}
