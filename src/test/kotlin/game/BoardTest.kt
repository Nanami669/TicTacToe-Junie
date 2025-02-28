package game

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class BoardTest :
    FunSpec({
        lateinit var board: Board

        beforeTest {
            board = Board()
        }

        test("board should be empty when created") {
            board.getBoard().forEach { row ->
                row.forEach { cell ->
                    cell.shouldBeNull()
                }
            }
        }

        test("makeMove should return true for valid empty position") {
            board.makeMove(0, 0, 'X') shouldBe true
        }

        test("makeMove should return false for invalid position") {
            board.makeMove(3, 0, 'X') shouldBe false
            board.makeMove(-1, 0, 'X') shouldBe false
            board.makeMove(0, 3, 'X') shouldBe false
            board.makeMove(0, -1, 'X') shouldBe false
        }

        test("makeMove should return false for occupied position") {
            board.makeMove(0, 0, 'X') shouldBe true
            board.makeMove(0, 0, 'O') shouldBe false
        }

        test("makeMove should return false for invalid symbol") {
            board.makeMove(0, 0, 'A') shouldBe false
        }

        test("checkWinner should detect row win") {
            board.makeMove(0, 0, 'X')
            board.makeMove(0, 1, 'X')
            board.makeMove(0, 2, 'X')
            board.checkWinner() shouldBe 'X'
        }

        test("checkWinner should detect column win") {
            board.makeMove(0, 0, 'O')
            board.makeMove(1, 0, 'O')
            board.makeMove(2, 0, 'O')
            board.checkWinner() shouldBe 'O'
        }

        test("checkWinner should detect diagonal win") {
            board.makeMove(0, 0, 'X')
            board.makeMove(1, 1, 'X')
            board.makeMove(2, 2, 'X')
            board.checkWinner() shouldBe 'X'
        }

        test("checkWinner should detect reverse diagonal win") {
            board.makeMove(0, 2, 'O')
            board.makeMove(1, 1, 'O')
            board.makeMove(2, 0, 'O')
            board.checkWinner() shouldBe 'O'
        }

        test("checkWinner should return null when no winner") {
            board.makeMove(0, 0, 'X')
            board.makeMove(0, 1, 'O')
            board.makeMove(0, 2, 'X')
            board.checkWinner().shouldBeNull()
        }

        test("isFull should return true when board is full") {
            val moves =
                listOf(
                    Triple(0, 0, 'X'),
                    Triple(0, 1, 'O'),
                    Triple(0, 2, 'X'),
                    Triple(1, 0, 'O'),
                    Triple(1, 1, 'X'),
                    Triple(1, 2, 'O'),
                    Triple(2, 0, 'X'),
                    Triple(2, 1, 'O'),
                    Triple(2, 2, 'X'),
                )
            moves.forEach { (row, col, symbol) ->
                board.makeMove(row, col, symbol)
            }
            board.isFull() shouldBe true
        }

        test("isFull should return false when board is not full") {
            board.makeMove(0, 0, 'X')
            board.makeMove(0, 1, 'O')
            board.isFull() shouldBe false
        }

        test("clear should reset the board") {
            board.makeMove(0, 0, 'X')
            board.makeMove(0, 1, 'O')
            board.clear()
            board.getBoard().forEach { row ->
                row.forEach { cell ->
                    cell.shouldBeNull()
                }
            }
        }

        test("toString should properly format the board") {
            board.makeMove(0, 0, 'X')
            board.makeMove(1, 1, 'O')
            val expected = "X |   |  \n  | O |  \n  |   |  "
            board.toString() shouldBe expected
        }
    })
