import java.util.List;

public class Day_1 {
    public static int part_1(List<String> data) {
        int count = 0;
        for (int i = 0; i < data.size() - 1; i++) {
            if (Integer.parseInt(data.get(i+1)) > Integer.parseInt(data.get(i))) {
                count += 1;
            }
        }
        return count;
    }

    public static int part_2(List<String> data) {
        int count = 0;
        int windowSum = 0;

        for (int i = 0; i < data.size() - 2; i++) {
            int windowSumNew = 0;
            for (int j = 0; j < 3; j++) {
                windowSumNew += Integer.parseInt(data.get(i+j));
            }
            if (i != 0 && windowSumNew > windowSum) {
                count += 1;
            }
            windowSum = windowSumNew;
        }
        return count;
    }
}
