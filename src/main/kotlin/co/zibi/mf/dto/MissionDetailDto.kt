package co.zibi.mf.dto

import co.zibi.mf.domain.mission.Mission
import co.zibi.mf.domain.mission.MissionState
import co.zibi.mf.domain.schedule.Schedule

class MissionDetailDto(
    val id: Long,
    val name: String,
    val description: String,
    val type: Int,
    val deadline: Long,
    val schedule: ScheduleDto?,
    val states: List<MissionStateDto>
) {
    companion object {
        fun of(
            mission: Mission,
            states: List<MissionState>,
            schedule: Schedule?
        ): MissionDetailDto {
            return MissionDetailDto(
                mission.id,
                mission.name,
                mission.description,
                mission.missionType.type,
                mission.deadline,
                schedule?.let { ScheduleDto.of(it) },
                states.map { MissionStateDto.of(it) }
            )
        }
    }
}