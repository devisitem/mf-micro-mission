package co.zibi.mf.event

import co.zibi.mf.vo.ScheduleInfo

class CreateMissionEvent (

    val name: String,

    val categoryId: Long,

    val subName: String,

    val type: Int,

    val deadline: Long?,

    val scheduleInfo: ScheduleInfo

): MissionEvent {
}