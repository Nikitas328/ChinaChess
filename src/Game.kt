enum class GameState {
    PLAYING,
    RED_WINS,
    BLACK_WINS
}
class Game {
    val board = Board()
    var state = GameState.PLAYING
        private set
    var currentPlayer = Color.RED
        private set

    init {
        board.setupInitialPosition()
    }

    fun clickPiece(position: Position): List<Position> {
        if (state != GameState.PLAYING) return emptyList()
        val piece = board.getPiece(position) ?: return emptyList()
        if (piece.color != currentPlayer) return emptyList()
        return board.getLegalMoves(position)
    }

    fun makeMove(from: Position, to: Position): Boolean {
        if (state != GameState.PLAYING) return false
        val legalMove = clickPiece(from)
        if (!legalMove.contains(to)) return false

        board.movePiece(from, to)
        currentPlayer = if (currentPlayer == Color.RED) Color.BLACK else Color.RED
        checkGameEnd()
        return true
    }
    private fun checkGameEnd() {
        if (!board.hasAnyLegalMove(currentPlayer)){
            state = if (currentPlayer == Color.RED) GameState.BLACK_WINS else GameState.RED_WINS
        }

    }
}