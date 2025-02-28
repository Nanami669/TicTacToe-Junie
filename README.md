# Tic Tac Toe

A console-based Tic Tac Toe game implemented in Kotlin. This implementation features a clean, modular design with separate components for the game board, players, and game logic.

## Features

- Interactive console-based gameplay
- Two-player game with customizable player names
- Clear game state visualization
- Input validation and error handling
- Option to play multiple games

## Requirements

- JDK 21 or higher
- Kotlin 2.1.0
- Gradle (wrapper included)

## Installation

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd TicTacToe
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

## How to Run

Run the game using Gradle:
```bash
./gradlew run
```

## How to Play

1. Enter names for Player X and Player O when prompted
2. On your turn, enter row (1-3) and column (1-3) numbers separated by a space
3. The game will display the current board state after each move
4. Win by getting three of your symbols in a row (horizontally, vertically, or diagonally)
5. Choose to play again when the game ends

## Project Structure

```
src/
├── main/kotlin/
│   ├── game/
│   │   ├── Board.kt    # Game board implementation
│   │   ├── Game.kt     # Game logic and state management
│   │   └── Player.kt   # Player representation
│   └── Main.kt         # Main game loop and UI
└── test/kotlin/
    └── game/
        ├── BoardTest.kt
        ├── GameTest.kt
        └── PlayerTest.kt
```

## Running Tests

Execute the test suite:
```bash
./gradlew test
```

## Technologies Used

- Kotlin 2.1.0
- Gradle with Kotlin DSL
- Kotest (Testing Framework)
  - kotest-runner-junit5
  - kotest-assertions-core
  - kotest-property
- ktlint (Code style enforcement)
  - Custom configuration in .editorconfig
  - Allows trailing commas in declarations and call sites
  - Standard Kotlin code style rules

## Development

The project follows Kotlin best practices and conventions:
- Functional programming principles
  - Expression-based code style
  - Sequence operations for efficient processing
  - Immutable state where possible
- Modern Kotlin features
  - Property delegation
  - Smart casts
  - Expression bodies
  - When expressions
- Code Quality
  - Comprehensive test coverage using Kotest
  - Enforced code style with ktlint
  - Early returns and clear flow control
- Clean Architecture
  - Modular design
  - Single responsibility principle
  - Clear separation of concerns
  - Comprehensive documentation
