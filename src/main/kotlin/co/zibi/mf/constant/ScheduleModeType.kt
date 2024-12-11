package co.zibi.mf.constant

import co.zibi.mf.exception.MissionException
import co.zibi.mf.exception.error.ValidateError
import com.fasterxml.jackson.annotation.JsonValue

enum class ScheduleModeType (
    @JsonValue val value: Int
) {

    SINGLE(1),
    MULTIPLE(2),
    PERIOD(3),
    REPEAT(4)
    ;

    fun equalsValue(value: Int): Boolean {
        return this.value == value
    }

    fun isNotRepeat(): Boolean {
        return REPEAT != this
    }

    companion object {
        private val CACHED: Map<Int, ScheduleModeType> = entries.associateBy { it.value }

        fun from(value: Int): ScheduleModeType {
            return CACHED[value] ?: throw MissionException (
                error = ValidateError.NONE_DECLARED_TYPE,
                message = "Not found schedule mode with type by value: $value"
            )
        }

        fun isRepeat(scheduleMode: Int): Boolean {
            return REPEAT.value == scheduleMode
        }

    }
}