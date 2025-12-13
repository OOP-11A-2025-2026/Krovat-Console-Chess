package chess;

public class Board {
    private Piece[][] squares;

    // INSTRUCTIONS - each block of comments is a different function
    // There are explanations to what the function should do
    // The suggestions are to guide you, they don't have to be strictly followed
    // You can change things the function name or algorithm if you think of a better one etc.
    // The parameters are not written, you choose them
    // Make sure your function works for both black and white
    // Write your code below the block of comments for the function
    // There are probably things I missed or got wrong so proceed with caution
    // Do your best and good luck! - Iveto :)

    //default constructor - new game
    // The board has to be set up with the beginning positions of the pieces:
    // Rooks in the corners, Knights next to the rooks etc.
    // Above is black in lowercase letters, below is white in uppercase letters
    public Board() {
        squares = new Piece[8][8];

        for (int j = 0; j < 8; j++) {
            squares[1][j] = new Pawn(false);
            squares[6][j] = new Pawn(true);
        }

        squares[0][0] = new Rook(false);
        squares[0][7] = new Rook(false);
        squares[7][0] = new Rook(true);
        squares[7][7] = new Rook(true);

        squares[0][1] = new Knight(false );
        squares[0][6] = new Knight(false);
        squares[7][1] = new Knight(true);
        squares[7][6] = new Knight(true);

        squares[0][2] = new Bishop(false);
        squares[0][5] = new Bishop(false);
        squares[7][2] = new Bishop(true);
        squares[7][5] = new Bishop(true);

        squares[0][3] = new Queen(false);
        squares[7][3] = new Queen(true);

        squares[0][4] = new King(false);
        squares[7][4] = new King(true);
    }

    //load constructor - load game
    // This constructor accepts a Piece[8][8] array and COPIES its elements in squares
    // Make sure it is a deep copy and not a shallow one
    // Do not forget input validation
    public Board(Piece[][] squares) {
        this.squares = new Piece[8][8];

        if(squares.length != 8) throw new IllegalArgumentException("Invalid array length");
        if(squares[0].length != 8) throw new IllegalArgumentException("Invalid array length");
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                this.squares[i][j] = squares[i][j].copy(); // Had to make a copy method to ensure deep copy
            }
        }
    }

    //has undoMove, can't stack undo's
    // Reverse the last played move
    // For example if I am black and play a move that is accepted and the board is updated
    // I can reverse this move or my opponent can play
    // I can't EVER reverse this move if my opponent plays

    // checkCheck()
    // Checks if the king is in check and returns true if it is, else - false

    // checkCastle()
    // Checks if it is a king move
    // Checks if the king is moving 2 squares to the left or right
    // ---------------- If any check after this line fails than throw an InvalidMove error
    // Checks that there are no pieces between the king and rook
    // Careful! The king can castle both right and left side and there are slight differences
    // Checks that the king and rook have not been moved before
    // Checks if the king is in check using checkCheck() function
    // Checks that the king isn't going through squares that are attacked by enemy pieces
    // Checks that the king doesn't end up on a square attacked by enemy piece
    // If all checks pass return true. If one of the checks above the line fails return false

    // Castle()
    // Moves the king and rook to the right places
    // Careful! The king can castle both right and left side and there are slight differences
    // call only AFTER checkCastle()

    // checkEnPassant()
    // Checks that it is a pawn move
    // Checks that the pawn (pawn1) is next to another pawn (pawn2)
    // Checks that pawn1 is trying to go to the square below pawn2
    // ---------------- If any check after this line fails than throw an InvalidMove error
    // Checks that LAST move pawn2 moved 2 squares up
    // If all checks pass return true. If one of the checks above the line fails return false

    // EnPassant()
    // Moves the pawn and removes the captured enemy pawn
    // call only AFTER checkEnPassant()

    // checkPin()
    // Checks if after a piece moves if the king will be in check
    // Should probably not actually change the board
    // You can use checkCheck() to make it easier maybe?
    // If after the move the king will be in check throw InvalidMove

    // promotion()
    // Checks if it is a pawn move
    // Checks if the pawn reached the end of the board
    // Gets input form somewhere that tells it what to promote the pawn to
    // Replaces the pawn with the new piece

    // checkMate()
    // Checks if the king is checkmated
    // You can use checkCheck() and/or checkStalemate() to make it easier maybe?
    // Returns true if he is, else false

    // checkStalemate()
    // Checks if the king is stalemated
    // Returns true if he is, else false

    // checkCollision()
    // This function checks if there are any pieces between 2 sets of coordinates

    //gets info from game for the next move - what piece and where is it moving
    //every odd turn is White, every even turn is Black
    // This function handles move validation and updating the board:
    // calls checkCastle() and depending on the result calls Castle()
    // If Castle() is called the function should end in some way
    // There is no reason to continue checking if the move was made
    // calls checkEnPassant() -> EnPassant() (same as checkCastle() and Castle())
    // If EnPassant() is called the function should end in some way
    // calls regular movement - this should be a method every piece has, because every piece moves differently
    // It checks if the piece is moving how it should be and throws an exception if needed:
    // Rooks move up and down, Kings move only one square in all directions and etc.
    // calls checkCollision() only if the piece is not a knight
    // calls checkPin()
// checkCheck(during the move) - might not actually be needed, checkPin() might cover what we needed to call it for
    // calls promotion()
    // calls checkMate(after the move) and maybe returns a winner or something like that
    // calls checkStalemate(after the move) and maybe returns a draw or something like that

    // toString method
    // Returns a string that is going to get printed on the console
    // Uppercase letters are for white
    // Lowercase letters are for black
    // Letters: N (Knight), Q, R, P, B, K (King)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  a b c d e f g h"); // file labels

        for (int i = 0; i < 8; i++) {
            sb.append(8 - i).append(" "); // rank labels

            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j];
                if (piece == null) {
                    sb.append(". ");
                }
                else {
                    sb.append(piece.getSymbol()).append(" ");
                }
            }

            sb.append("\n");
        }
        
        return sb.toString();
    }

    public boolean checkCheck() {
        //checks for, well, checks ig
        return false;
    }

    public boolean hasValidMoves() {
        //used for checks for mate & stalemate (and checks, I suppose)
        //if()
        return false;
    }

    public boolean checkMate() {
        //if no valid moves AND in check
        //if(checkCheck() && !hasValidMoves()) return true;
        return false;
    }

    public boolean checkStalemate() {
        //if no valid moves BUT no check
        //if(!checkCheck() && !hasValidMoves()) return true;
        return false;
    }

    /// PESHKA 2 SPECIAL MOVES
    // Helper method to retrieve a piece using the Coordinates object
    public Piece getPiece(Coordinates coords) {
        if (coords == null) return null;
        return squares[coords.getFirst()][coords.getSecond()];
    }

    // Helper method to reset the enPassantEligible flag for all pawns za one-turn En Passant rule.
    private void resetAllEnPassantEligibility() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = squares[i][j];
                if (piece instanceof Pawn) {
                    ((Pawn) piece).setEnPassantEligible(false);
                }
            }
        }
    }

    public boolean checkEnPassant(Coordinates from, Coordinates to) {
        Piece movingPiece = getPiece(from);

        if (!(movingPiece instanceof Pawn)) {
            return false;
        }
        Pawn pawn1 = (Pawn) movingPiece;

        // Check 1 & 2: Geometry and empty target square
        if (!pawn1.regularMovement(from, to) || getPiece(to) != null) {
            return false;
        }

        try {
            // Find thepawn2 that must be captured
            int targetPawnR = from.getFirst();
            int targetPawnC = to.getSecond();
            Coordinates targetPawnCoords = new Coordinates(targetPawnR, targetPawnC);

            Piece capturedPiece = getPiece(targetPawnCoords);
            if (!(capturedPiece instanceof Pawn)) {
                return false;
            }
            Pawn pawn2 = (Pawn) capturedPiece;

            // Peshkata e chujda
            if (pawn2.isWhite() == pawn1.isWhite()) {
                return false;
            }
            // Pawn - ?moved two squares last turn)
            if (!pawn2.isEnPassantEligible()) {
                return false;
            }

        } catch (ClassCastException | IllegalArgumentException e) {
            return false;
        }

        return true; // En Passant - validen
    }

    public void enPassant(Coordinates from, Coordinates to) {
        Pawn pawn1 = (Pawn) getPiece(from);

        //Remove the captured pawn (on row 'from', column 'to')
        squares[from.getFirst()][to.getSecond()] = null;

        //Move pawn1
        squares[to.getFirst()][to.getSecond()] = pawn1;
        squares[from.getFirst()][from.getSecond()] = null;
        pawn1.setHasMoved(true);

        resetAllEnPassantEligibility();
    }

    public void promotion(Coordinates coords, char promotionChoice) {
        Piece piece = getPiece(coords);

        if (!(piece instanceof Pawn)) {
            return;
        }
        Pawn pawn = (Pawn) piece;

        // Dali peshkata e stignala kraq
        if ((pawn.isWhite() && coords.getFirst() != 0) ||
            (!pawn.isWhite() && coords.getFirst() != 7)) {
            return;
        }

        Piece newPiece = null;
        boolean isWhite = pawn.isWhite();

        // Smeni peshkata
        switch (Character.toUpperCase(promotionChoice)) {
            case 'Q': newPiece = new Queen(isWhite); break;
            case 'R': newPiece = new Rook(isWhite); break;
            case 'B': newPiece = new Bishop(isWhite); break;
            case 'N': newPiece = new Knight(isWhite); break;
            default:
                newPiece = new Queen(isWhite);
                break;
        }

        squares[coords.getFirst()][coords.getSecond()] = newPiece;
    }

    /// PESHKA 2 SPECIAL MOVES END
    
    
}