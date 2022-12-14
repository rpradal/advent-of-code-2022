package day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day5KtTest {

    @Test
    fun solveDay5Part1Puzzle() {
        val testFile = File("src/test/kotlin/day05/sample")

        val result = solveDay5Part1Puzzle(testFile)

        assertEquals("CMZ", result)
    }

    @Test
    fun solveDay5Part2Puzzle() {
        val testFile = File("src/test/kotlin/day05/sample")

        val result = solveDay5Part2Puzzle(testFile)

        assertEquals("MCD", result)
    }
}
