package co.zibi.mf.dto

import co.zibi.mf.domain.schedule.Schedule

class ScheduleDto(
    val id: Long,
    val categoryId: Long,
    val mode: Int,
    val scheduleTime: Long,
    val startAt: Long,
    val endAt: Long,
    val repeatOption: Int,
    val repeatValues: List<Int>
) {

    companion object {
        fun of(schedule: Schedule): ScheduleDto {
            return schedule.let {
                ScheduleDto(
                    it.id,
                    it.categoryId,
                    it.scheduleMode.mode,
                    it.scheduleTime,
                    it.startAt,
                    it.endAt,
                    it.repeatOption.option,
                    it.repeatValues()
                )
            }
        }
    }
}