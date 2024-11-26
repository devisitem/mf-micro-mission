package co.zibi.mf.service


import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.domain.mission.Mission
import co.zibi.mf.domain.mission.MissionDetail
import co.zibi.mf.domain.mission.MissionState
import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.event.CreateMissionEvent
import co.zibi.mf.manager.MissionManager
import co.zibi.mf.manager.MissionStateManager
import co.zibi.mf.manager.ScheduleManager
import co.zibi.mf.vo.MissionDetailVo
import co.zibi.mf.vo.MissionEventMessage
import co.zibi.mf.vo.ScheduleInfo
import jakarta.transaction.Transactional
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class MissionService(
    private val missionManager: MissionManager,
    private val scheduleManager: ScheduleManager,
    private val missionStateManager: MissionStateManager
) {

    @Transactional
    fun crateMission(message: MissionEventMessage<CreateMissionEvent>): List<MissionDetailVo> {
        val schedules = createNewSchedules(message)
        val missions = createNewMission(schedules, message)
        val schedulesById = schedules.associateBy { it.id }

        createRepeatState(missions, schedulesById, message.event.scheduleInfo)
        missionManager.createDetailMissions(missions.map(MissionDetail::forCreate))

        return missions
            .map { MissionDetailVo.of(it, listOf(), schedulesById[it.scheduleId]) }
            .sortedBy { it.id }
    }

    private fun createRepeatState(
        missions: List<Mission>,
        schedulesById: Map<Long, Schedule>,
        scheduleInfo: ScheduleInfo
    ) {

        if (ScheduleModeType.isRepeat(scheduleInfo.scheduleMode)) {
            val states = missions.mapNotNull {
                schedulesById[it.scheduleId]
                    ?.let { schedule -> MissionState.forCreateMission(it, schedule) }
            }
            missionStateManager.createStates(states)
        }
    }

    private fun createNewSchedules(message: MissionEventMessage<CreateMissionEvent>): List<Schedule> {
        val schedules = Schedule.forCreate(message)
        return scheduleManager.createSchedules(schedules)
    }

    private fun createNewMission(
        schedules: List<Schedule>,
        message: MissionEventMessage<CreateMissionEvent>
    ): List<Mission> {
        val event = message.event
        val missions = schedules.map { Mission.forCreate(event, it, message.timestamp) }

        return missionManager.crateMissions(missions)
    }

}