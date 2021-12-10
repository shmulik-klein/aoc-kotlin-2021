import java.io.FileReader
import java.util.*

fun main() {
    fun part1(input: List<String>) {
    }

    // part 1
    loadCoordinates("src/day5_1-input.txt")
}

fun loadCoordinates(fileName: String) {
    val scanner = Scanner(FileReader(fileName))
    var plain = Array(1000) { Array(1000) { 0 } }
    while (scanner.hasNext()) {
        val coords =
            scanner.nextLine()
                .splitToSequence("->", ",")
                .map { it.trim().toInt() }.toList()
        val line =
            Line(Point(coords[0], coords[1]), Point(coords[2], coords[3]))
        println("$line:")
        if (line.isHorizontal()) {
            println("horizontal")
            val range = line.p1.x.coerceAtMost(line.p2.x).rangeTo(line.p1.x.coerceAtLeast(line.p2.x))
            println(range)
            for (x in range) {
                plain[x][line.p1.y]++
                print("($x, ${line.p1.y}) ")
            }
            println()
        }

        if (line.isVertical()) {
            println("Vertical")
            val range = line.p1.y.coerceAtMost(line.p2.y).rangeTo(line.p1.y.coerceAtLeast(line.p2.y))
            println(range)
            for (y in range) {
                plain[line.p1.x][y]++
                print("(${line.p1.x}, $y) ")
            }
        }
    }
    scanner.close()
    var dangerous = 0
    for (row in plain.indices) {
        for (col in plain.indices) {
            if (plain[row][col] > 1) {
                dangerous++
            }
            print(plain[row][col].toString() + ",")
        }
        println()
    }
    println(dangerous)
}

data class Point(val x: Int, val y: Int)
data class Line(val p1: Point, val p2: Point) {
    fun isHorizontal(): Boolean {
        return p1.y == p2.y
    }

    fun isVertical(): Boolean {
        return p1.x == p2.x
    }
}