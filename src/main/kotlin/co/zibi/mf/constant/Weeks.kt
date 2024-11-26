package co.zibi.mf.constant

import co.zibi.mf.exception.error.ValidateError

enum class Weeks private constructor(value: Int) {
    MONDAY(1),
    ;


    val index: Int = value
    val decimal: Int = 1 shl (value -1)

    companion object {
        private val CACHED_BY_INDEX = entries.associateBy { it.index }
        private val CACHED_BY_DECIMAL = entries.associateBy { it.decimal }

        private fun fromIndex(index: Int): Weeks? {
            return CACHED_BY_INDEX[index]
        }

        private fun fromDecimal(decimal: Int): Weeks? {
            return CACHED_BY_DECIMAL[decimal]
        }

        fun findWeeks(indexes: List<Int>): List<Weeks> {
            //변경이 없는경우
            if(indexes.isEmpty()) {
                return emptyList()
            }

            val selected = indexes
                .mapNotNull { fromIndex(it) }
                .sumOf { it.decimal }

            return (0 until Int.SIZE_BITS)
                .map { selected and (1 shl it) }
                .filter { it != 0 }
                .mapNotNull(Companion::fromDecimal)
        }

        fun toIndexes(selected: Int): List<Int> {
            if (selected == 0) {
                return emptyList()
            }
            return (0 until Int.SIZE_BITS)
                .map { selected and (1 shl it) }
                .filter { it != 0 }
                .mapNotNull { fromDecimal(it)?.index }
        }

        fun toSelected(weeks: List<Weeks>): Int {
            return weeks.sumOf { it.decimal }
        }

        fun toSelected(indexes: List<Int>): Int {
            return findWeeks(indexes).sumOf { it.decimal }
        }
    }

}