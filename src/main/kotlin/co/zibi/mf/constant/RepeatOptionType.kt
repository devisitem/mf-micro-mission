package co.zibi.mf.constant

import com.fasterxml.jackson.annotation.JsonValue

enum class RepeatOptionType (@JsonValue val value: Int) {
    NONE(-1),
    WEEKLY(1),
    MONTHLY(2),
    YEARLY(3);

    companion object {
        private val CACHED = entries.associateBy { it.value }

        fun has(value: Int): Boolean {
            return CACHED.containsKey(value)
        }

    }

    fun isWeekly(): Boolean {
        return WEEKLY == this
    }

}