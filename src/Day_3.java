import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Day_3 {
    static class ColumnCount {
        public int zero = 0;
        public int one = 0;
    }

    public static int part_1(List<String> data) {
        int bitWidth = data.getFirst().length();
        ColumnCount[] counts = new ColumnCount[bitWidth];
        Arrays.setAll(counts, i -> new ColumnCount());

        for (String line: data) {
            for (int j = 0; j < bitWidth; ++j) {
                if (line.charAt(j) == '0') {
                    counts[j].zero += 1;
                } else if (line.charAt(j) == '1') {
                    counts[j].one += 1;
                }
            }
        }

        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for (ColumnCount count : counts) {
            gammaRate.append(count.one > count.zero ? '1' : '0');
            epsilonRate.append(count.one > count.zero ? '0' : '1');
        }
        return Integer.parseInt(String.valueOf(gammaRate), 2) * Integer.parseInt(String.valueOf(epsilonRate), 2);
    }

    public static int calculateCriteria(List<String> data, int bitWidth, boolean mostCommon) {
        Set<Integer> ignoredIndices = new HashSet<>();
        for (int col = 0; col < bitWidth; ++col) {
            Set<Integer> zeroStart = new HashSet<>();
            Set<Integer> oneStart = new HashSet<>();
            for (int row = 0; row < data.size(); ++row) {
                if (ignoredIndices.contains(row)) {
                    continue;
                }
                if (ignoredIndices.size() == data.size() - 1) {
                    break;
                }
                if (data.get(row).charAt(col) == '0') {
                    zeroStart.add(row);
                } else {
                    oneStart.add(row);
                }
            }
            if (oneStart.size() >= zeroStart.size()) {
                ignoredIndices.addAll(mostCommon? zeroStart: oneStart);
            } else {
                ignoredIndices.addAll(mostCommon? oneStart: zeroStart);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            if (!ignoredIndices.contains(i)) {
                return Integer.parseInt(data.get(i), 2);
            }
        }
        throw new RuntimeException("Unreachable!");
    }
    public static int part_2(List<String> data) {
        int bitWidth = data.getFirst().length();
        int oxygen = calculateCriteria(data, bitWidth, true);
        int co2 = calculateCriteria(data, bitWidth, false);
        return oxygen*co2;
    }
}
