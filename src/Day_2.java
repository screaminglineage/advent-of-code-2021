import java.util.List;

public class Day_2 {
    public static int part_1(List<String> data) {
        int horizontal = 0;
        int depth = 0;
        for (String line : data) {
            String[] lineSplit = line.split(" ");
            switch (lineSplit[0]) {
                case "forward":
                    horizontal += Integer.parseInt(lineSplit[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(lineSplit[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(lineSplit[1]);
                    break;
            }
        }
        return horizontal * depth;
    }
    public static int part_2(List<String> data) {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (String line : data) {
            String[] lineSplit = line.split(" ");
            switch (lineSplit[0]) {
                case "forward":
                    int value = Integer.parseInt(lineSplit[1]);
                    horizontal += value;
                    depth += aim * value;
                    break;
                case "up":
                    aim -= Integer.parseInt(lineSplit[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(lineSplit[1]);
                    break;
            }
        }
        return horizontal * depth;
    }
}
