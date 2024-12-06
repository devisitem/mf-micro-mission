package co.zibi.mf.manager

import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.repository.ScheduleRepository
import jakarta.transaction.Transactional.TxType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ScheduleManager(
    private val scheduleRepository: ScheduleRepository,
) {
    @Transactional(propagation = Propagation.MANDATORY)
    fun createSchedules(schedules: List<Schedule>): List<Schedule> {
        return scheduleRepository.saveAll(schedules)
    }
}