import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class MinesweeperTests {

    @Test
    fun itDraws2Field() {
        val input = listOf(
            "3 3",
            ". . .",
            ". . .",
            ". . .",
            "2 2",
            ". .",
            ". .",
            "0 0"
        )
        val sut = Minesweeper(input)
        val expected = listOf(
            "Field #1:",
            "0 0 0",
            "0 0 0",
            "0 0 0",
            "Field #2:",
            "0 0",
            "0 0",
        )
        assertEquals(expected, sut.draw())
    }
}

class Minesweeper(val input: List<String>) {
    fun draw(): List<String> {
        val (field1, rest) = getFieldLines(input, 0)
        val (field2, _) = getFieldLines(rest, 0)

        return listOf("Field #1:") + field1 +
                listOf("Field #2:") + field2
    }

    private fun getFieldLines(fieldLines: List<String>, start: Int): Pair<List<String>, List<String>> {
        val field1Lines = fieldLines.drop(start)
        val head1 = field1Lines.first()
        val (n1, m1) = head1.split(" ")
        val tail1 = field1Lines.subList(1, field1Lines.size)

        return Pair(
            tail1.take(n1.toInt()).map { line ->
                val items = line.split(" ")
                items.map { item -> "0" }
            }.map { y -> y.joinToString(" ") },
            tail1.drop(n1.toInt())
        )
    }
}
