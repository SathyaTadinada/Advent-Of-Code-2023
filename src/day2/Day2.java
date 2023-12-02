package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("C:\\Users\\sathy\\OneDrive\\Documents\\GitHub\\Advent Of Code 2023\\src\\day2\\day2.txt"));
        int sum = 0;

        while (in.hasNext()) {
            boolean flag = false;
            String text = in.nextLine();

            // gets the ID
            String[] arr = text.split(": ");
            int ID = Integer.parseInt(arr[0].substring(5));

            // separates the subsets of each "round" in the game
            String[] subsets = arr[1].strip()
                    .replace(";", ",")
                    .replace(" ", "")
                    .split(",");

    // Part 1

            for (String s : subsets) {
                // set a boolean flag to true if the number of cubes is less than or equal to the pre-defined limits
                if (s.contains("red")) {
                    String num = s.replace("red", "");
                    flag = Integer.parseInt(num) <= 12;
                } else if (s.contains("green")) {
                    String num = s.replace("green", "");
                    flag = Integer.parseInt(num) <= 13;
                } else if (s.contains("blue")) {
                    String num = s.replace("blue", "");
                    flag = Integer.parseInt(num) <= 14;
                }

                // if the number of cubes ever exceed the pre-defined limits, exit out
                if (!flag) {
                    break;
                }
            }

            // if all the conditions were met, add the ID to the sum
            if (flag) {
                sum += ID;
            }

    // Part 2

            // declares some counter variables
            int red = 0;
            int green = 0;
            int blue = 0;

            for (String s : subsets) {
                // updates the counter variables if the current number is larger
                if (s.contains("red")) {
                    String num = s.replace("red", "");
                    if (Integer.parseInt(num) > red) {
                        red = Integer.parseInt(num);
                    }
                } else if (s.contains("green")) {
                    String num = s.replace("green", "");
                    if (Integer.parseInt(num) > green) {
                        green = Integer.parseInt(num);
                    }
                } else if (s.contains("blue")) {
                    String num = s.replace("blue", "");
                    if (Integer.parseInt(num) > blue) {
                        blue = Integer.parseInt(num);
                    }
                }
            }
            // calculates the power by multiplying each of them, then increments the sum
            int power = red * green * blue;
            sum += power;
        }

        System.out.println("The sum is: " + sum);
    }
}
