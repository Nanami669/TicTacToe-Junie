package game

/**
 * Represents a Tic Tac Toe game.
 * Manages the game state and coordinates between players and the board.
 *
 * @property playerX The player using 'X' symbol
 * @property playerO The player using 'O' symbol
 */
class Game(
    private val playerX: Player,
    private val playerO: Player,
) {
    init {
        require(playerX.symbol == 'X') { "First player must use 'X' symbol" }
        require(playerO.symbol == 'O') { "Second player must use 'O' symbol" }
    }

    private val board = Board()
    private var _currentPlayer: Player = playerX
    val currentPlayer: Player get() = _currentPlayer

    private var state: GameState = GameState.IN_PROGRESS
    val gameState: GameState get() = state
    val boardState: Array<Array<Char?>> get() = board.getBoard()

    /**
     * Makes a move at the specified position for the current player.
     *
     * @param row The row index (0-2)
     * @param col The column index (0-2)
     * @return Result of the move
     */
    fun makeMove(
        row: Int,
        col: Int,
    ): MoveResult =
        when {
            state != GameState.IN_PROGRESS -> MoveResult.GAME_OVER
            !board.makeMove(row, col, currentPlayer.symbol) -> MoveResult.INVALID_MOVE
            board.checkWinner() != null -> {
                state = GameState.WIN
                MoveResult.WIN
            }
            board.isFull() -> {
                state = GameState.DRAW
                MoveResult.DRAW
            }
            else -> {
                _currentPlayer = if (currentPlayer == playerX) playerO else playerX
                MoveResult.VALID_MOVE
            }
        }

    /** Resets the game to its initial state. */
    fun reset() =
        run {
            board.clear()
            _currentPlayer = playerX
            state = GameState.IN_PROGRESS
        }

    override fun toString() = board.toString()
}

/**
 * Represents the possible states of the game.
 */
enum class GameState {
    IN_PROGRESS,
    WIN,
    DRAW,
}

/**
 * Represents the possible results of a move.
 */
enum class MoveResult {
    VALID_MOVE,
    INVALID_MOVE,
    WIN,
    DRAW,
    GAME_OVER,
}
