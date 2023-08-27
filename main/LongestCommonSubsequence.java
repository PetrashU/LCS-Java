
import java.util.ArrayList;
import java.util.List;

class LongestCommonSubsequence {

    private enum Direction {
        UP, LEFT, NEXT
    }

    private static class Node {
        int numb;
        Direction dir = null;
    }
    private static class Indexes{
        int i;
        int j;

        public Indexes(int i, int j){
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Indexes indexes = (Indexes) o;
            return i == indexes.i && j == indexes.j;
        }
    }
    private final List<Indexes> path = new ArrayList<>();
    private final String firstStr, secondStr;
    private final Node[][] matrix;
    private final int width, length;

    private boolean LCSfound;
    private boolean lcsEmpty;


    public LongestCommonSubsequence(String firstStr, String secondStr) {
        if (firstStr == null || secondStr == null) {
            throw new IllegalArgumentException("Strings can not be NULL!");
        }
        if (firstStr.isEmpty() || secondStr.isEmpty()) {
            throw new IllegalArgumentException("Strings can not be empty!");
        }
        this.firstStr = firstStr;
        this.secondStr = secondStr;

        width = firstStr.length();
        length = secondStr.length();
        matrix = new Node[length + 1][width + 1];

        for (int i = 0; i < length + 1; i++) {
            for (int j = 0; j < width + 1; j++) {
                matrix[i][j] = new Node();
                if (i == 0 || j == 0) {
                    matrix[i][j].numb = 0;
                } else if (firstStr.charAt(j - 1) == secondStr.charAt(i - 1)) {
                    matrix[i][j].numb = matrix[i - 1][j - 1].numb + 1;
                    matrix[i][j].dir = Direction.NEXT;
                } else {
                    if (matrix[i - 1][j].numb < matrix[i][j - 1].numb) {
                        matrix[i][j].numb = matrix[i][j - 1].numb;
                        matrix[i][j].dir = Direction.LEFT;
                    } else {
                        matrix[i][j].numb = matrix[i - 1][j].numb;
                        matrix[i][j].dir = Direction.UP;
                    }
                }
            }
        }

    }

    public String findLCS() {
        StringBuilder sb = new StringBuilder();

        int i = length;
        int j = width;

        while (i > 0 && j > 0) {
            path.add(new Indexes(i, j));
            if (matrix[i][j].dir == Direction.NEXT) {
                sb.append(firstStr.charAt(j - 1));
                i--;
                j--;
            } else if (matrix[i][j].dir == Direction.UP) {
                i--;
            } else j--;
        }

        LCSfound = true;
        String lcs = sb.reverse().toString();
        lcsEmpty = lcs.isEmpty();
        return lcs;
    }

    public void display() {
        if (!LCSfound) {
            findLCS();
        }

        lineSeparation();

        System.out.print("|       |     |");
        for (int i = 0; i < width; i++) {
            System.out.print("     |");
        }
        newLine();

        System.out.print("|       |     |");
        for (int i = 0; i < width; i++) {
            System.out.printf(" %2s  |", checkForEscapes(firstStr.charAt(i)));
        }
        newLine();

        afterLine();
        lineSeparation();

        for (int i = 0; i < length + 1; i++) {
            System.out.print("|       ");
            for (int j = 0; j < width + 1; j++) {
                if (j == 0) {
                    System.out.print("|     |");
                } else if (!lcsEmpty && path.contains(new Indexes(i, j))) {
                    if (matrix[i][j].dir == Direction.UP) {
                        System.out.print("  ^  |");
                    } else if (matrix[i][j].dir == Direction.NEXT) {
                        System.out.print("\\    |");
                    } else System.out.print("     |");
                } else
                    System.out.print("     |");
            }
            newLine();
            System.out.print("|");
            if (i == 0) {
                System.out.print("       |");
            } else {
                System.out.printf("  %2s   |", checkForEscapes(secondStr.charAt(i - 1)));
            }
            for (int j = 0; j < width + 1; j++) {
                if ( !path.contains(new Indexes(i, j)) || matrix[i][j].dir == null || matrix[i][j].dir != Direction.LEFT) {
                    System.out.printf(" %2d  |", matrix[i][j].numb);
                } else {
                    System.out.printf("<%2d  |", matrix[i][j].numb);
                }
            }
            newLine();
            afterLine();
            lineSeparation();
        }
    }

    private void lineSeparation() {
        System.out.print("+-------+");
        for (int i = 0; i < width + 1; i++)
            System.out.print("-----+");
        System.out.println();
    }

    private void afterLine() {
        System.out.print("|       |     |");
        for (int i = 0; i < width; i++)
            System.out.print("     |");
        System.out.println();
    }

    private void newLine() {
        System.out.println();
    }

    private String checkForEscapes(char i) {
        switch (i) {
            case '\n':
                return "\\n";
            case '\t':
                return "\\t";
            case '\r':
                return "\\r";
            case '\f':
                return "\\f";
            case '\b':
                return "\\b";
            case '"':
                return "\"";
            default:
                return String.valueOf(i);
        }
    }
}
