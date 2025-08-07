import practice.{IsPalindrome, SuperDigit}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@main
def main(): Unit =
  val seeBoard:Array[Array[Char]] = Array(Array('X','.', '.', 'X'), Array('.','.', '.', 'X'), Array('.','.', '.', 'X'))
  val seeBoard2: Array[Array[Char]] = Array(Array('.', 'X'), Array('X', '.'))

  val seaboard3: Array[Array[Char]] = Array.ofDim[Char](3, 3)
  seaboard3(0)(0) = '.'
  seaboard3(0)(0) = '.'
  seaboard3(0)(0) = 'X'
  seaboard3(0)(0) = 'X'


  val seaboard4: Array[Array[Char]] = Array.ofDim[Char](5, 4)
  seaboard4(0) = Array('X','.', '.', 'X')
  seaboard4(1) = Array('.','.', '.', 'X')
  seaboard4(2) = Array('.', '.', '.', 'X')



  //println(" percentage of Battle Ship cells  : " + BattleShips.shipsPercentage(seeBoard))
  //println(" percentage of Battle Ship cells  : " + BattleShips.nbBattleShips(seaboard3 ))

  //(1 to 5).map(println)
  //for (i <- 1 to 5) {
  //  println(s"i = $i")
  //  println(s"i = $i")
  // }


  // ##### Word search

  val word = "ABCCED"
  val board:Array[Array[Char]] = Array.ofDim[Char](4,3)
  board(0) = Array('A','B','C','E')
  board(1) = Array('S','F','C','S')
  board(2) = Array('A','D','E','E')

  //println(" qword search  : " + WordSearch.exist(board, word))
  val s: String = "ab998gba"
  //val s: String = "abbba"
  //println("is Palindrojme  : " + IsPalindrome.isPalindrome(s))


  //println(board.reverse)

  val args = Array("14879207699999211110000000000000001  99999")
  SuperDigit.main(args)

