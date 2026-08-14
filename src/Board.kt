import General.Companion.getPalaceXRange
import kotlin.math.abs

data class Position(val x: Int, val y: Int) {
    companion object {
        // Задняя линия (y = 0)
        val RED_CHARIOT_LEFT = Position(0, 0) // Ладья левая
        val RED_HORSE_LEFT = Position(1, 0) // Конь левый
        val RED_ELEPHANT_LEFT = Position(2, 0) // Слон левый
        val RED_ADVISOR_LEFT = Position(3, 0) // Советник левый
        val RED_GENERAL_START = Position(4, 0) // Генерал (Король)
        val RED_ADVISOR_RIGHT = Position(5, 0) // Советник правый
        val RED_ELEPHANT_RIGHT = Position(6, 0) // Слон правый
        val RED_HORSE_RIGHT = Position(7, 0) // Конь правый
        val RED_CHARIOT_RIGHT = Position(8, 0) // Ладья правая

        // Линия пушек (y = 2)
        val RED_CANNON_LEFT = Position(1, 2)
        val RED_CANNON_RIGHT = Position(7, 2)

        // Линия солдат (y = 3) - стоят через одну клетку
        val RED_SOLDIER_1 = Position(0, 3)
        val RED_SOLDIER_2 = Position(2, 3)
        val RED_SOLDIER_3 = Position(4, 3)
        val RED_SOLDIER_4 = Position(6, 3)
        val RED_SOLDIER_5 = Position(8, 3)



        // Задняя линия (y = 9)
        val BLACK_CHARIOT_LEFT = Position(0, 9)
        val BLACK_HORSE_LEFT = Position(1, 9)
        val BLACK_ELEPHANT_LEFT = Position(2, 9)
        val BLACK_ADVISOR_LEFT = Position(3, 9)
        val BLACK_GENERAL_START = Position(4, 9)
        val BLACK_ADVISOR_RIGHT = Position(5, 9)
        val BLACK_ELEPHANT_RIGHT = Position(6, 9)
        val BLACK_HORSE_RIGHT = Position(7, 9)
        val BLACK_CHARIOT_RIGHT = Position(8, 9)

        // Линия пушек (y = 7)
        val BLACK_CANNON_LEFT = Position(1, 7)
        val BLACK_CANNON_RIGHT = Position(7, 7)

        // Линия солдат (y = 6)
        val BLACK_SOLDIER_1 = Position(0, 6)
        val BLACK_SOLDIER_2 = Position(2, 6)
        val BLACK_SOLDIER_3 = Position(4, 6)
        val BLACK_SOLDIER_4 = Position(6, 6)
        val BLACK_SOLDIER_5 = Position(8, 6)
    }

    fun isValidToBoard(): Boolean {
        return x in 0..8 && y in 0..9
    }
}
class Board {
    private val cells = mutableMapOf<Position, Piece>()

    fun getLegalMoves(clickedPosition: Position): List<Position> {
        val legalMoves = mutableListOf<Position>()
        val availablelMoves = getAvailableMoves(clickedPosition)
        val piece = cells[clickedPosition]?: return legalMoves
        cells.remove(clickedPosition)
        for (move in availablelMoves) {
            val capturedPiece = cells[move]
            cells[move] = piece
            if (!eyeGeneral() && isGeneralofCheck(piece.color).isEmpty()){
                legalMoves.add(move)
            }
            cells.remove(move)
            if (capturedPiece != null) {
                cells[move] = capturedPiece
            }
        }
        cells[clickedPosition] = piece

        return legalMoves
    }
    fun getAvailableMoves(clickedPosition: Position): List<Position> {
        val targetPiece = cells[clickedPosition]?: return emptyList()
        return targetPiece.getPseudoLegalMoves(clickedPosition, this )
    }

    fun eyeGeneral(): Boolean {
        val redGeneralPos = findGeneralPosition(Color.RED)?: return false
        val blackGeneralPos = findGeneralPosition(Color.BLACK)?: return false
        if(redGeneralPos.x != blackGeneralPos.x) return false
        for (i in redGeneralPos.y+1..<blackGeneralPos.y) {
            if (getPiece(Position(redGeneralPos.x, i)) != null) {
                return false
            }
        }
        return true
    }

    fun isGeneralofCheck(color: Color): List<Position> {
        val generalofCheck = mutableListOf<Position>()
        val generalPos = findGeneralPosition(color) ?: return emptyList()

        for ((pos,piece) in cells) {
            if (piece.color == color) continue

            val moves = piece.getPseudoLegalMoves(pos, this)
            if(moves.contains(generalPos)) generalofCheck.add(pos)

        }
        return generalofCheck
    }

    fun findGeneralPosition(color: Color):Position?{
        val xRange = General.getPalaceXRange
        val yRange = General.getPalaceYRange(color)
        for (x in xRange ){
            for (y in yRange ){

                val pos = Position(x, y)
                val piece = cells[pos]

                if(piece is General && color == piece.color ){
                    return pos

                }
            }
        }
        return null
    }

    fun setupInitialPosition(){
        cells[Position.RED_GENERAL_START] = General(Color.RED)
        cells[Position.RED_ADVISOR_LEFT] = Advisor(Color.RED)
        cells[Position.RED_ADVISOR_RIGHT] = Advisor(Color.RED)
        cells[Position.RED_ELEPHANT_LEFT] = Elephant(Color.RED)
        cells[Position.RED_ELEPHANT_RIGHT] = Elephant(Color.RED)
        cells[Position.RED_HORSE_LEFT] = Horse(Color.RED)
        cells[Position.RED_HORSE_RIGHT] = Horse(Color.RED)
        cells[Position.RED_CHARIOT_LEFT] = Chariot(Color.RED)
        cells[Position.RED_CHARIOT_RIGHT] = Chariot(Color.RED)
        cells[Position.RED_CANNON_LEFT] = Cannon(Color.RED)
        cells[Position.RED_CANNON_RIGHT] = Cannon(Color.RED)

        cells[Position.RED_SOLDIER_1] = Soldier(Color.RED)
        cells[Position.RED_SOLDIER_2] = Soldier(Color.RED)
        cells[Position.RED_SOLDIER_3] = Soldier(Color.RED)
        cells[Position.RED_SOLDIER_4] = Soldier(Color.RED)
        cells[Position.RED_SOLDIER_5] = Soldier(Color.RED)

        cells[Position.BLACK_GENERAL_START] = General(Color.BLACK)
        cells[Position.BLACK_ADVISOR_LEFT] = Advisor(Color.BLACK)
        cells[Position.BLACK_ADVISOR_RIGHT] = Advisor(Color.BLACK)
        cells[Position.BLACK_ELEPHANT_LEFT] = Elephant(Color.BLACK)
        cells[Position.BLACK_ELEPHANT_RIGHT] = Elephant(Color.BLACK)
        cells[Position.BLACK_HORSE_LEFT] = Horse(Color.BLACK)
        cells[Position.BLACK_HORSE_RIGHT] = Horse(Color.BLACK)
        cells[Position.BLACK_CHARIOT_LEFT] = Chariot(Color.BLACK)
        cells[Position.BLACK_CHARIOT_RIGHT] = Chariot(Color.BLACK)
        cells[Position.BLACK_CANNON_LEFT] = Cannon(Color.BLACK)
        cells[Position.BLACK_CANNON_RIGHT] = Cannon(Color.BLACK)

        cells[Position.BLACK_SOLDIER_1] = Soldier(Color.BLACK)
        cells[Position.BLACK_SOLDIER_2] = Soldier(Color.BLACK)
        cells[Position.BLACK_SOLDIER_3] = Soldier(Color.BLACK)
        cells[Position.BLACK_SOLDIER_4] = Soldier(Color.BLACK)
        cells[Position.BLACK_SOLDIER_5] = Soldier(Color.BLACK)
    }

    fun getPiece(position: Position): Piece? = cells[position]
}