import java.io.FileReader
import java.util.*
import kotlin.streams.toList

fun main() {
    fun part1(input: List<String>) {
    }

    // part 1
    val ages = loadLanterfishAges("src/day6_1-test.txt")
    var schoolFish = mutableListOf<LanternFish>()
    for (age in ages) {
        schoolFish.add(LanternFish(age))
    }
    for (itr in 1..80) {
        val newGeneration = mutableListOf<LanternFish>()
        schoolFish.forEach {f ->
            if (f.age == 0) {
                newGeneration.add(f.reset())
                newGeneration.add(LanternFish(8))
            } else {
                newGeneration.add(f.nextDay())
            }
        }
        schoolFish = newGeneration
        println("Day $itr: ${newGeneration.size}")
    }

}

fun loadLanterfishAges(fileName: String): List<Int> {
    val scanner = Scanner(FileReader(fileName))
    val ages = scanner.nextLine().split(",")
    return ages.stream().map { it.toInt() }.toList()
}

data class LanternFish(var age: Int) {
    fun reset(): LanternFish {
        age = 6
        return this
    }

    fun nextDay(): LanternFish {
        age--
        return this
    }
}