package co.zibi.mf.domain.schedule

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class ScheduleCategory (
    @Column(name = "category", nullable = false)
    val type: Int
) {
    companion object {
    }

}