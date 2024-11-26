package co.zibi.mf.vo

import co.zibi.mf.domain.mission.Mission
import co.zibi.mf.domain.mission.MissionState
import co.zibi.mf.domain.schedule.Schedule

class MissionDetailVo(
    val id: Long,
    val name: String,
    val description: String,
    val type: Int,
    val deadline: Long,
    val schedule: ScheduleVo?,
    val states: List<MissionStateVo>
) {
    companion object {
        fun of(
            mission: Mission,
            states: List<MissionState>,
            schedule: Schedule?
        ): MissionDetailVo {
            return MissionDetailVo(
                mission.id,
                mission.name,
                mission.description,
                mission.missionType.type,
                mission.deadline,
                schedule?.let { ScheduleVo.of(it) },
                states.map { MissionStateVo.of(it) }
            )
        }
    }
}