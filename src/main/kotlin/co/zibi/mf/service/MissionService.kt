package co.zibi.mf.service

import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.dto.MissionEventMessage
import co.zibi.mf.event.CreateMissionEvent
import co.zibi.mf.manager.MissionManager
import co.zibi.mf.manager.ScheduleManager
import jakarta.transaction.Transactional
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class MissionService(
    missionManager: MissionManager,
    private val scheduleManager: ScheduleManager,
) {

    @Transactional
    fun crateMission(message: MissionEventMessage<CreateMissionEvent>) {
        val schedules = createNewSchedules(message)

        return
    }

    private fun createNewSchedules(message: MissionEventMessage<CreateMissionEvent>): List<Schedule> {
        val schedules = Schedule.forCreate(message)
        return scheduleManager.createSchedules(schedules)
    }

    private fun createNewMission(schedules: List<Schedule>) {
        schedules.map {
        }
    }

}