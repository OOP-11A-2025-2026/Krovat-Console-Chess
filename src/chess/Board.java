package chess;

public class Board {
    private Piece[][] squares = new Piece[8][8];

    //default constructor - new game
    //load constructor - load game

    //has undoMove, can't stack undo's
    //gets info from game for the next move
    //every odd turn is White, every even turn is Black
    //checkCastle(), checkEnPassant()
    //regular movement, checkPin(), checkCheck(during the move)
    //promotion
    //checkMate(after the move), checkStalemate(after the move)
    //move validation - can the piece move like that? ; is the piece pinned? ; is the king in check? ; can the piece even go there (is there something in the way) ; will the movement stop the check?
    //move(piece, square)
}
