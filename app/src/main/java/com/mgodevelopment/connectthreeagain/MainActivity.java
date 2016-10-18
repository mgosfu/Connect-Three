package com.mgodevelopment.connectthreeagain;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.mgodevelopment.connectthreeagain.R.id.btnPlayAgain;
import static com.mgodevelopment.connectthreeagain.R.id.tvPlayer;

public class MainActivity extends AppCompatActivity {

    private boolean gameOver;
    private boolean redPlayer;
    private ArrayList<BoardPosition> mBoardPositions;
    private Board mBoard;
    private int[][] mWinningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    // 0 means unplayed, 1 = red, 2 = yellow
    public int[] mGameState;

    public Button btnStartOver;
    public TextView player;

    private void initBoard() {
        mGameState = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gameOver = false;
        redPlayer = true;
        mBoardPositions = new ArrayList<>();
        mBoard = new Board();
        player.setBackgroundColor(Color.BLACK);
    }

    private void resetTokens() {

        GridLayout glTicTacToe = (GridLayout) findViewById(R.id.gvBoard);
        for (int i = 0; i < glTicTacToe.getChildCount(); i++) {
            ((ImageView) glTicTacToe.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (TextView) findViewById(tvPlayer);
        btnStartOver = (Button) findViewById(btnPlayAgain);
        btnStartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBoard();
                resetTokens();
                btnStartOver.setVisibility(View.GONE);
            }
        });

        initBoard();

    }

    public void dropIn(View view) {

        if (!gameOver) {

            ImageView counter = (ImageView) view;
            int positionTag = Integer.parseInt(counter.getTag().toString());

            if (mGameState[positionTag] == 0) {
                counter.setTranslationY(-1000f);
                counter.setImageResource(redPlayer ? R.drawable.red : R.drawable.yellow);
                counter.animate().rotation(360).translationYBy(1000f).setDuration(250);

                BoardPosition position = new BoardPosition(counter, true, positionTag, redPlayer ? "Red" : "Yellow");
                mBoardPositions.add(position);
                mBoard.setPositions(mBoardPositions);

                mGameState[positionTag] = redPlayer ? 1 : 2;

                if (isInWinningPosition()) {
                    //if (checkForWin()) {
                    updateGameOverMessage(redPlayer ? "Red Wins!" : "Yellow Wins!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    updateGameOverMessage(getText(R.string.tied_game_message).toString());
                    gameOver = true;
                } else {
                    redPlayer = !redPlayer;
                    player.setText(redPlayer ? "Red's Turn" : "Yellow's Turn");
                    player.setTextColor(redPlayer ? Color.RED : Color.YELLOW);
                }

                if (gameOver) {
                    btnStartOver.setVisibility(View.VISIBLE);
                }

            } else {
                player.setText("Position already played");
            }

        }

    }

    private void updateGameOverMessage(String message) {
        player.setText(message);
        player.setTextColor(Color.BLACK);
        player.setBackgroundColor(Color.WHITE);
    }

    public boolean isInWinningPosition() {
        for (int[] winningPosition : mWinningPositions) {

            if (checkRowCol(mGameState[winningPosition[0]], mGameState[winningPosition[1]], mGameState[winningPosition[2]])) {
                return true;
            }

        }

        return false;
    }

    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < 9; i++) {
            if (mGameState[i] == 0) {
                isFull = false;
            }
        }

        return isFull;
    }

    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(int c1, int c2, int c3) {
        return ((c1 != 0) && (c1 == c2) && (c2 == c3));
    }

}
