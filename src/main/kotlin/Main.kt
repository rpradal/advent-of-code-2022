import day1.solveDay1Part1Puzzle
import day1.solveDay1Part2Puzzle
import day2.solveDay2Part1Puzzle
import day2.solveDay2Part2Puzzle
import java.io.File

fun main() {
    println("Day1")
    println("Part1: ${solveDay1Part1Puzzle(File("src/main/kotlin/day1/input"))}")
    println("Part2: ${solveDay1Part2Puzzle(File("src/main/kotlin/day1/input"))}")

    println("Day2")
    println("Part1: ${solveDay2Part1Puzzle(File("src/main/kotlin/day2/input"))}")
    println("Part2: ${solveDay2Part2Puzzle(File("src/main/kotlin/day2/input"))}")
}

