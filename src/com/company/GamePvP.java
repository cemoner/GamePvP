package com.company;

import java.util.Scanner;

public class GamePvP {
    int boardsize;
    String[][] board;
    boolean continuity = true;

    public GamePvP(int boardsize) {
        this.boardsize = boardsize - 1;
    }

    public void createBoard() {
        String[][] board = new String[boardsize + 1][boardsize + 1];
        for (int i = 0; i <= boardsize; i++) {
            for (int l = 0; l <= boardsize; l++) {
                board[i][l] = "_";
            }
        }
        this.board = board;
    }

    public void board() {
        for (int i = 0; i <= boardsize; i++) {
            for (int n = 0; n <= boardsize; n++) {
                System.out.print(board[i][n] + "|");
            }
            System.out.println();
        }
    }

    public void game() {
        Player1 player1 = new Player1("X");
        Player2 player2 = new Player2("O");
        GameCheck gameCheck = new GameCheck();
        createBoard();
        board();
        try {
            while (true) {
                player1.move();
                gameCheck.allinone();
                if (!continuity) {
                    System.out.println("Player 1 Won!");
                    board();
                    break;
                }
                board();
                player2.move();
                gameCheck.allinone();
                if (!continuity) {
                    System.out.println("Player 2 Won!");
                    board();
                    break;
                }
                board();
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong.");
        }
    }

    public class Player1 {
        String type;

        public Player1(String type) {
            this.type = type;
        }

        public void move() {
            boolean carryon = true;
            System.out.println("Player 1 Enter row number :");
            Scanner row = new Scanner(System.in);
            int rowint = row.nextInt() - 1;
            if (rowint > boardsize || rowint < 0) {
                System.out.println("Invalid row number.Start over.");
                carryon = false;
                move();
            }
            if (carryon) {
                System.out.println("Player 1 Enter column number :");
                Scanner column = new Scanner(System.in);
                int columnint = column.nextInt() - 1;
                if (columnint > boardsize || columnint < 0) {
                    System.out.println("Invalid column number.Start over.");
                    carryon = false;
                    move();
                }
                if (carryon) {
                    try {
                        if (board[rowint][columnint].equals("_")) {
                            board[rowint][columnint] = type;
                        } else {
                            System.out.println("That location is filled");
                            move();
                        }
                    } catch (Exception exception) {
                        System.out.println("Something is wrong.Start over");
                        move();
                    }
                }
            }

        }
    }

    public class Player2 {
        String type;

        public Player2(String type) {
            this.type = type;
        }

        public void move() {
            boolean carryon = true;
            System.out.println("Player 2 Enter row number :");
            Scanner row = new Scanner(System.in);
            int rowint = row.nextInt() - 1;
            if (rowint > boardsize || rowint < 0) {
                System.out.println("Invalid row number.Start over.");
                carryon = false;
                move();
            }
            if (carryon) {
                System.out.println("Player 2 Enter column number :");
                Scanner column = new Scanner(System.in);
                int columnint = column.nextInt() - 1;
                if (columnint > boardsize || columnint < 0) {
                    System.out.println("Invalid column number.Start over.");
                    carryon = false;
                    move();
                }
                if (carryon) {
                    try {
                        if (board[rowint][columnint].equals("_")) {
                            board[rowint][columnint] = type;
                        } else {
                            System.out.println("That location is filled");
                            move();
                        }
                    } catch (Exception exception) {
                        System.out.println("Something is wrong.Start over");
                        move();
                    }
                }
            }

        }
    }

    public class GameCheck {
        public void rowCheck() {
            int counter = -1;
            for (int i = 0; i <= boardsize; i++) {
                for (int j = 0; j <= boardsize; j++) {
                    if (board[i][i].equals(board[i][j]) && !board[i][i].equals("_")) {
                        counter += 1;
                    }
                }
                if (counter == boardsize) {
                    continuity = false;
                    break;
                }
                counter = -1;
            }
        }

        public void columnCheck() {
            int counter = -1;
            for (int i = 0; i <= boardsize; i++) {
                for (int j = 0; j <= boardsize; j++) {
                    if (board[i][i].equals(board[j][i]) && !board[i][i].equals("_")) {
                        counter += 1;
                    }
                }
                if (counter == boardsize) {
                    continuity = false;
                }
                counter = -1;
            }
        }

        public void leftDiagonalCheck() {
            int counter = -1;
            for (int i = 0; i <= boardsize; i++) {
                if (board[0][0].equals(board[i][i]) && !board[0][0].equals("_")) {
                    counter += 1;
                }
            }
            if (counter == boardsize) {
                continuity = false;
            }
        }

        public void rightDiagonalCheck() {
            int counter = -1;

            if (!board[0][boardsize].equals("_")) {
                for (int i = 0; i <= boardsize; i++) {
                    if (board[0][boardsize].equals(board[i][boardsize - i])) {
                        counter += 1;
                    }
                }
                if (counter == boardsize) {
                    continuity = false;
                }
            }
        }

        public void allinone() {
            rowCheck();
            columnCheck();
            leftDiagonalCheck();
            rightDiagonalCheck();
        }
    }
}
