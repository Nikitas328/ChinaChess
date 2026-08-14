enum class Color { RED, BLACK }

sealed class Piece(val color: Color) {
    abstract fun getPseudoLegalMoves(from: Position, board: Board): List<Position>
}

class General (color: Color): Piece(color){
    companion object{
        val getPalaceXRange = 3..5
        fun getPalaceYRange(color: Color) = if(color == Color.RED) 0..2 else 7..9
    }
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        val directions = listOf(
            Position(1, 0),
            Position(0, -1),
            Position(-1, 0),
            Position(0, 1),
        )
        for (direction in directions){

            val step = Position(from.x + direction.x, from.y + direction.y)

            if(!step.isValidToBoard()){continue}
            if(color == Color.RED && (step.x !in getPalaceXRange  || step.y !in getPalaceYRange(Color.RED))){continue}
            if(color == Color.BLACK && (step.x !in getPalaceXRange  || step.y !in getPalaceYRange(Color.BLACK))){continue}


            val targetPiece = board.getPiece(step)
            if(targetPiece == null){
                validMoves.add(step)
            } else {
                if(targetPiece.color != this.color){
                    validMoves.add(step)
                }

            }

        }
        return validMoves.toList()
    }
}
class Advisor  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        val directions = listOf(
            Position(1, 1),
            Position(1, -1),
            Position(-1, -1),
            Position(-1, 1),
        )
        for (direction in directions){

            val step = Position(from.x + direction.x, from.y + direction.y)

            if(!step.isValidToBoard()){continue}
            if(color == Color.RED && (step.x !in 3..5  || step.y !in 0..2)){continue}
            if(color == Color.BLACK && (step.x !in 3..5  || step.y !in 7..9)){continue}

            val targetPiece = board.getPiece(step)
            if(targetPiece == null){
                validMoves.add(step)
            } else {
                if(targetPiece.color != this.color){
                    validMoves.add(step)
                }

            }

        }
        return validMoves.toList()
    }
}
class Elephant  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        val directions = listOf(
            Position(2, 2),
            Position(-2, 2),
            Position(2, -2),
            Position(-2, -2),
        )
        for (direction in directions){
            val step = Position(from.x + direction.x, from.y + direction.y)
            if(!step.isValidToBoard()){continue}
            if(color == Color.RED && step.y > 4){continue}
            if(color == Color.BLACK && step.y < 5){continue}

            val eyePosition = Position(from.x + direction.x/2, from.y + direction.y/2)
            if(board.getPiece(eyePosition) != null){continue}

            val targetPiece = board.getPiece(step)
            if(targetPiece == null){
                validMoves.add(step)
            } else {
                if(targetPiece.color != this.color){
                    validMoves.add(step)
                }
            }

        }
        return validMoves.toList()
    }
}
class Horse  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        val directions = listOf(
            Position(-1, 2),
            Position(1, 2),

            Position(2, 1),
            Position(2, -1),

            Position(-1, -2),
            Position(1, -2),

            Position(-2, -1),
            Position(-2, 1),
        )
        for (direction in directions){
            val step = Position(from.x + direction.x, from.y + direction.y)
            if(!step.isValidToBoard()){continue}

            val eyePosition: Position = when{
                Math.abs(direction.x) == 2 -> Position(from.x + direction.x/2, from.y)
                else -> Position(from.x, from.y + direction.y/2)
            }

            if(board.getPiece(eyePosition) != null){continue}

            val targetPiece = board.getPiece(step)
            if(targetPiece == null){
                validMoves.add(step)
            } else {
                if(targetPiece.color != this.color){
                    validMoves.add(step)
                }
            }

        }
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
        for (direction in directions){
            var step = Position(from.x, from.y)

            while (true){
                step = Position(step.x + direction.x, step.y + direction.y)
                if(!step.isValidToBoard()){break}
                val targetPiece = board.getPiece(step)
                if(targetPiece == null){
                    validMoves.add(step)
                } else {
                    if(targetPiece.color != this.color){
                        validMoves.add(step)
                    }
                    break
                }

            }
        }
        return validMoves.toList()
    }
}
class Cannon  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        val directions = listOf(
            Position(1, 0),
            Position(0, -1),
            Position(-1, 0),
            Position(0, 1),
        )
        for (direction in directions){
            var step = Position(from.x, from.y)

            while (true){
                step = Position(step.x + direction.x, step.y + direction.y)
                if(!step.isValidToBoard()){break}

                val targetPiece = board.getPiece(step)
                if(targetPiece == null){
                    validMoves.add(step)
                } else {
                    while (true){
                        step = Position(step.x + direction.x, step.y + direction.y)
                        if(!step.isValidToBoard()){break}
                        val targetPiece = board.getPiece(step)
                        if(targetPiece == null){
                            continue
                        } else {
                            if(targetPiece.color != this.color){
                                validMoves.add(step)
                            }
                            break
                        }
                    }
                    break
                }

            }
        }
        return validMoves.toList()
    }
}
class Soldier  (color: Color): Piece(color){
    override fun getPseudoLegalMoves(from: Position, board: Board): List<Position> {
        val validMoves = mutableListOf<Position>()
        val directions = listOf(
            Position(1, 0),
            Position(0, -1),
            Position(-1, 0),
            Position(0, 1),
        )
        for (direction in directions){

            val step = Position(from.x + direction.x, from.y + direction.y)

            if(!step.isValidToBoard()){continue}
            if(color == Color.RED && from.y < 5 && direction.y != 1){continue}
            if(color == Color.BLACK && from.y > 4 && direction.y != -1){continue}

            if(color == Color.RED && direction.y == -1){continue}
            if(color == Color.BLACK && direction.y == 1){continue}

            val targetPiece = board.getPiece(step)
            if(targetPiece == null){
                validMoves.add(step)
            } else {
                if(targetPiece.color != this.color){
                    validMoves.add(step)
                }
            }

        }
        return validMoves.toList()
    }
}