package two

private const val ADD = 1
private const val MLT = 2
private const val END = 99
private const val OUTPUT_OFFSET = 3

private const val OP_LENGTH = 4

class PartOne(private val input: Array<Int> = INPUT) {
    fun resultForSeeds(seed1: Int = 12, seed2: Int = 2): Int {
        val restored = input.toMutableList().apply { this[1] = seed1; this[2] = seed2 }
        loop@ for (i in input.indices step OP_LENGTH) {
            when (restored[i]) {
                ADD -> restored.execute(i, Int::plus)
                MLT -> restored.execute(i, Int::times)
                END -> break@loop
            }
        }
        return restored[0]
    }
}

class PartTwo(private val partOne: PartOne = PartOne()) {

    fun findOutput(desiredValue: Int = 19690720): Int {
        val (noun, verb) = findNounAndVerb(desiredValue)
        return 100 * noun + verb
    }

    private fun findNounAndVerb(desiredValue: Int): Pair<Int, Int> {
        for(i in 0..99) {
            for(j in 0..99) {
                val result = partOne.resultForSeeds(i, j)
                if (result == desiredValue) return i to j
            }
        }
        throw IllegalArgumentException()
    }
}

private fun MutableList<Int>.execute(position: Int, operation: Int.(Int) -> Int) {
    this[this[position + OUTPUT_OFFSET]] = this[this[position + 1]].operation(this[this[position + 2]])
}

private val INPUT = arrayOf(
    1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,9,1,19,1,9,19,23,1,23,5,27,2,27,10,31,1,6,31,35,1,6,35,39,2,9,39,43,1,6,43,47,1,47,5,51,1,51,13,55,1,55,13,59,1,59,5,63,2,63,6,67,1,5,67,71,1,71,13,75,1,10,75,79,2,79,6,83,2,9,83,87,1,5,87,91,1,91,5,95,2,9,95,99,1,6,99,103,1,9,103,107,2,9,107,111,1,111,6,115,2,9,115,119,1,119,6,123,1,123,9,127,2,127,13,131,1,131,9,135,1,10,135,139,2,139,10,143,1,143,5,147,2,147,6,151,1,151,5,155,1,2,155,159,1,6,159,0,99,2,0,14,0
)