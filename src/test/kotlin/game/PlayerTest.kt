package game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("should create player with valid symbol X") {
        val player = Player('X', "Player 1")
        player.symbol shouldBe 'X'
        player.name shouldBe "Player 1"
    }

    test("should create player with valid symbol O") {
        val player = Player('O', "Player 2")
        player.symbol shouldBe 'O'
        player.name shouldBe "Player 2"
    }

    test("should throw exception for invalid symbol") {
        shouldThrow<IllegalArgumentException> {
            Player('A', "Player 1")
        }.message shouldBe "Symbol must be either 'X' or 'O'"
    }

    test("should throw exception for blank name") {
        shouldThrow<IllegalArgumentException> {
            Player('X', "  ")
        }.message shouldBe "Player name cannot be blank"
    }

    test("factory method createX should create player with X symbol") {
        val player = Player.createX("Player 1")
        player.symbol shouldBe 'X'
        player.name shouldBe "Player 1"
    }

    test("factory method createO should create player with O symbol") {
        val player = Player.createO("Player 2")
        player.symbol shouldBe 'O'
        player.name shouldBe "Player 2"
    }

    test("players with same symbol and name should be equal") {
        val player1 = Player('X', "Player 1")
        val player2 = Player('X', "Player 1")
        player1 shouldBe player2
    }

    test("players with different symbols should not be equal") {
        val player1 = Player('X', "Player 1")
        val player2 = Player('O', "Player 1")
        (player1 == player2) shouldBe false
    }

    test("players with different names should not be equal") {
        val player1 = Player('X', "Player 1")
        val player2 = Player('X', "Player 2")
        (player1 == player2) shouldBe false
    }
})
