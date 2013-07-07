package fr.esgi.ia;
import java.util.ArrayList;

/**
 * This class represent the Queen piece.
 * 
 * @author Cédric TESNIERE
 */
public class Dame extends Piece {
  
    /**
     * Création d'une nouvelle instance d'une Dame avec un id
     */
    public Dame(int _id) {
    	
    	super();
    	
        setId(_id);
        setColour(Helper.IsEven(_id));
        
        if (isColour()==false)
        	setValue(-900);
        else
        	setValue(900);
        
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
    	
        Dame myClone= new Dame(this.getId());
        myClone.setInDanger(this.isInDanger());
        myClone.setEnemy(this.getEnemy());
        myClone.setMoved(this.isMoved());
        myClone.setPosition(getX(),getY());
        myClone.setValPos(this.getValPos());
        return myClone;
    }
    
    /**
     * The Queen can move until it finds an enemy of an ally piece.
     * We give all possible moves, not the good ones.
     * 
     * @param chessboard The actual chessboard
     * @return An array of all possible moves (not the good ones!)
     */
    @Override
    public ArrayList<Move> generateMovesForThisPiece(Chessboard chessboard) {
    	
        int toX = 0, toY = 0;
        ArrayList<Move> moves = new ArrayList<Move>();
        
        //8 direction
        for (int direction=0; direction<8; direction++) {
        	
            //Max 8 moves
            for (int length=1; length<9; length++) {
            	
                if (direction==0)
                {
                    toX=getX()+length;
                    toY=getY()+length;
                }
                if (direction==1)
                {
                    toX=getX()-length;
                    toY=getY()+length;
                }
                if (direction==2)
                {
                    toX=getX()-length;
                    toY=getY()-length;
                }
                if (direction==3)
                {
                    toX=getX()+length;
                    toY=getY()-length;
                }
                if (direction==4)
                {
                    toX=getX()+length;
                    toY=getY();
                }
                if (direction==5)
                {
                    toX=getX();
                    toY=getY()+length;
                }
                if (direction==6)
                {
                    toX=getX()-length;
                    toY=getY();
                }
                if (direction==7)
                {
                    toX=getX();
                    toY=getY()-length;
                }

                //TODO: Change checkThis to return a Boolean
                Move mossa = checkThis(toX, toY, chessboard, moves);
                if (mossa == null){
                    break;
                }
            }
        }

        return moves;
    }    
    
    /**
     * Test the possible move.
     * 
     * @param toX x destination
     * @param toY y destination
     * @param chessboard actual chessboard
     * @return A Move or NULL.
     */
    private Move checkThis(int toX, int toY, Chessboard chessboard, ArrayList<Move> moves){
    	
    	Piece destination = chessboard.getPieceMuov(toX, toY);
    	Move move;
    	
        if (destination != null) {
           if (destination.isColour() != this.isColour()) {
               move = new Move(getX(), getY(), toX, toY, this.isColour());
               if (move.isValid()) {
            	   moves.add(move);
               }
           }
           return null;
        }
        else {
           move = new Move(getX(),getY(), toX, toY, this.isColour());
           if (move.isValid()) {
               moves.add(move);
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
            for (int j=0; j<8; j++)
            valPos[r0][j] = 0;

                    // Riga 1
            for (int j=0; j<8; j++)
            valPos[r1][j] = 0;

                    // Riga 2
            for (int j=0; j<8; j++)
            valPos[r2][j] = 0;

                    // Riga 3
            for (int j=0; j<8; j++)
            valPos[r3][j] = 0;

                    // Riga 4
            for (int j=0; j<8; j++)
            valPos[r4][j] = 0;

                    // Riga 5
            for (int j=0; j<8; j++)
            valPos[r5][j] = 0;

                    // Riga 6
            for (int j=0; j<8; j++)
            valPos[r6][j] = 0;

                    // Riga 7
            for (int j=0; j<8; j++)
            valPos[r7][j] = 0;
    }
    */
    
}
