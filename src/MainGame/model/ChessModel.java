package MainGame.model;

import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/*********************************************************************
 * Created by Cameron Sprowls and Josh Eldridge on 3/20/2016.
 *********************************************************************/
public class ChessModel implements IChessModel {

    private int numRows, numColumns;
    private Player currentPlayer;
    private IChessPiece[][] board;
    private boolean inCheck;

    public ChessModel()
    {
        numRows = 8;
        numColumns = 8;
        currentPlayer = Player.WHITE;
        board = new IChessPiece[numRows][numColumns];
        board[0][0] = new Rook(Player.WHITE);
        board[0][1] = new Knight(Player.WHITE);
        board[0][2] = new Bishop(Player.WHITE);
        board[0][3] = new Queen(Player.WHITE);
        board[0][4] = new King(Player.WHITE);
        board[0][5] = new Bishop(Player.WHITE);
        board[0][6] = new Knight(Player.WHITE);
        board[0][7] = new Rook(Player.WHITE);
        for(int i = 0; i <= 7; i++)
        {
            board[1][i] = new Pawn(Player.WHITE);
            board[6][i] = new Pawn(Player.BLACK);
        }
        board[7][0] = new Rook(Player.BLACK);
        board[7][1] = new Knight(Player.BLACK);
        board[7][2] = new Bishop(Player.BLACK);
        board[7][3] = new Queen(Player.BLACK);
        board[7][4] = new King(Player.BLACK);
        board[7][5] = new Bishop(Player.BLACK);
        board[7][6] = new Knight(Player.BLACK);
        board[7][7] = new Rook(Player.BLACK);

    }

    public ChessModel (int[] positions, IChessPiece[] pieces){
        numRows = 8;
        numColumns = 8;
        currentPlayer = Player.WHITE;
        board = new IChessPiece[numRows][numColumns];
        int counter = 0;
        for(IChessPiece i : pieces){
            board[positions[counter]][positions[counter+1]] = i;
            counter += 2;
        }
    }

    @Override
    public int numRows() {
        return numRows;
    }

    @Override
    public int numColumns() {
        return numColumns;
    }

    @Override
    public IChessPiece pieceAt(int row, int col) {
        return board[row][col];
    }

    @Override
    public boolean isComplete()
    {
        // TODO: Finish this
        return false;
    }

    @Override
    public boolean isValidMove(Move move) throws IndexOutOfBoundsException {
        if((board[move.fromRow][move.fromColumn].player() == this.currentPlayer() &&
                board[move.fromRow][move.fromColumn].isValidMove(move, board)))
        return true;
        else
        return false;
    }

    @Override
    public void move(Move move) throws IllegalArgumentException, IllegalStateException {
        if(isValidMove(move))
        {
            board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
            if(currentPlayer() == Player.WHITE)
                currentPlayer = Player.BLACK;
            else
                currentPlayer = Player.WHITE;
        }
        else
            throw new IllegalStateException();
    }

    @Override
    public boolean inCheck()
    {
        // TODO: Finish this
        //we can do this one of two ways...
        //check every other piece and see if they can take the king
        //or check all of the spaces around the king and see if anything is able to take it
        //I prefer the first method
        return false;
    }

    @Override
    public Player currentPlayer() {
        return currentPlayer;
    }
}
