package co.zibi.mf.service

import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.constant.ScheduleModeType.*
import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.event.CreateMissionEvent
import co.zibi.mf.manager.ScheduleManager
import co.zibi.mf.vo.AuthorizedMember
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ScheduleService (
    private val manager: ScheduleManager
) {

    @Transactional(propagation = Propagation.MANDATORY)
    fun createNewSchedules (mode: ScheduleModeType, event: CreateMissionEvent, member: AuthorizedMember): List<Schedule> {
        val schedules = when (mode) {
            PERIOD -> Schedule.forPeriod(event, member, mode)
            MULTIPLE, SINGLE -> Schedule.forMultiple(event, member, mode)
            REPEAT -> Schedule.forRepeat(event, member, mode)
        }

        return manager.createSchedules(schedules)
    }
}