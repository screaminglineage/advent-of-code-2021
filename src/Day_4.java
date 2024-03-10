import java.util.*;

public class Day_4 {
    public static class Board {
        int[] grid;
        Set<Integer> marked;

        Board(int[] grid) {
            this.grid = grid;
            this.marked = new HashSet<>();
        }

        private int find(int[] nums, int num) {
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] == num) {
                    return i;
                }
            }
            return -1;
        }

        public boolean isBingo() {
            // checking by row
            for (int row = 0; row < 5; row++) {
                boolean bingoRow = true;
                for (int col = 0; col < 5; col++) {
                    if (!this.marked.contains(row * 5 + col)) {
                        bingoRow = false;
                        break;
                    }
                }
                if (bingoRow) {
                    return true;
                }
            }
            // checking by column
            for (int col = 0; col < 5; col++) {
                boolean bingoCol = true;
                for (int row = 0; row < 5; row++) {
                    if (!this.marked.contains(row * 5 + col)) {
                        bingoCol = false;
                        break;
                    }
                }
                if (bingoCol) {
                    return true;
                }
            }
            return false;
        }

        public int unmarkedSum() {
            int sum = 0;
            for (int i = 0; i < this.grid.length; i++) {
                if (!this.marked.contains(i)) {
                    sum += this.grid[i];
                }
            }
            return sum;
        }

        public void play(int num) {
            int index = find(this.grid, num);
            if (index == -1) {
                return;
            }
            this.marked.add(index);
        }
    }

    private static int calculateScoreFirst(int[] nums, List<Board> boards) {
        for (int num : nums) {
            for (Board board : boards) {
                board.play(num);
                if (board.isBingo()) {
                    return num * board.unmarkedSum();
                }
            }
        }
        throw new RuntimeException("Unreachable!");
    }

    private static int calculateScoreLast(int[] nums, List<Board> boards) {
        int boardCount = boards.size();
        Set<Integer> wonBoards = new HashSet<>();
        for (int num : nums) {
            for (int i = 0; i < boardCount; ++i) {
                if (wonBoards.contains(i)) {
                    continue;
                }
                Board board = boards.get(i);
                board.play(num);
                if (board.isBingo()) {
                    wonBoards.add(i);
                    if (wonBoards.size() == boardCount) {
                        return num * board.unmarkedSum();
                    }
                }
            }
        }
        throw new RuntimeException("Unreachable!");
    }

    private static int[] parseNums(List<String> data) {
        String[] numsData = data.getFirst().split(",");
        int[] nums = new int[numsData.length];
        for (int i = 0; i < numsData.length; ++i) {
            nums[i] = Integer.parseInt(numsData[i]);
        }
        return nums;
    }

    private static List<Board> parseBoards(List<String> data) {
        List<Board> boards = new ArrayList<>();
        List<String> boardData = data.subList(2, data.size());
        int count = 0;
        while (count < boardData.size()) {
            int[] grid = new int[5 * 5];
            for (int row = 0; row < 5; ++row, ++count) {
                String[] rowData = boardData.get(count).split(" ");
                int rowDataIndex = 0;
                for (int col = 0; col < 5; ++col, ++rowDataIndex) {
                    // weird hack to skip empty characters due to java not having
                    // a method to split at all contiguous whitespaces
                    while (rowData[rowDataIndex].isEmpty() && rowDataIndex < rowData.length - 1) {
                        rowDataIndex += 1;
                    }
                    grid[row * 5 + col] = Integer.parseInt(rowData[rowDataIndex]);
                }
            }
            boards.add(new Board(grid));
            count += 1;
        }
        return boards;
    }

    public static int part_1(List<String> data) {
        int[] nums = parseNums(data);
        List<Board> boards = parseBoards(data);
        return calculateScoreFirst(nums, boards);
    }

    public static int part_2(List<String> data) {
        int[] nums = parseNums(data);
        List<Board> boards = parseBoards(data);
        return calculateScoreLast(nums, boards);
    }

}
