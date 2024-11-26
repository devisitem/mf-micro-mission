package co.zibi.mf.manager

import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.repository.ScheduleRepository
import org.springframework.stereotype.Service

@Service
class ScheduleManager(
    private val scheduleRepository: ScheduleRepository,
) {
    fun createSchedules(schedules: List<Schedule>): List<Schedule> {
        return scheduleRepository.saveAll(schedules)
    }
}