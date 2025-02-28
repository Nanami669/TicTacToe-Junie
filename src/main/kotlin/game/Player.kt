package game

/**
 * Represents a player in the Tic Tac Toe game.
 * @property symbol The player's symbol ('X' or 'O')
 * @property name The player's name
 */
data class Player(
    val symbol: Char,
    val name: String,
) {
    init {
        require(symbol in VALID_SYMBOLS) { "Symbol must be either '$SYMBOL_X' or '$SYMBOL_O'" }
        require(name.isNotBlank()) { "Player name cannot be blank" }
    }

    companion object {
        const val SYMBOL_X = 'X'
        const val SYMBOL_O = 'O'
        private val VALID_SYMBOLS = setOf(SYMBOL_X, SYMBOL_O)

        /**
         * Creates a player X.
         * @param name The player's name
         * @return A new Player instance with symbol 'X'
         */
        fun createX(name: String) = Player(SYMBOL_X, name)

        /**
         * Creates a player O.
         * @param name The player's name
         * @return A new Player instance with symbol 'O'
         */
        fun createO(name: String) = Player(SYMBOL_O, name)
    }
}
