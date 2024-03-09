import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static List<String> readFile(String filename) throws IOException {
        return Files.readAllLines(Path.of(filename));
    }

    // TODO: pass in cmdline args to set day
    public static void main(String[] args) throws IOException {
        List<String> lines =  readFile("input_data/day_3.txt");
        int answer = Day_3.part_2(lines);
        System.out.println(answer);
    }
}