enum class Color { RED, BLACK }
sealed class Piece(val color: Color) {
    abstract fun getPseudoLegalMoves(from: Position, board: Board): List<Position>
}

class General (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()

        return validMoves.toList()
    }
}
class Advisor  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        return validMoves.toList()
    }
}
class Elephant  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        return validMoves.toList()
    }
}
class Horse  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        return validMoves.toList()
    }
}
class Chariot  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        val directions = listOf(
            Position(1, 0),
            Position(0, -1),
            Position(-1, 0),
            Position(0, 1),
        )

        return validMoves.toList()
    }
}
class Cannon  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        return validMoves.toList()
    }
}
class Soldier  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        return validMoves.toList()
    }
}