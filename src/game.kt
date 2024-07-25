import javax.transaction.xa.Xid
import kotlin.random.Random

var board = arrayListOf<ArrayList<String>>()

fun main (){
    for (i in 0..2) {
        val row: ArrayList<String> = arrayListOf<String>()

        for (j in 0..2)
            row.add(" ")
        board.add(row)
    }
        printBoard()
        var continueGame = true
        do {
            println("please enter the position to play in format eg. 1,1")
            val input = readLine()?:""
            var x = 0
            var y = 0
            try {
                val positions = input.split(",")
                x= positions[0].trim().toInt()
                y = positions[1].trim().toInt()
                var skipRound = false

                if (board[x-1][y-1] != " "){
                    println("position tacken")
                    skipRound = true

                }else{
                    board[x-1][y-1] = "X"
                printBoard()
                }

                if (!skipRound){
                    val playerWon = false
                    if (playerWon) {
                        println("congratulations you won")
                    continueGame = false
                    }
                    val boardFull = checkBoardFull()
                    if (!playerWon && boardFull){
                        println("its a tie")
                        continueGame = false

                    }
                    if (continueGame){
                        placeComputer()
                        printBoard()
                        val computerWon = checkWinner(false)
                        if (computerWon){
                            println("Computer won")
                            continueGame  = false
                        }

                    }

                }



            }catch (e: Exception){
                println("invalid input")
            }




        }while (continueGame)


    }








fun printBoard(){
    println("-------")
    for (i in 0..2)
    {
        for (j in 0..2){
            when(board[i][j]){
                "X" -> print("| X ")
                "O" -> print("| O ")
                else -> print("| ")
            }

        }
      println("| ")
        println("-------")
    }
}

fun checkWinner(player: Boolean) : Boolean{
   var won = false
   val checkSymbol = if (player) "X" else "0"
    for (i in 0..2){
        // horizontal wins
        if (board[i][0]==checkSymbol && board[i][1] == checkSymbol && board[i][2] == checkSymbol ){
            won  = true
            break
        }
        // vertical wins
        if (board[0][i] == checkSymbol && board[1][i] ==checkSymbol && board[2][i]==checkSymbol){
            won = true
            break
        }
    }
    // diagnol wins
    if (board[0][0] == checkSymbol && board[1][1] == checkSymbol && board[2][2] == checkSymbol){
        won = true

    }
    if (board[2][0] == checkSymbol && board[1][1] == checkSymbol && board[0][2] == checkSymbol){
        won = true

    }
return won

}
fun checkBoardFull (): Boolean{
    var boardFull = true
    for (i in 0..2)
        for (j in 0..2)
            if (board[i][j] == " "){
                boardFull = false
            }
    return boardFull
}
fun placeComputer(){
    var i =0
    var j = 0
   do {
        i = Random.nextInt(3)
        j = Random.nextInt(3)
   } while (board[i][j] != " ")
   board[i][j] = "O"
}