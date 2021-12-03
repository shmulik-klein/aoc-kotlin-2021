fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        var lastDepth = input[0]
        for (depth in input) {
            if (lastDepth < depth) {
                count++
            }
            lastDepth = depth
        }
        return count
    }


    fun part2(input: List<String>): Int {
        fun getWindowSum(input: List<String>, windowsIndex: Int): Int {
            return input[windowsIndex].toInt() + input[windowsIndex + 1].toInt() + input[windowsIndex + 2].toInt()
        }

        var lastWindowSum = getWindowSum(input, 0)
        var count = 0
        for (i in 1..input.size - 3) {
            val currentWindowSum = getWindowSum(input, i)
            if (lastWindowSum < currentWindowSum) {
                count++
            }
            lastWindowSum = currentWindowSum
        }
        return count
    }


    // part 1
    val input = readInput("day1_1-input")
    val part1Result = part1(input)
    println("The number of increments is $part1Result")

    // part 2
    val part2Result = part2(input)
    println("The number of increments is $part2Result")
}
