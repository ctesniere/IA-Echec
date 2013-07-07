package fr.esgi.ia;
import java.util.ArrayList;

/**
 * This class represent the Knight piece.
 * 
 * @author CŽdric TESNIERE
 */
public class Chevalier extends Piece {
    
    /** Creates a new instance of Knight from it's ID*/
    public Chevalier(int _id) {
    	
    	super();
    	
        setId(_id);
        setColour(Helper.IsEven(_id));
        
        if (isColour() == false)
        	setValue(-300);
        else
        	setValue(300);
        
        //Starting position
        String square = getStartingPosition(_id);
        setX(Helper.getXfromString(square));
        setY(Helper.getYfromString(square));
    }
    
    /**
     * Used when a chessboard must be cloned.
     */
    public Object clone() {
    	
        Chevalier myClone= new Chevalier(this.getId());
        myClone.setValPos(this.getValPos());
        myClone.setInDanger(this.isInDanger());
        myClone.setEnemy(this.getEnemy());
        myClone.setMoved(this.isMoved());
        myClone.setPosition(getX(),getY());
        return myClone;
    }
    
    /**
     * We give all possible moves, not the good ones.
     * 
     * @param _chessboard The actual chessboard
     * @return An array of all possible moves (not the good ones!)
     */
    @Override
    public ArrayList<Move> generateMovesForThisPiece(Chessboard _chessboard) {
    	
        int toX = 0, toY = 0;
        ArrayList<Move> moves = new ArrayList <Move>();
        
        for (int i=0; i<8; i++) { //8 possible moves
            
        	if (i == 0) {
                toX=getX()+2;
                toY=getY()+1;
            }
            if (i==1) {
                toX=getX()+2;
                toY=getY()-1;
            }
            if (i==2) {
                toX=getX()-2;
                toY=getY()+1;
            }
            if (i==3) {
                toX=getX()-2;
                toY=getY()-1;
            }
            if (i==4) {
                toX=getX()+1;
                toY=getY()+2;
            }
            if (i==5) {
                toX=getX()-1;
                toY=getY()+2;
            }
            if (i==6) {
                toX=getX()-1;
                toY=getY()-2;
            }
            if (i==7) {
                toX=getX()+1;
                toY=getY()-2;
            }
            
            Move move = checkThis(toX, toY, _chessboard);
            if (move != null)
            	moves.add(move);
            
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
    private Move checkThis(int _toX, int _toY, Chessboard _chessboard){
    	
    	Piece destination = _chessboard.getPieceMuov(_toX, _toY);
    	Move move;
    	
        if (destination != null) {
           if (destination.isColour() != this.isColour()) {
               move = new Move(getX(), getY(), _toX, _toY, this.isColour());
               if (move.isValid())
            	   return move;
           }
        }
        else {
           move = new Move(getX(),getY(), _toX, _toY, this.isColour());
           if (move.isValid())
               return move;
        }
        
        return null;
    }
    
    /*
    // Costruttore Tabella Posizione Cavallo Bianco
    private void ini_val_pos() {
    	
        int r0=7;
        int r1=6;
        int r2=5;
        int r3=4;
        int r4=3;
        int r5=2;
        int r6=1;
        int r7=0;
        if (setColour(true)) {
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
            for (int j=0; j<8; j++)
            valPos[r0][j] = 1;

                    // Riga 1
            valPos[r1][0] = 2;
            valPos[r1][1] = 3;
            valPos[r1][2] = 6;
            valPos[r1][3] = 4;
            valPos[r1][4] = 4;
            valPos[r1][5] = 6;
            valPos[r1][6] = 3;
            valPos[r1][7] = 2;

                    // Riga 2
            valPos[r2][0] = 2;
            valPos[r2][1] = 6;
            valPos[r2][2] = 15;
            valPos[r2][3] = 8;
            valPos[r2][4] = 8;
            valPos[r2][5] = 15;
            valPos[r2][6] = 6;
            valPos[r2][7] = 2;

                    // Riga 3
            valPos[r3][0] = 5;
            valPos[r3][1] = 10;
            valPos[r3][2] = 10;
            valPos[r3][3] = 14;
            valPos[r3][4] = 14;
            valPos[r3][5] = 10;
            valPos[r3][6] = 10;
            valPos[r3][7] = 5;

                    // Riga 4
            valPos[r4][0] = 5;
            valPos[r4][1] = 14;
            valPos[r4][2] = 16;
            valPos[r4][3] = 16;
            valPos[r4][4] = 16;
            valPos[r4][5] = 16;
            valPos[r4][6] = 14;
            valPos[r4][7] = 5;

                    // Riga 5
            valPos[r5][0] = 5;
            valPos[r5][1] = 20;
            valPos[r5][2] = 20;
            valPos[r5][3] = 18;
            valPos[r5][4] = 18;
            valPos[r5][5] = 20;
            valPos[r5][6] = 20;
            valPos[r5][7] = 5;

                    // Riga 6
            valPos[r6][0] = 5;
            valPos[r6][1] = 12;
            valPos[r6][2] = 14;
            valPos[r6][3] = 16;
            valPos[r6][4] = 16;
            valPos[r6][5] = 14;
            valPos[r6][6] = 12;
            valPos[r6][7] = 5;

                    // Riga 7
            valPos[r7][0] = 5;
            valPos[r7][1] = 5;
            valPos[r7][2] = 5;
            valPos[r7][3] = 10;
            valPos[r7][4] = 10;
            valPos[r7][5] = 5;
            valPos[r7][6] = 5;
            valPos[r7][7] = 5;
    }
    */
    
}