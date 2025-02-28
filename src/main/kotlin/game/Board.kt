package game

/**
 * Represents a Tic Tac Toe board.
 * The board is represented as a 3x3 grid where:
 * - null represents an empty cell
 * - 'X' represents player X's move
 * - 'O' represents player O's move
 */
class Board {
    companion object {
        const val GRID_SIZE = 3
        private val VALID_RANGE = 0 until GRID_SIZE
    }

    private val grid = Array(GRID_SIZE) { Array<Char?>(GRID_SIZE) { null } }

    /**
     * Makes a move on the board.
     * @param row The row index (0-2)
     * @param col The column index (0-2)
     * @param symbol The player's symbol ('X' or 'O')
     * @return true if the move was successful, false if the cell was already occupied
     */
    fun makeMove(
        row: Int,
        col: Int,
        symbol: Char,
    ): Boolean =
        when {
            row !in VALID_RANGE || col !in VALID_RANGE || symbol !in setOf(Player.SYMBOL_X, Player.SYMBOL_O) -> false
            grid[row][col] != null -> false
            else -> {
                grid[row][col] = symbol
                true
            }
        }

    /**
     * Checks if the game has been won.
     * @return the winning symbol ('X' or 'O') or null if no winner
     */
    fun checkWinner(): Char? {
        val rows = VALID_RANGE.asSequence().map { row -> grid[row].toList() }
        val cols = VALID_RANGE.asSequence().map { col -> VALID_RANGE.map { row -> grid[row][col] } }
        val diagonals =
            sequenceOf(
                VALID_RANGE.map { i -> grid[i][i] },
                VALID_RANGE.map { i -> grid[i][GRID_SIZE - 1 - i] },
            )

        return (rows + cols + diagonals)
            .firstOrNull { line -> line.first()?.let { symbol -> line.all { it == symbol } } ?: false }
            ?.first()
    }

    /**
     * Checks if the board is full (draw condition).
     * @return true if the board is full, false otherwise
     */
    fun isFull(): Boolean = grid.all { row -> row.all { it != null } }

    /**
     * Gets the current state of the board.
     * @return 2D array representing the current board state
     */
    fun getBoard(): Array<Array<Char?>> = grid.map { it.clone() }.toTypedArray()

    /**
     * Clears the board for a new game.
     */
    fun clear() = grid.forEach { it.fill(null) }

    /**
     * Returns a string representation of the board.
     */
    override fun toString(): String =
        grid.joinToString("\n") { row ->
            row.joinToString(" | ") { it?.toString() ?: " " }
        }
}
