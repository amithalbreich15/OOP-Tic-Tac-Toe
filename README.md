# OOP-Tic-Tac-Toe
This project is about implementing a customizable Tic-Tac-Toe tournament. The game board can be any size between 2x2 and 9x9, and a player wins by achieving a streak of their mark in a row, column, or diagonal. The game supports both human and automated players, with different AI strategies to ensure competitive gameplay.

# Tic-Tac-Toe Tournament

## Project Description

This project implements a customizable Tic-Tac-Toe Tournament in Java. The tournament involves two players competing in multiple rounds of Tic-Tac-Toe games. The board size and winning streak can be configured, and the tournament supports both human and automated players with varying strategies.

### Game Setup

- **Board Size (n x n)**: The default board size is 4x4.
- **Winning Streak (k)**: The default winning streak length is 3.
- The game ends when a player achieves a winning streak or when the board is full.
- Players can win by forming a horizontal, vertical, or diagonal streak of their mark (X or O).
- The board size (n) and winning streak (k) must satisfy the condition n ≥ k ≥ 2.

### Player Types

1. **Human Player**: The player manually enters their moves via the console.
2. **Automated Players**:
   - **WhateverPlayer**: Makes random moves.
   - **CleverPlayer**: Uses a smarter strategy that wins more than 55% of the games against the WhateverPlayer.
   - **GeniusPlayer**: Uses an even more advanced strategy, winning the majority of games against both CleverPlayer and WhateverPlayer.

### Command-Line Execution

The game is run using the following command:

```shell
java Tournament [round count] [size] [win_streak] [render target: console/none] [first player: human/whatever/clever/genius] [second player: human/whatever/clever/genius]
```

#### Examples:

1. To run a 10,000-round tournament between WhateverPlayer and CleverPlayer on a 4x4 board with a winning streak of 3, without rendering the board:
   ```shell
   java Tournament 10000 4 3 none whatever clever
   ```
2. To play three games between a human player and an automated player with the board displayed:
   ```shell
   java Tournament 3 4 3 console whatever human
   ```

### Error Handling

The program validates input and provides informative error messages for incorrect inputs:

- If the player type or render target is misspelled, the program will display a relevant error message and terminate.
- Example error message for an invalid player type:
  ```shell
  Choose a player, and start again.
  The players: [human, clever, whatever, genius]
  ```
- Example error message for an invalid render target:
  ```shell
  Choose a renderer, and start again.
  Please choose one of the following [console, none]
  ```

### Game Classes and Interfaces

#### Interfaces:

- **Renderer**: Defines the method to render the board.
- **Player**: Defines the method to play a turn.

#### Classes:

1. **Board**: Manages the board state.
   - Default and parameterized constructors.
   - Methods to get board size, place marks, and retrieve marks.
2. **Renderers**:
   - **ConsoleRenderer**: Displays the board on the console (provided).
   - **VoidRenderer**: A no-op renderer that does not display the board.
3. **Players**:
   - **HumanPlayer**: Requests input from the user.
   - **WhateverPlayer**: Makes random moves.
   - **CleverPlayer**: Uses a strategy to win most games against WhateverPlayer.
   - **GeniusPlayer**: Uses a superior strategy to win most games against both WhateverPlayer and CleverPlayer.
4. **Game**: Manages a single game instance.
   - Handles game rules, player turns, and game outcomes.
5. **Tournament**: Manages multiple game rounds and tracks results.
6. **Factories**:
   - **PlayerFactory**: Creates player instances based on input.
   - **RendererFactory**: Creates renderer instances based on input.
7. **Constants**: Contains predefined messages and values.
8. **Enum Mark**: Represents the marks (X, O, BLANK).

### Input Validation Rules

- The board size must be between 2 and 9.
- The winning streak must be between 2 and 9.
- The program expects exactly six command-line arguments.
- The round count must be a positive integer.

### Human Player Input

- The human player enters coordinates in the format: row column (e.g., "3 1" means row 4, column 2).
- If the coordinates are invalid or the cell is already occupied, an error message is displayed:
  ```shell
  Invalid mark position, please choose a different position.
  Invalid coordinates, type again:
  ```
- If the selected position is occupied:
  ```shell
  Mark position is already occupied.
  Invalid coordinates, type again:
  ```

### Automated Players

- **WhateverPlayer**: Randomly selects an empty cell.
- **CleverPlayer**: Uses a strategy to achieve a win rate of over 55% against WhateverPlayer.
- **GeniusPlayer**: Uses a more advanced strategy to achieve a win rate of over 55% against both CleverPlayer and WhateverPlayer.

### Development Tips

- Start by creating the interfaces.
- Ensure accurate console messages and formatting to pass automated tests.
- Utilize provided classes (e.g., ConsoleRenderer, Constants) without modification.

This project offers a practical exercise in Java programming, reinforcing concepts of object-oriented design, input validation, and strategy development for automated players.

