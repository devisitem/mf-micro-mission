package co.zibi.mf.repository

import co.zibi.mf.domain.schedule.Schedule
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository: JpaRepository<Schedule, Long> {
}