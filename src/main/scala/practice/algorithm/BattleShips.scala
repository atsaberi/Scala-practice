package practice.algorithm

object BattleShips {
   def nbBattleShips(board: Array[Array[Char]]):Int= {
    val nbRows: Int = board.length
    val nbCols: Int = board(0).length
    var nbShips: Int = 0
    var isNew: Boolean = false;

    for(row <- 0 until nbRows) {
      for (col <- 0 until nbCols) {
        if (board(row)(col) == 'X') {
          isNew = true
          if (row - 1 >= 0 && board(row - 1)(col) == 'X') {
            isNew = false
          }
          if (col - 1 >= 0 && board(row)(col - 1) == 'X') {
            isNew = false
          }

          if(isNew) nbShips+=1
        }
      }
    }
    nbShips
  }

  def shipsPercentage(board: Array[Array[Char]]): Float = {
    val nbRows: Int = board.length
    val nbCols: Int = board(0).length
    var nbShipCells: Int = 0
    var shipsPerc: Float = 0

    for(row <- 0 until nbRows) {
      for(col <- 0 until nbCols) {
        if (board(row)(col) == 'X')
          nbShipCells+=1
      }
    }
    val total = nbRows*nbCols
    (nbShipCells.toFloat)/total
  }

}
