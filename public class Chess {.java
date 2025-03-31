public class Chess {
    public static void main(String[] args) {
   
    const board = [
  ['R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'],
  ['P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'],
  [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
  [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
  [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
  [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '],
  ['p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'],
  ['r', 'n', 'b', 'q', 'k', 'b', 'n', 'r']
];

let currentPlayer = 'white'; // Start with white's turn

function displayBoard() {
  console.log('  a b c d e f g h');
  for (let i = 0; i < 8; i++) {
    console.log(`${8 - i} ${board[i].join(' ')}`);
  }
}

function isValidMove(from, to) {

  if (from[0] < 0 || from[0] > 7 || from[1] < 0 || from[1] > 7 ||
      to[0] < 0 || to[0] > 7 || to[1] < 0 || to[1] > 7) {
    return false;
  }
  if (board[from[0]][from[1]] === ' ') {
    return false;
  }

  // Check if the piece belongs to the current player
  if (currentPlayer === 'white' && board[from[0]][from[1]].toLowerCase() === board[from[0]][from[1]]) {
    return false;
  }
  if (currentPlayer === 'black' && board[from[0]][from[1]].toUpperCase() === board[from[0]][from[1]]) {
    return false;
  }

  // Add specific move validation logic for each piece type here

  return true;
}

function movePiece(from, to) {
  if (isValidMove(from, to)) {
    board[to[0]][to[1]] = board[from[0]][from[1]];
    board[from[0]][from[1]] = ' ';
    switchPlayer();
  } else {
    console.log('Invalid move!');
  }
}

function switchPlayer() {
  currentPlayer = currentPlayer === 'white' ? 'black' : 'white';
  console.log(`It's ${currentPlayer}'s turn.`);
}


displayBoard();

// Get user input for moves
let from, to;
while (true) {
  from = prompt('Enter the starting position (e.g., A2):').toUpperCase();
  to = prompt('Enter the ending position (e.g., A4):').toUpperCase();

  // Convert user input to array indices
  from = [8 - parseInt(from[1]), 'abcdefgh'.indexOf(from[0])];
  to = [8 - parseInt(to[1]), 'abcdefgh'.indexOf(to[0])];

  movePiece(from, to);
  displayBoard();
}
}
}