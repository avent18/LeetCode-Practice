public class Solution {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Cell implements Comparable<Cell> {
        int time, row, col;

        Cell(int time, int row, int col) {
            this.time = time;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;
        int n = moveTime[0].length;

        int[][] result = new int[m][n];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        result[0][0] = 0;
        pq.offer(new Cell(0, 0, 0));

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();

            if (curr.row == m - 1 && curr.col == n - 1) {
                return curr.time;
            }

            for (int[] dir : directions) {
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                    int moveCost = (newRow + newCol) % 2 == 0 ? 2 : 1;
                    int wait = Math.max(moveTime[newRow][newCol] - curr.time, 0);
                    int arrivalTime = curr.time + wait + moveCost;

                    if (result[newRow][newCol] > arrivalTime) {
                        result[newRow][newCol] = arrivalTime;
                        pq.offer(new Cell(arrivalTime, newRow, newCol));
                    }
                }
            }
        }

        return -1;
    }
}
