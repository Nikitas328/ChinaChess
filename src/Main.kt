fun main(){
    val board = Board()
    board.setupInitialPosition()
    val clickedPosition = Position.BLACK_CHARIOT_RIGHT
    val availableMoves = board.getAvailableMoves(clickedPosition)


    println("Фигура на клетке $clickedPosition может пойти в:")
    if(availableMoves.isEmpty()){
        println("ходов нет")
    }else{
        for( move in availableMoves){
            println("-> $move")
        }
    }
}