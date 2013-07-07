package fr.esgi.ia;
import java.util.ArrayList;

/**
 * This class represent the King piece.
 * 
 * @author Cédric TESNIERE
 */
public class Roi extends Piece {

    /**
     * Crée une nouvelle instance du roi 
     */
    public Roi(int _id) {
    	
        setId(_id);        
        setColour(Helper.IsEven(_id));

        if (isColour()==false) {
            setValue(-200);
        } else {
            setValue(200);
        }
        
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
    	
        Roi myClone= new Roi(this.getId());
        myClone.setInDanger(this.isInDanger());
        myClone.setEnemy(this.getEnemy());
        myClone.setMoved(this.isMoved());
        myClone.setPosition(getX(),getY());
        myClone.setValPos(this.getValPos());
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
        ArrayList<Move> moves = new ArrayList<Move>();
        
        for (int i2=0;i2<10;i2++) {
        	
            if (i2==0)
            {
                toX=getX()+1;
                toY=getY()+1;
            }
            if (i2==1)
            {
                toX=getX()-1;
                toY=getY()+1;
            }
            if (i2==2)
            {
                toX=getX()-1;
                toY=getY()-1;
            }
            if (i2==3)
            {
                toX=getX()+1;
                toY=getY()-1;
            }
            if (i2==4)
            {
                toX=getX()+1;
                toY=getY();
            }
            if (i2==5)
            {
                toX=getX();
                toY=getY()+1;
            }
            if (i2==6)
            {
                toX=getX()-1;
                toY=getY();
            }
            if (i2==7)
            {
                toX=getX();
                toY=getY()-1;
             }
            
            Move move = checkThis(toX, toY, _chessboard);
            if (move != null) {
                moves.add(move);
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
    private Move checkThis(int toX, int toY, Chessboard chessboard){
    	
    	Piece destination = chessboard.getPieceMuov(toX, toY);
    	Move move;
    	
        if (destination != null) {
           if (destination.isColour() != this.isColour()) {
               move = new Move(getX(), getY(), toX, toY, this.isColour());
               if (move.isValid()) {
                   return move;
               }
           }
        }
        else {
           move = new Move(getX(),getY(), toX, toY, this.isColour());
           if (move.isValid()) {
                return move;
            }
        }
        
        return null;
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
            valPos[r0][0] = 2;
            valPos[r0][1] = 2;
            valPos[r0][2] = 4;
            valPos[r0][3] = 2;
            valPos[r0][4] = 8;
            valPos[r0][5] = 2;
            valPos[r0][6] = 4;
            valPos[r0][7] = 2;

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
            valPos[r4][j] = -5;

                    // Riga 5
            for (int j=0; j<8; j++)
            valPos[r5][j] = -10;

                    // Riga 6
            for (int j=0; j<8; j++)
            valPos[r6][j] = -15;

                    // Riga 7
            for (int j=0; j<8; j++)
            valPos[r7][j] = -20;
    }
    */
    
}
