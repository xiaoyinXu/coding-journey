package com.xxywebsite.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class FiveChess extends JFrame {
    private int state = 0;
    private final static int BLACK = -1;
    private final static int BLANK = 0;
    private final static int WHITE = 1;
    private List<List<Integer>> board;
    private final static int GAP = 50;
    private final static int X = 15;
    private final static int Y = 15;
    private final static int OFFSET = 100;


    private int player;

    public FiveChess() {
        init();
    }


    private void init() {
        player = BLACK;

        // initial board
        board = new ArrayList<>();
        for (int i = 0; i < X; ++i) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < Y; ++j) {
                list.add(BLANK);
            }
            board.add(list);
        }

        this.setSize((X - 1) * GAP + 2 * OFFSET, (Y - 1) * GAP + 2 * OFFSET);
        this.setBackground(Color.GREEN);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int row = (int) Math.round((y - OFFSET) / (double) GAP);
                int col = (int) Math.round((x - OFFSET) / (double) GAP);

                System.out.println(String.format("(%d, %d)", row, col));

                if (row >= 0 && col >= 0 && row < X && col < Y) {
                    Integer num = board.get(row).get(col);
                    if (num == BLANK) {

                        board.get(row).set(col, player);
                        repaint();

                        if (finished(player, row, col)) {
                            System.out.println(String.format("%s胜利", player == WHITE ? "白棋" : "黑棋"));
                            System.exit(0);
                        }

                        // 切换
                        player = player == BLACK ? WHITE : BLACK;

                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private boolean finished(int player, int row, int col) {
        int[][] directions = {{0, 1}, {1, -1}, {1, 0}, {1, 1}};

        // 4个方向寻找，是否有5颗一样的子
        for (int[] direction : directions) {
            int offsetX = direction[0];
            int offsetY = direction[1];
            int count = 1;

            int rowPos = row + offsetY;
            int colPos = col + offsetX;

            while (rowPos >= 0 && rowPos < X && colPos >= 0 && colPos < Y && board.get(rowPos).get(colPos) == player) {
                count++;
                rowPos += offsetY;
                colPos += offsetX;
            }

            offsetX = -offsetX;
            offsetY = -offsetY;
            rowPos = row + offsetY;
            colPos = col + offsetX;
            while (rowPos >= 0 && rowPos < X && colPos >= 0 && colPos < Y && board.get(rowPos).get(colPos) == player) {
                count++;
                rowPos += offsetY;
                colPos += offsetX;
            }
            if (count >= 5) {
                return true;
            }
        }

        return false;

    }

    @Override
    public void paint(Graphics g) {
        int x = board.size();
        int y = board.get(0).size();

        int WIDTH = this.getWidth();
        int HEIGHT = this.getHeight();


        // 画棋子
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                Integer color = board.get(i).get(j);
                int offsetX = j * GAP + OFFSET - (int) (0.5 * GAP);
                int offsetY = i * GAP + OFFSET - (int) (0.5 * GAP);
                if (color == WHITE) {
                    g.setColor(Color.WHITE);
                    g.fillOval(offsetX, offsetY, GAP, GAP);
                } else if (color == BLACK) {
                    g.setColor(Color.BLACK);
                    g.fillOval(offsetX, offsetY, GAP, GAP);
                }

            }
        }

        // only process once
        if (state == 0) {
            state++;
            g.setColor(Color.YELLOW);
            // 画横线
            for (int i = 0; i <= x - 1; ++i) {
                g.drawLine(OFFSET, i * GAP + OFFSET, WIDTH - OFFSET, i * GAP + OFFSET);
            }

            // 画竖线
            for (int i = 0; i <= y - 1; ++i) {
                g.drawLine(i * GAP + OFFSET, OFFSET, i * GAP + OFFSET, HEIGHT - OFFSET);
                ;
            }

        }
    }

    public void start() {
        this.setVisible(true);
    }


    public static void main(String[] args) {
        new FiveChess().start();
    }

}
