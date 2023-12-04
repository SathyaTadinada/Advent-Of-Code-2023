package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day3 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("C:\\Users\\sathy\\OneDrive\\Documents\\GitHub\\Advent Of Code 2023\\src\\day3\\day3.txt"));

        StringBuilder schematic = new StringBuilder();
        List<String> inputLines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            schematic.append(line).append("\n");
            inputLines.add(line);
        }

        // Part 1
        /*
        int sum = 0;

        // Breaks up schematic into individual lines
        String[] lines = schematic.toString().split("\n");

        // Loop through the list horizontally and vertically
        int skips = 0;
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); x++) {
                // Skip if necessary
                if (skips > 0) {
                    skips--;
                    continue;
                }

                // skip to next character if not digit
                char currentChar = line.charAt(x);
                if (!Character.isDigit(currentChar)) {
                    continue;
                }

                // if char is digit, find the full number's length
                int length = 1;
                while (x + length < line.length() && Character.isDigit(line.charAt(x + length))) {
                    length++;
                }

                // Get the current number
                int current = Integer.parseInt(line.substring(x, x + length));

                // Get the coordinates of our search
                int min_x = Math.max(0, x - 1);
                int max_x = Math.min(line.length() - 1, x + length + 1);

                // Search the adjacent lines for symbols
                boolean valid = false;

                // Search the top line
                if (y > 0) {
                    String fragment = lines[y - 1].substring(min_x, max_x);
                    if (Pattern.compile("[^\\d.]").matcher(fragment).find()) {
                        valid = true;
                    }
                }

                // Search the middle
                if (!valid) {
                    String fragment = line.substring(min_x, max_x);
                    if (Pattern.compile("[^\\d.]").matcher(fragment).find()) {
                        valid = true;
                    }
                }

                // Search the bottom line
                if (!valid && y < lines.length - 1) {
                    String fragment = lines[y + 1].substring(min_x, max_x);
                    if (Pattern.compile("[^\\d.]").matcher(fragment).find()) {
                        valid = true;
                    }
                }

                if (valid) {
                    sum += current;
                }

                // Skip the next characters
                skips = length - 1;
            }
        }

        // Print the answer
        System.out.println("The sum is: " + sum);
         */

        // Part 2

        int sum = 0;

        String digits = "0123456789";

        // Loop through the list horizontally and vertically
        for (int y = 0; y < inputLines.size(); y++) {
            String line = inputLines.get(y);
            for (int x = 0; x < line.length(); x++) {

                // If this character is not a * symbol, skip to the next character
                char currentChar = line.charAt(x);
                if (currentChar != '*') {
                    continue;
                }

                // If the char is a * symbol, find all the part numbers next to it
                int[][] directions = { {-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1} };
                List<int[]> partNumberCoords = new ArrayList<>();
                boolean skipTop = false;
                boolean skipBottom = false;

                for (int[] dir : directions) {
                    // If this is out of bounds, continue
                    if (x + dir[0] < 0 || x + dir[0] >= line.length() || y + dir[1] < 0 || y + dir[1] >= inputLines.size()) {
                        continue;
                    }

                    char content = inputLines.get(y + dir[1]).charAt(x + dir[0]);

                    // If this is the top or bottom left, check if we should continue with the middle
                    if (dir[0] == -1 && dir[1] == -1) {
                        skipTop = digits.indexOf(content) != -1;
                    } else if (dir[0] == -1 && dir[1] == 1) {
                        skipBottom = digits.indexOf(content) != -1;
                    } else if (dir[0] == 0 && dir[1] == -1) {
                        if (skipTop) {
                            skipTop = digits.indexOf(content) != -1;
                            continue;
                        } else {
                            skipTop = digits.indexOf(content) != -1;
                        }
                    } else if (dir[0] == 0 && dir[1] == 1) {
                        if (skipBottom) {
                            skipBottom = digits.indexOf(content) != -1;
                            continue;
                        } else {
                            skipBottom = digits.indexOf(content) != -1;
                        }
                    } else if (dir[0] == 1 && dir[1] == -1) {
                        if (skipTop) {
                            continue;
                        }
                    } else if (dir[0] == 1 && dir[1] == 1) {
                        if (skipBottom) {
                            continue;
                        }
                    }

                    // If we didn't continue, we should check this cell
                    if (digits.indexOf(content) != -1) {
                        partNumberCoords.add(dir);
                    }
                }

                // If there aren't exactly two part numbers, quit
                if (partNumberCoords.size() != 2) {
                    continue;
                }

                // Start at all the part number coords, and get all the part numbers
                List<Integer> partNums = new ArrayList<>();
                for (int[] coordinate : partNumberCoords) {
                    int numY = coordinate[1] + y;

                    int startX = coordinate[0] + x;
                    while (startX > 0 && digits.indexOf(inputLines.get(numY).charAt(startX - 1)) != -1) {
                        startX--;
                    }

                    int endX = coordinate[0] + x;
                    while (endX < line.length() - 1 && digits.indexOf(inputLines.get(numY).charAt(endX + 1)) != -1) {
                        endX++;
                    }

                    partNums.add(Integer.parseInt(inputLines.get(numY).substring(startX, endX + 1)));
                }

                // Find the gear ratio
                int gearRatio = partNums.get(0) * partNums.get(1);

                sum += gearRatio;
            }
        }

        System.out.println("The sum is: " + sum);
    }
}
