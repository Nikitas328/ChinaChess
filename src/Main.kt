fun main(){
    val board = Board()
    board.setupInitialPosition()
    val clickedPosition = Position.RED_ELEPHANT_LEFT
    val availableMoves = board.getAvailableMoves(clickedPosition)

    println("general red ${board.findGeneralPosition(Color.RED)}")
    println("general black ${board.findGeneralPosition(Color.BLACK)}")


    println("Фигура на клетке $clickedPosition может пойти в:")
    if(availableMoves.isEmpty()){
        println("ходов нет")
    }else{
        for( move in availableMoves){
            println("-> $move")
        }
    }
}