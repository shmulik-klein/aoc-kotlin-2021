fun main() {
    fun part1(input: List<String>): Int {

        var digits = Array(input[0].length) { Array(2) { 0 } }
        for (line in input) {
            for ( (bitIndex, bit) in line.withIndex()) {
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
            println("For bit ${digitIdx}: ones[${digit[1]}], zero[${digit[0]}]")
            if (digit[0] > digit[1]) {
                gamma += 0
                epsilon += 1
            } else {
                gamma += 1
                epsilon += 0
            }
        }
        println("Gamma: $gamma (${gamma.toInt(2)}) and Epsilon $epsilon (${epsilon.toInt(2)})")
        return gamma.toInt(2) * epsilon.toInt(2)
    }


    fun part2(input: List<String>): Int {
        return 0
    }


    // part 1
    val input = readInput("day3_1-input")
    val part1Result = part1(input)
    println("The number of increments is $part1Result")

    // part 2
    val part2Result = part2(input)
    println("The number of increments is $part2Result")
}
