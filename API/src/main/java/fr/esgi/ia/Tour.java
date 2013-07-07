package fr.esgi.ia;
import java.util.ArrayList;

/**
 * This class represent the Rook piece.
 * 
 * @author CŽdric TESNIERE
 */
public class Tour extends Piece {

    /** Creates a new instance of Rook from it's ID*/
    public Tour(int _id) {
    	
    	super();
        
    	setId(_id);
        setColour(Helper.IsEven(_id));
        
        if (isColour()==false)
        	setValue(-500);
        else
        	setValue(500);
        
        //Starting position
        String square = getStartingPosition(_id);
        setX(Helper.getXfromString(square));
        setY(Helper.getYfromString(square));
    }

    /**
     * Used when a chessboard must be cloned.
     */
    @Override
    public Object clone() {
    	
        Tour myClone= new Tour(this.getId());
        myClone.setInDanger(this.isInDanger());
        myClone.setEnemy(this.getEnemy());
        myClone.setMoved(this.isMoved());
        myClone.setPosition(getX(),getY());
        myClone.setValPos(this.getValPos());
        return myClone;
    }

    /**
     * The Rook can move until it finds an enemy of an ally piece.
     * We give all possible moves, not the good ones.
     * 
     * @param _chessboard The actual chessboard
     * @return An array of all possible moves (not the good ones!)
     */
    @Override
    public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {
    	
        int toX = 0, toY = 0;
        ArrayList<Move> moves = new ArrayList<Move>();
        
        //4 direction
        for (int direction = 0; direction < 4; direction++) {
        	
            //Max 8 moves
            for (int length = 1; length < 9; length++) {
            	
                if (direction==0) {
                    toX=getX()+length;
                    toY=getY();
                }
                if (direction==1) {
                    toX=getX();
                    toY=getY()+length;
                }
                if (direction==2) {
                    toX=getX()-length;
                    toY=getY();
                }
                if (direction==3) {
                    toX=getX();
                    toY=getY()-length;
                }
                
                /*
                 * OLD CODE
                 */
                /*
                Move move = checkMove(toX, toY, chessboard);
                if (move != null) {
                	moves.add(move);
                }
                else {  //If move is null, no more possible moves in this direction
                    break; //Stop going in this direction
                }
                */
                
                //TODO: Change checkThis to return a Boolean
                Move move = checkMove(toX, toY, _chessboard, moves);
                if (move == null) {
                    //If move is null, no more possible moves in this direction
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
     * @param _chessboard actual chessboard
     * @return A Move or NULL.
     */
    private Move checkMove(int _toX, int _toY, Chessboard _chessboard, ArrayList<Move> _moves){
    	
    	Piece destination = _chessboard.getPieceMuov(_toX, _toY);
    	Move move;
        
        if (destination != null) { //If is not empty
           if (destination.isColour() != this.isColour()) { //Another color
               move = new Move(getX(), getY(), _toX, _toY, this.isColour());
               if (move.isValid()){
            	   _moves.add(move); //Add move
               }
           }
           //Mine or not, if there is a piece STOP
           return null; //You must stop
        }
        else {
           move = new Move(getX(),getY(), _toX, _toY, this.isColour());
           if (move.isValid()) {
               _moves.add(move); //Add move
           }
        }
        
        return move;
    }

    /*
    private void ini_val_pos()     
    {
        int r0=7;
        int r1=6;
        int r2=5;
        int r3=4;
        int r4=3;
        int r5=2;
        int r6=1;
        int r7=0;
        if (setColour(true))
        {
            r0=0;
            r1=1;
            r2=2;
            r3=3;
            r4=4;
            r5=5;
            r6=6;
            r7=7;
        }    

                    // Riga 0
            valPos[r0][0] = 10;
            valPos[r0][1] = 0;
            valPos[r0][2] = 0;
            valPos[r0][3] = 2;
            valPos[r0][4] = 0;
            valPos[r0][5] = 2;
            valPos[r0][6] = 0;
            valPos[r0][7] = 10;

                    // Riga 1
            valPos[r1][0] = 5;
            valPos[r1][1] = 5;
            valPos[r1][2] = 4;
            valPos[r1][3] = 4;
            valPos[r1][4] = 4;
            valPos[r1][5] = 4;
            valPos[r1][6] = 5;
            valPos[r1][7] = 5;

                    // Riga 2
            valPos[r2][0] = 10;
            valPos[r2][1] = 10;
            valPos[r2][2] = 8;
            valPos[r2][3] = 8;
            valPos[r2][4] = 8;
            valPos[r2][5] = 8;
            valPos[r2][6] = 10;
            valPos[r2][7] = 10;

                    // Riga 3
            valPos[r3][0] = 12;
            valPos[r3][1] = 12;
            valPos[r3][2] = 11;
            valPos[r3][3] = 11;
            valPos[r3][4] = 11;
            valPos[r3][5] = 11;
            valPos[r3][6] = 12;
            valPos[r3][7] = 12;

                    // Riga 4
            valPos[r4][0] = 14;
            valPos[r4][1] = 14;
            valPos[r4][2] = 13;
            valPos[r4][3] = 13;
            valPos[r4][4] = 13;
            valPos[r4][5] = 13;
            valPos[r4][6] = 14;
            valPos[r4][7] = 14;

                    // Riga 5
            valPos[r5][0] = 14;
            valPos[r5][1] = 14;
            valPos[r5][2] = 13;
            valPos[r5][3] = 13;
            valPos[r5][4] = 13;
            valPos[r5][5] = 13;
            valPos[r5][6] = 14;
            valPos[r5][7] = 14;

                    // Riga 6
            valPos[r6][0] = 15;
            valPos[r6][1] = 20;
            valPos[r6][2] = 20;
            valPos[r6][3] = 15;
            valPos[r6][4] = 15;
            valPos[r6][5] = 20;
            valPos[r6][6] = 20;
            valPos[r6][7] = 15;

                    // Riga 7
            valPos[r7][0] = 15;
            valPos[r7][1] = 20;
            valPos[r7][2] = 20;
            valPos[r7][3] = 15;
            valPos[r7][4] = 15;
            valPos[r7][5] = 20;
            valPos[r7][6] = 20;
            valPos[r7][7] = 15;
    }
    */
}