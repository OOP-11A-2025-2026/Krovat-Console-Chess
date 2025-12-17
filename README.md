# Krovat-Console-Chess
The third project of team Krovat for OOP class

## (BG)

### 1. Описание
Krovat-Console-Chess е конзолен шах, който поддържа:
- Стандартни правила на шаха
- Проверка за шах, шахмат и пат
- Рокада, ан пасан и промоция на пешка
- Запазване и зареждане на игра
- Връщане един ход назад (Undo)
- Предлагане на реми и предаване


### 2. Инструкции за използване - инсталация и стартиране
Изисквания:
- Java 25
- Конзола/терминал или IDE (IntelliJ IDEA, Eclipse и др.)

Стартиране от конзола в директорията, където се намира проекта:
javac *.java
java Main

Стартиране от IDE:
1. Отворете проекта в IDE
2. Намерете класа Main
3. Стартирайте метода main()
4. Избор за зареждане на дъска от PGN нотация или начало на нова
5. Главен цикъл - Избор на ход (Move, Save, Draw, Resign)
6. Move - Задайте валиден ход (SAN / алгебрична нотация)
7. Save - Дъската се запазва в PGN формат
8. Draw - Предлага на опонента Реми
9. Resign - Предавате се, опонентът печели служебно
10. Докато не се стигне до Mate, Stalemate, Draw или Resign - изпълнение на главния цикъл
11. Бели фигури (главни букви) - P (Pawn), R (Rook), N (Knight), B (Bishop), Q (Queen), K (King)
12. Черни фигури (малки букви) - p (Pawn), r (Rook), n (Knight), b (Bishop), q (Queen), k (King)

### 3. Алгоритъм
State diagram:
![img.png](img.png)


### 4. Обяснение на кода

Инициализира се Game, създава се нова дъска (8х8) със стандартните позиции на фигурите (крайните редове са заети от топове, коне, офицери, царица и цар, а тези пред тях - с пешки), дъската има тъмни (*) и светли (.) полета.
Белият и Черният играч участват в безкраен цикъл на игра на шах, докато не стигнат до край (мат, пат, реми или един от двамата да се предаде).
Класът Board държи цялата вътрешна логика на играта, а Game - цялата външна логика (с четене на данни от потребителя и работа с файлове).

### 5. Екип. Разпределение на работата
- Иванета - Board instructions, constructors, toString, getKingCoordinates, checkCheck, checkCollision, saveUndoState, undoMove, makeMove and isLegalMove methods, Piece, Coordinates, Queen, copy method and copy constructors in all pieces, InvalidMove, README, Game interpretMove
- Виктор - Knight, King, Board checkMate, checkStalemate and hasValidMoves, README, presentation
- Самуил - Pawn, Board promotion, checkEnPassant, enPassant, getPiece, resetAllEnPassantEligibility and checkPin, Game interpretMove (first version)
- Игнат - Rook, Bishop, Board checkCastle and castle

## (EN)

### 1. Description
Krovat-Console-Chess is a chess game that runs in the Java console. It supports:
- Standard chess rules
- Checks for check, mate and stalemate
- Castling, En Passant and Pawn Promotion
- Saving and Loading a game
- Taking back a move (Using Undo)
- Offering a Draw and Resigning


### 2. Usage instructions - installation and execution
Requirements:
- Java 25
- Console, Terminal or IDE (IntelliJ IDEA, Eclipse, etc.)

Running via the project directory:
javac *.java
java Main

Running via IDE:
1. Open the project in IDE
2. Find class Main
3. Run method main()
4. Choose whether to start a New Game or Load an existing one from a PGN file (example.pgn)
5. Main loop - Options (Move, Save, Draw, Resign)
6. Move - Enter a valid move (SAN / algebraic notation)
7. Save - The board is saved in a PGN format
8. Draw - Offer the opponent a Draw
9. Resign - Give up the match, the opponent wins automatically
10. Until the game has reached an end (Mate, Stalemate, Draw or Resign) - infinite loop
11. White pieces (capital letters) - P (Pawn), R (Rook), N (Knight), B (Bishop), Q (Queen), K (King)
12. Black pieces (small letters) - p (Pawn), r (Rook), n (Knight), b (Bishop), q (Queen), k (King)

### 3. Algorithm
State diagram:
![img.png](img.png)


### 4. Code Explanation

Game is initialized, a new Board is created (8х8) with the standard piece positions (the edge rows are occupied by rooks, knight, bishops, a queen and a king, while the ones in front of them - with pawns), the board has dark (*) and light (.) squares.
The White and Black player's game of chess takes place in an infinite loop, until an end is declared (mate, stalemate, draw or resign).
The Board class holds the whole game logic, while Game - the whole outer logic (user input and file integration).

### 5. Our team. Who worked on what
- Ivaneta - Board instructions, constructors, toString, getKingCoordinates, checkCheck, checkCollision, saveUndoState, undoMove, makeMove and isLegalMove methods, Piece, Coordinates, Queen, copy method and copy constructors in all pieces, InvalidMove, README, Game interpretMove
- Viktor - Knight, King, Board checkMate, checkStalemate and hasValidMoves, README, presentation
- Samuil - Pawn, Board promotion, checkEnPassant, enPassant, getPiece, resetAllEnPassantEligibility and checkPin, Game interpretMove (first version)
- Ignat - Rook, Bishop, Board checkCastle and castle