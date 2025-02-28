package game

fun main() {
    println("Welcome to Tic Tac Toe!")

    // Get player names
    print("\nEnter name for Player X: ")
    val playerXName = readln().trim().replace(Regex("[^a-zA-Z0-9\\s]"), "").ifEmpty { "Player X" }
    print("\nEnter name for Player O: ")
    val playerOName = readln().trim().replace(Regex("[^a-zA-Z0-9\\s]"), "").ifEmpty { "Player O" }

    // Create players and game
    val playerX = Player.createX(playerXName)
    val playerO = Player.createO(playerOName)
    val game = Game(playerX, playerO)

    // Game loop
    while (true) {
        // Display current state
        println("\n${game}\n")
        val player = game.currentPlayer
        println("${player.name}'s turn (${player.symbol})")

        // Get player move
        val (row, col) = getPlayerMove()

        // Make move and handle result
        when (game.makeMove(row, col)) {
            MoveResult.VALID_MOVE -> continue
            MoveResult.INVALID_MOVE -> {
                println("Invalid move! Try again.")
                continue
            }
            MoveResult.WIN -> {
                println("\n${game}\n")
                println("${player.name} wins!")
                if (!playAgain()) break
                game.reset()
            }
            MoveResult.DRAW -> {
                println("\n${game}\n")
                println("It's a draw!")
                if (!playAgain()) break
                game.reset()
            }
            MoveResult.GAME_OVER -> {
                println("Game is already over!")
                if (!playAgain()) break
                game.reset()
            }
        }
    }

    println("Thanks for playing!")
}

/**
 * Gets the player's move from console input.
 * @return Pair of row and column indices
 */
private fun getPlayerMove(): Pair<Int, Int> {
    while (true) {
        print("\nEnter row (1-3) and column (1-3) separated by space: ")
        val input = readln().trim()

        try {
            val (rowStr, colStr) = input.split(Regex("\\s+"))
            val row = rowStr.toIntOrNull()?.minus(1)
            val col = colStr.toIntOrNull()?.minus(1)

            if (row != null && col != null && row in 0..2 && col in 0..2) {
                return row to col
            }
        } catch (e: Exception) {
            // Handle any parsing errors
        }

        println("Invalid input! Please enter two numbers between 1 and 3.")
    }
}

/**
 * Asks if players want to play again.
 * @return true if players want to play again, false otherwise
 */
private fun playAgain(): Boolean {
    while (true) {
        print("\nPlay again? (y/n): ")
        when (readln().trim().lowercase()) {
            "y", "yes" -> return true
            "n", "no" -> return false
            else -> println("Please enter 'y' or 'n'")
        }
    }
}
