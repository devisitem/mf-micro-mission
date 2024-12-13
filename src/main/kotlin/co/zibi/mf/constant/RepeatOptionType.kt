package co.zibi.mf.constant

import com.fasterxml.jackson.annotation.JsonValue

enum class RepeatOptionType (@JsonValue val value: Int) {
    NONE(-1),
    WEEKLY(1),
    MONTHLY(2),
    YEARLY(3);

    fun isWeekly(): Boolean {
        return WEEKLY == this
    }
}