package chess;

public class Board {
    private Piece[][] squares = new Piece[8][8];

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

    //load constructor - load game
    // This constructor accepts a Piece[8][8] array and COPIES its elements in squares
    // Make sure it is a deep copy and not a shallow one
    // Do not forget input validation

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
}
