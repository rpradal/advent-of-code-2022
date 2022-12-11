package day11

import java.io.File
import java.math.BigInteger

sealed class Operation {
    object Squared : Operation() {
        override fun apply(value: BigInteger): BigInteger = value * value
    }

    data class Plus(val number: BigInteger) : Operation() {
        override fun apply(value: BigInteger): BigInteger = value + number
    }

    data class Times(val number: BigInteger) : Operation() {
        override fun apply(value: BigInteger): BigInteger = value * number
    }

    abstract fun apply(value: BigInteger): BigInteger

    companion object {

        fun fromString(stringRepresentation: String): Operation {
            return stringRepresentation
                .replace("  Operation: new = old ", "")
                .let {
                    when {
                        it.contains("old") -> Squared
                        it.contains("+") -> Plus(it.split(" ")[1].toBigInteger())
                        it.contains("*") -> Times(it.split(" ")[1].toBigInteger())
                        else -> throw IllegalArgumentException()
                    }
                }
        }
    }
}

data class Test(
    val divider: Int,
    val truthyRecipient: Int,
    val falsyRecipient: Int,
) {
    companion object {
        fun fromString(stringRepresentation: List<String>): Test {
            return Test(
                divider = stringRepresentation[0].replace("  Test: divisible by ", "").toInt(),
                truthyRecipient = stringRepresentation[1].replace("    If true: throw to monkey ", "").toInt(),
                falsyRecipient = stringRepresentation[2].replace("    If false: throw to monkey ", "").toInt()
            )
        }
    }
}

data class Monkey(
    val items: ArrayDeque<BigInteger>,
    val operation: Operation,
    val test: Test,
    var inspectionCount: Long,
) {
    companion object {
        fun fromString(moneyStringRepresentation: List<String>): Monkey {
            return Monkey(
                items = moneyStringRepresentation[1]
                    .replace("  Starting items: ", "")
                    .split(", ")
                    .map(String::toBigInteger)
                    .let(::ArrayDeque),
                operation = Operation.fromString(moneyStringRepresentation[2]),
                test = Test.fromString(moneyStringRepresentation.subList(3, 6)),
                inspectionCount = 0,
            )
        }
    }
}

private fun monkeyBusinessLevel(file: File, levelDivision: Long, roundCount: Int): Long {
    val monkeys = file
        .readLines()
        .joinToString("\n")
        .split("\n\n")
        .map { Monkey.fromString(it.split("\n")) }

    repeat(roundCount) {
        println(it)
        if (it == 20) {
            println("foo")
        }
        monkeys.forEach { monkey ->
            monkey.items.forEach {
                monkey.inspectionCount += 1
                val newWorryValue = monkey.operation.apply(it) / levelDivision.toBigInteger()
                if (newWorryValue % monkey.test.divider.toBigInteger() == BigInteger.ZERO) {
                    monkeys[monkey.test.truthyRecipient].items.addLast(newWorryValue)
                } else {
                    monkeys[monkey.test.falsyRecipient].items.addLast(newWorryValue)
                }
            }
            monkey.items.clear()
        }
    }

    return monkeys
        .map(Monkey::inspectionCount)
        .sortedDescending()
        .take(2)
        .reduce(Long::times)
}

fun solveDay11Part1Puzzle(file: File): Long {
    return monkeyBusinessLevel(file,3, 20)
}

fun solveDay11Part2Puzzle(file: File): Long {
    return monkeyBusinessLevel(file,1, 10000)
}