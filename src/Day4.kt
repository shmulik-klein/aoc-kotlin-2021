import java.io.FileReader
import java.util.*

fun main() {
    fun part1(input: List<String>): Pair<String, String> {

        var digits = Array(input[0].length) { Array(2) { 0 } }
        for (line in input) {
            for ((bitIndex, bit) in line.withIndex()) {
                if (bit.digitToInt() == 1) {
                    digits[bitIndex][1]++
                } else {
                    digits[bitIndex][0]++
                }
            }
        }
        var gamma = ""
        var epsilon = ""
        for ((digitIdx, digit) in digits.withIndex()) {
            when {
                digit[0] > digit[1] -> {
                    gamma += 0
                    epsilon += 1
                }
                digit[0] < digit[1] -> {
                    gamma += 1
                    epsilon += 0
                }
            }
        }
        return Pair(gamma, epsilon)
    }

    fun oxygen(input: List<String>): String {
        var result = input
        var pos = 0
        while (result.size != 1) {
            var (ones, zeros) = arrayOf(0, 0)
            for (num in result) {
                if (num[pos].digitToInt() == 1) {
                    ones++
                } else {
                    zeros++
                }
            }
            val filter = if (ones >= zeros) 1 else 0
            result = result.filter { it -> it[pos] == filter.digitToChar() }
            pos++
        }
        return result[0]
    }


    // part 1
    load("src/day4_1-input.txt")
}

fun load(fileName: String) {
    var calls: List<String>? = null
    val scanner = Scanner(FileReader(fileName))
    if (scanner.hasNext()) {
        calls = scanner.nextLine().split(",")
    }
    val boards: MutableList<Board> = mutableListOf()
    while (scanner.hasNext()) {
        scanner.nextLine()
        val matrix: Array<Array<MarkedNumber>> =
            Array(5) { Array(5) { MarkedNumber(0, false) } }
        for (row in 0..4) {
            for (col in 0..4)
                matrix[row][col] = MarkedNumber(scanner.nextInt(), false)
        }
        boards.add(Board(matrix, false))
        scanner.nextLine()
    }
    scanner.close()
    println("The winner is: " + roll(calls, boards))
}

data class Board(val matrix: Array<Array<MarkedNumber>>, var isWon: Boolean)

fun roll(calls: List<String>?, boards: MutableList<Board>): Int {
    var numOfBoards = boards.size;
    for (call in calls!!) {
        for (board in boards) {
            for (row in 0..4) {
                for (col in 0..4) {
                    val matrix = board.matrix
                    if (matrix[row][col].number == call.toInt()) {
                        matrix[row][col].marked = true
                        if (!board.isWon && (checkRow(matrix, row) || checkColumn(matrix, col))) {
                            board.isWon = true
                            numOfBoards -= 1
                            if (numOfBoards == 0) {
                                return matrixSum(matrix) * call.toInt()
                            }
                        }
                    }
                }
            }
        }
    }
    return 0
}

fun matrixSum(matrix: Array<Array<MarkedNumber>>): Int {
    var sum = 0
    for (row in 0..4) {
        for (col in 0..4) {
           if (!matrix[row][col].marked) {
               sum += matrix[row][col].number
           }
        }
    }
    return sum
}

fun checkRow(matrix: Array<Array<MarkedNumber>>, row: Int): Boolean {
    var isBingo = true
    for (col in 0..4) {
        if (!matrix[row][col].marked) {
            isBingo = false
        }
    }
    return isBingo
}

fun checkColumn(matrix: Array<Array<MarkedNumber>>, col: Int): Boolean {
    var isBingo = true
    for (row in 0..4) {
        if (!matrix[row][col].marked) {
            isBingo = false
        }
    }
    return isBingo
}

data class MarkedNumber(val number: Int, var marked: Boolean)
