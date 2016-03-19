package MainGame.model;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/***********************************************
 * Created by Cameron Sprowls on 3/18/2016.
 *
 * Class for the pawn piece
 **********************************************/
public class Pawn extends ChessPiece{

    private boolean hasMoved;

    public Pawn(Player p) {
        super(p);
        hasMoved = false;
    }

    @Override
    public String type() {
        return null;
    }

    @Override
    public boolean isValidMove (Move m, IChessPiece[][] board) {
        if (!super.isValidMove(m, board))
            return false;
        else {
            //see if they are moving diagonally, one space, and capture
            if((m.toColumn == m.fromColumn+1 || m.toColumn == m.toColumn-1) &&
                    (m.toRow == m.fromRow+1 || m.toRow == m.fromRow-1) &&
                    board[m.toRow][m.toColumn] != null &&
                    board[m.toRow][m.toColumn].player() != this.player()){
                return true;
            }

            //this check needs to go after the other check
            //check to see if they are trying to move 2 spaces
            if((!hasMoved && (m.toRow == m.toRow+2)) &&
                    m.fromColumn == m.toColumn){
                hasMoved = true;
                return true;
            }
            //check to see if they are trying to move 1 space
            if((m.toRow == m.fromRow+1 || m.toRow == m.fromRow-1) &&
                    m.fromColumn == m.toColumn) {
                hasMoved = true;
                return true;
            }



            //I think that's it... for now
            return false;
        }
    }
}