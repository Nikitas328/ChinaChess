enum class Color { RED, BLACK }
sealed class Piece(val color: Color) {
    abstract fun getPseudoLegalMoves(from: Position, board: Board)
}
class General (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board) {

    }
}
class Advisor  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board) {

    }
}
class Elephant  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board) {

    }
}
class Horse  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board) {

    }
}
class Chariot  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board) {

    }
}
class Cannon  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board) {

    }
}
class Soldier  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board) {

    }
}