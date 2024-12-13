package co.zibi.mf.service


import co.zibi.mf.constant.CategoryType
import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.domain.mission.Mission
import co.zibi.mf.domain.mission.MissionDetail
import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.event.CreateMissionEvent
import co.zibi.mf.manager.MissionManager
import co.zibi.mf.dto.MissionDetailDto
import co.zibi.mf.vo.mission.MissionEventMessage
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
class MissionService(

    private val missionManager: MissionManager,
    private val scheduleService: ScheduleService,
    private val stateService: MissionStateService
) {

    @Transactional
    fun createMission(message: MissionEventMessage<CreateMissionEvent>): List<MissionDetailDto> {
        val (member, event, timestamp) = message.validate()
        val mode = event.scheduleMode

        val schedules = scheduleService.createNewSchedules(mode, CategoryType.MISSION, event, member)
        val missions = createNewMissions(schedules, event, timestamp)
        val schedulesById = schedules.associateBy { it.id }

        stateService.createRepeatState(missions, schedulesById, mode)
        missionManager.createDetailMissions(missions.map(MissionDetail::forCreate))

        return missions
            .map { MissionDetailDto.of(it, emptyList(), schedulesById[it.scheduleId]) }
            .sortedBy { it.id }
    }

    private fun createNewMissions(
        schedules: List<Schedule>,
        event: CreateMissionEvent,
        timestamp: Long
    ): List<Mission> {
        val missions = schedules.map { Mission.forCreate(event, it, timestamp) }

        return missionManager.crateMissions(missions)
    }

}