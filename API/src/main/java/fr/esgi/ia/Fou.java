package fr.esgi.ia;

import java.util.ArrayList;

/**
 * This class represent the Bishop piece.
 * 
 * @author CŽdric TESNIERE
 */
public class Fou extends Piece {
    
    /** Creates a new instance of Bishop from it's ID*/
    public Fou (int ID) {
    	
    	super();
    	
        setId(ID);
        setColour(Helper.IsEven(ID));
        
        if (isColour() == false) {
            setValue(-325);
        }
        else {
            setValue(325);
        }
        
        //Starting position
        String square = getStartingPosition(ID);
        setX(Helper.getXfromString(square));
        setY(Helper.getYfromString(square));
    }
    
    /**
     * Used when a chessboard must be cloned.
     */
    @Override
    public Object clone() {
    	
        Fou myClone = new Fou(this.getId());
        myClone.setInDanger(this.isInDanger());
        myClone.setEnemy(this.getEnemy());
        myClone.setMoved(this.isMoved());
        myClone.setPosition(this.getX(),this.getY());
        myClone.setValPos(this.getValPos());
        return myClone;
    }
    
    /**
     * The Bishop can move until it finds an enemy of an ally piece.
     * We give all possible moves, not the good ones.
     * 
     * @param _chessboard The actual chessboard
     * @return An array of all possible moves (not the good ones!)
     */
    @Override
    public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {
    	
        int toX = 0, toY = 0;
        ArrayList<Move> moves = new ArrayList <Move>();
        
        //4 direction
        for (int direction = 0; direction < 4; direction++) {
        	
        	//Max 8 moves
            for (int length = 1; length < 9; length++) {
            	
                if (direction == 0) {
                    toX=getX()+length;
                    toY=getY()+length;
                }
                if (direction == 1) {
                    toX=getX()-length;
                    toY=getY()+length;
                }
                if (direction == 2) {
                    toX=getX()-length;
                    toY=getY()-length;
                }
                if (direction == 3) {
                    toX=getX()+length;
                    toY=getY()-length;
                }
                
                //TODO: Change checkThis to return a Boolean
                Move move = checkThis(toX, toY, _chessboard,moves);
                if (move == null){
                    break; //Stop going in this direction
                }
            }
        }

        return moves;
    }
    
    /**
     * Test the possible move.
     * 
     * @param _toX x destination
     * @param _toY y destination
     * @param chessboard actual chessboard
     * @return A Move or NULL.
     */
    private Move checkThis(int _toX, int _toY, Chessboard chessboard, ArrayList<Move> moves){
    	
    	Piece destination = chessboard.getPieceMuov(_toX, _toY);
    	Move move;
    	
        if (destination != null) {
           if (destination.isColour() != this.isColour()) {
               move = new Move(getX(), getY(), _toX, _toY, this.isColour());
               if (move.isValid()){
            	   moves.add(move);
               }
           }
           return null;
        }
        else {
           move = new Move(getX(),getY(), _toX, _toY, this.isColour());
           if (move.isValid()){
               moves.add(move);
           }
        }
        
        return move;
    }
    
    /*
    private void initPositionValue(){
    	
        int r0=7;
        int r1=6;
        int r2=5;
        int r3=4;
        int r4=3;
        int r5=2;
        int r6=1;
        int r7=0;
        
        if (isColour()) {
            r0=0;
            r1=1;
            r2=2;
            r3=3;
            r4=4;
            r5=5;
            r6=6;
            r7=7;
        }
            
        for (int j=0; j<8; j++)
        	valPos[r0][j] = 0;

        valPos[r1][0] = 1;
        valPos[r1][1] = 1;
        valPos[r1][2] = 2;
        valPos[r1][3] = 2;
        valPos[r1][4] = 2;
        valPos[r1][5] = 2;
        valPos[r1][6] = 1;
        valPos[r1][7] = 1;

        valPos[r2][0] = 2;
        valPos[r2][1] = 3;
        valPos[r2][2] = 3;
        valPos[r2][3] = 5;
        valPos[r2][4] = 5;
        valPos[r2][5] = 3;
        valPos[r2][6] = 3;
        valPos[r2][7] = 2;

        valPos[r3][0] = 8;
        valPos[r3][1] = 11;
        valPos[r3][2] = 5;
        valPos[r3][3] = 15;
        valPos[r3][4] = 15;
        valPos[r3][5] = 5;
        valPos[r3][6] = 11;
        valPos[r3][7] = 8;

        valPos[r4][0] = 10;
        valPos[r4][1] = 11;
        valPos[r4][2] = 15;
        valPos[r4][3] = 20;
        valPos[r4][4] = 20;
        valPos[r4][5] = 15;
        valPos[r4][6] = 11;
        valPos[r4][7] = 10;

        valPos[r5][0] = 10;
        valPos[r5][1] = 15;
        valPos[r5][2] = 20;
        valPos[r5][3] = 20;
        valPos[r5][4] = 20;
        valPos[r5][5] = 20;
        valPos[r5][6] = 15;
        valPos[r5][7] = 13;

        valPos[r6][0] = 8;
        valPos[r6][1] = 13;
        valPos[r6][2] = 13;
        valPos[r6][3] = 12;
        valPos[r6][4] = 12;
        valPos[r6][5] = 13;
        valPos[r6][6] = 13;
        valPos[r6][7] = 18;

        for (int j=0; j<8; j++)
        	valPos[r7][j] = 0;
    }
    */
}