package co.zibi.mf.vo

import co.zibi.mf.constant.RepeatOptionType
import co.zibi.mf.constant.RepeatOptionType.*
import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.constant.ScheduleModeType.*
import co.zibi.mf.constant.Weeks
import co.zibi.mf.data.Validatable

data class ScheduleInfo (
    val scheduleMode: ScheduleModeType,
    val selected: List<Long>,
    val startAt: Long,
    val scheduleTime: Long,
    val endAt: Long,
    val repeatOption: RepeatOptionType,
    val repeatValues: List<Int>
): Validatable {

    override fun validate() {
        val isValid = when (scheduleMode) {
            PERIOD -> isValidStampRange()
            SINGLE -> selected.size == 1
            MULTIPLE -> selected.size > 1
            REPEAT -> {
                val isValidOption = when (repeatOption) {
                    WEEKLY -> Weeks.toSelected(repeatValues) > 0
                    MONTHLY, YEARLY -> repeatValues.size == 1
                    else -> false
                }

                isValidOption && isValidStampRange()
            }
        }

        require(isValid) { "올바르지 않은 값 입니다." }
    }

    private fun isValidStampRange(): Boolean {
        return startAt < endAt && endAt != 0L
    }
}