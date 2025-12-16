# Krovat-Console-Chess
The third project of team Krovat for OOP class

## (BG)

1. Описание\
Конзолен шах, с опция за запазване на играта, зареждане на предишна игра и връщане един ход назад. По време на играта играч може да преложи равенство или да се предаде.


2. Инструкции за използване


3. Алгоритъм\
State diagram:
![img.png](img.png)\


4. Обяснение на кода


5. Екип. Разпределение на работата
- Иванета - Board instructions, constructors, toString, getKingCoordinates, checkCheck, checkCollision, saveUndoState, undoMove and makeMove methods, Piece, Coordinates, Queen, copy method and copy constructors in all pieces, InvalidMove, README
- Виктор - Knight, King, checkMate, checkStalemate, hasValidMoves, README, presentation
- Самуил - Pawn, promotion, checkEnPassant, enPassant, getPiece, resetAllEnPassantEligibility, checkPin
- Игнат - Rook, Bishop, checkCastle, castle