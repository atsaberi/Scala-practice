package practice.algorithm

object WordSearch {
  def search(board: Array[Array[Char]], word: String, row: Int, col: Int, nbRows: Int, nbCols: Int, wordIndex: Int, isSeen: Array[Array[Boolean]]): Boolean = {
    if (wordIndex == word.length() - 1)
      true

    if (row>= nbRows || col >= nbCols || row < 0 || col < 0 || board(row)(col).toLower != word.charAt(wordIndex).toLower || isSeen(row)(col))
      false

    isSeen(row)(col) = true
    val found = search(board, word, row - 1, col, nbRows, nbCols, wordIndex + 1, isSeen) ||
      search(board, word, row + 1, col, nbRows, nbCols, wordIndex + 1, isSeen) ||
      search(board, word, row, col - 1, nbRows, nbCols, wordIndex + 1, isSeen) ||
      search(board, word, row, col + 1, nbRows, nbCols, wordIndex + 1, isSeen)

    isSeen(row)(col) = false
    found

  }

  def exist(board: Array[Array[Char]], word: String): Boolean = {
    val nbRows: Int = board.length
    val nbCols: Int = board(0).length
    var wordIndex = 0
    var isSeen: Array[Array[Boolean]] = Array.ofDim(2, 2)

    // loop through cells 
    for (row:Int <- 0 to nbRows) {
      for (col: Int <- 0 to nbCols) {
        if (board(row)(col) == word.charAt(wordIndex) && search(board, word, row, col, nbRows, nbCols, wordIndex, isSeen)) {
          true

        }
      }
    }
    false
  }

}
