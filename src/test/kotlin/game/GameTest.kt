package game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class GameTest :
    FunSpec({
        lateinit var game: Game
        lateinit var playerX: Player
        lateinit var playerO: Player

        beforeTest {
            playerX = Player.createX("Player 1")
            playerO = Player.createO("Player 2")
            game = Game(playerX, playerO)
        }

        test("should initialize game with correct players") {
            game.currentPlayer shouldBe playerX
            game.gameState shouldBe GameState.IN_PROGRESS
        }

        test("should throw exception when initializing with wrong player symbols") {
            shouldThrow<IllegalArgumentException> {
                Game(Player('O', "Wrong X"), playerO)
            }.message shouldBe "First player must use 'X' symbol"

            shouldThrow<IllegalArgumentException> {
                Game(playerX, Player('X', "Wrong O"))
            }.message shouldBe "Second player must use 'O' symbol"
        }

        test("should make valid move and switch players") {
            game.makeMove(0, 0) shouldBe MoveResult.VALID_MOVE
            game.currentPlayer shouldBe playerO
            game.boardState[0][0] shouldBe 'X'
        }

        test("should reject invalid move") {
            game.makeMove(0, 0) shouldBe MoveResult.VALID_MOVE
            game.makeMove(0, 0) shouldBe MoveResult.INVALID_MOVE
            game.makeMove(3, 0) shouldBe MoveResult.INVALID_MOVE
            game.makeMove(-1, 0) shouldBe MoveResult.INVALID_MOVE
        }

        test("should detect win condition - row") {
            game.makeMove(0, 0) shouldBe MoveResult.VALID_MOVE // X
            game.makeMove(1, 0) shouldBe MoveResult.VALID_MOVE // O
            game.makeMove(0, 1) shouldBe MoveResult.VALID_MOVE // X
            game.makeMove(1, 1) shouldBe MoveResult.VALID_MOVE // O
            game.makeMove(0, 2) shouldBe MoveResult.WIN // X wins
            game.gameState shouldBe GameState.WIN
        }

        test("should detect win condition - column") {
            game.makeMove(0, 0) shouldBe MoveResult.VALID_MOVE // X
            game.makeMove(0, 1) shouldBe MoveResult.VALID_MOVE // O
            game.makeMove(1, 0) shouldBe MoveResult.VALID_MOVE // X
            game.makeMove(1, 1) shouldBe MoveResult.VALID_MOVE // O
            game.makeMove(2, 0) shouldBe MoveResult.WIN // X wins
            game.gameState shouldBe GameState.WIN
        }

        test("should detect win condition - diagonal") {
            game.makeMove(0, 0) shouldBe MoveResult.VALID_MOVE // X
            game.makeMove(0, 1) shouldBe MoveResult.VALID_MOVE // O
            game.makeMove(1, 1) shouldBe MoveResult.VALID_MOVE // X
            game.makeMove(0, 2) shouldBe MoveResult.VALID_MOVE // O
            game.makeMove(2, 2) shouldBe MoveResult.WIN // X wins
            game.gameState shouldBe GameState.WIN
        }

        test("should detect draw condition") {
            // Fill board without winning
            val moves =
                listOf(
                    Pair(0, 0),
                    Pair(0, 1),
                    Pair(0, 2),
                    Pair(1, 1),
                    Pair(1, 0),
                    Pair(1, 2),
                    Pair(2, 1),
                    Pair(2, 0),
                    Pair(2, 2),
                )
            moves.take(8).forEach { (row, col) ->
                game.makeMove(row, col) shouldBe MoveResult.VALID_MOVE
            }
            game.makeMove(moves.last().first, moves.last().second) shouldBe MoveResult.DRAW
            game.gameState shouldBe GameState.DRAW
        }

        test("should not allow moves after game is over") {
            // Create winning condition
            game.makeMove(0, 0) shouldBe MoveResult.VALID_MOVE
            game.makeMove(1, 0) shouldBe MoveResult.VALID_MOVE
            game.makeMove(0, 1) shouldBe MoveResult.VALID_MOVE
            game.makeMove(1, 1) shouldBe MoveResult.VALID_MOVE
            game.makeMove(0, 2) shouldBe MoveResult.WIN

            // Try to make move after game is over
            game.makeMove(2, 2) shouldBe MoveResult.GAME_OVER
        }

        test("should reset game state") {
            // Make some moves
            game.makeMove(0, 0)
            game.makeMove(1, 1)

            // Reset game
            game.reset()

            // Verify reset state
            game.currentPlayer shouldBe playerX
            game.gameState shouldBe GameState.IN_PROGRESS
            game.boardState.forEach { row ->
                row.forEach { cell ->
                    cell.shouldBeNull()
                }
            }
        }
    })
