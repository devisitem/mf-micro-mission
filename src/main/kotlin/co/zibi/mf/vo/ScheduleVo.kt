package co.zibi.mf.vo

import co.zibi.mf.domain.schedule.Schedule

class ScheduleVo(
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
        fun of(schedule: Schedule): ScheduleVo {
            return schedule.let {
                ScheduleVo(
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