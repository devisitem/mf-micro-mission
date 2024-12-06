package co.zibi.mf.event

import co.zibi.mf.constant.MissionType
import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.vo.MessageEvent
import co.zibi.mf.vo.MissionDescription
import co.zibi.mf.vo.MissionName
import co.zibi.mf.vo.ScheduleInfo

data class CreateMissionEvent (

    val name: MissionName,

    val categoryId: Long,

    val subName: MissionDescription,

    val type: MissionType,

    val deadline: Long?,

    val scheduleInfo: ScheduleInfo

): MissionEvent {

    val scheduleMode: ScheduleModeType
        get() = scheduleInfo.scheduleMode

    override fun validate() {

    }
}