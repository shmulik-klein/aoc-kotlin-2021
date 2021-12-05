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

    fun co2Scrapper(input: List<String>): String {
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
            val filter = if (ones < zeros) 1 else 0
            result = result.filter { it[pos] == filter.digitToChar() }
            pos++
        }
        return result[0]
    }


    // part 1
    val input = readInput("day3_1-input")
    val (gamma, epsilon) = part1(input)
    println("Solution $gamma * $epsilon is ${gamma.toInt(2) * epsilon.toInt(2)}")

    // part 2
    val oxygenRating = oxygen(input)
    println("Oxygen generator rating: ${oxygenRating.toInt(2)}")

    val scrubberRating = co2Scrapper(input)
    println("Co2 rating: ${scrubberRating.toInt(2)}")
    println(
        "Life support rating: ${
            scrubberRating.toInt(2) * oxygenRating.toInt(
                2
            )
        }"
    )
}