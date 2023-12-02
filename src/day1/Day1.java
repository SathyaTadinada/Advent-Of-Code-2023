package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("C:\\Users\\sathy\\OneDrive\\Documents\\GitHub\\Advent Of Code 2023\\src\\day1\\day1.txt"));
        int sum = 0;

        // Part 1
        while (in.hasNext()) {
            String text = in.nextLine().strip().replaceAll("[a-z]|[A-Z]", "");

            if (text.length() == 1)
                text = text + text;
            else
                text = text.charAt(0) + "" + text.charAt(text.length() - 1);

            sum += Integer.parseInt(text);
        }

        // Part 2
        while (in.hasNext()) {
            String text = in.nextLine().strip();
            if (text.contains("one")) {
                text = text.replace("one", "o1e");
            }
            if (text.contains("two")) {
                text = text.replace("two", "t2o");
            }
            if (text.contains("three")) {
                text = text.replace("three", "t3e");
            }
            if (text.contains("four")) {
                text = text.replace("four", "f4r");
            }
            if (text.contains("five")) {
                text = text.replace("five", "f5e");
            }
            if (text.contains("six")) {
                text = text.replace("six", "s6x");
            }
            if (text.contains("seven")) {
                text = text.replace("seven", "s7n");
            }
            if (text.contains("eight")) {
                text = text.replace("eight", "e8t");
            }
            if (text.contains("nine")) {
                text = text.replace("nine", "n9e");
            }

            text = text.replaceAll("[a-z]|[A-Z]", "");

            if (text.length() == 1) {
                text = text + text;
            } else {
                text = text.charAt(0) + "" + text.charAt(text.length() - 1);
            }

            sum += Integer.parseInt(text);
        }

        System.out.println("The sum is: " + sum);
    }
}
