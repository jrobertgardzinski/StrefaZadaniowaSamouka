package pl.samouczekprogramisty.szs.gameoflife.ui;

import pl.samouczekprogramisty.szs.gameoflife.Board;
import pl.samouczekprogramisty.szs.gameoflife.Cell;

import java.io.PrintStream;

public class Console implements BoardRenerer {
    private static final String BOARD_VERTICAL_BORDER = "|";
    private static final String BOARD_HORIZONTAL_BORDER = "-";
    private static final String BOARD_CORNER = "+";

    private final Board board;
    private final PrintStream outputStream;

    public Console(Board board) {
        this(board, System.out);
    }

    public Console(Board board, PrintStream outputStream) {
        this.board = board;
        this.outputStream = outputStream;
    }

    @Override
    public void renderBoard() {
        outputStream.print(boardToString());
        outputStream.print(System.lineSeparator());
    }

    public String boardToString() {
        StringBuilder builder = new StringBuilder();
        int size = board.getSize();
        builder.append(createTopBottomLine(size));
        builder.append(System.lineSeparator());

        int columnIndex = 0;

        for (Cell cell : board.getCells()) {
            if (columnIndex == 0) {
                builder.append(BOARD_VERTICAL_BORDER);
            }
            builder.append(cell.toSimpleString());
            columnIndex++;
            if (columnIndex == size) {
                builder.append(BOARD_VERTICAL_BORDER);
                builder.append(System.lineSeparator());
                columnIndex = 0;
            }
        }

        builder.append(createTopBottomLine(size));
        return builder.toString();
    }

    private static String createTopBottomLine(int size) {
        StringBuilder line = new StringBuilder();
        line.append(BOARD_CORNER);
        for(int i = 0; i < size; i++) {
            line.append(BOARD_HORIZONTAL_BORDER);
        }
        line.append(BOARD_CORNER);
        return line.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        long oneSecond = 1000;
        int numberOfSteps = 10;

        Board board = new Board(5, Cell.live(0, 2), Cell.live(1, 2), Cell.live(2, 2), Cell.live(2, 3), Cell.live(1, 4));
        for (int stepNumber = 0; stepNumber < numberOfSteps; stepNumber++) {
            Console console = new Console(board);
            console.renderBoard();
            board = board.nextGeneration();
            Thread.sleep(oneSecond);
        }
    }
}
