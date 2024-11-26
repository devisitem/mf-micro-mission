package co.zibi.mf.domain.schedule

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class ScheduleType (
    @Column(name = "schedule_type", nullable = false)
    val type: Int
) {
    companion object {
    }

}