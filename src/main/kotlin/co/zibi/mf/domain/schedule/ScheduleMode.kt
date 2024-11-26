package co.zibi.mf.domain.schedule

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class ScheduleMode (

    @Comment("스케쥴 모드[1: 단일, 2: 다중, 3: 기간, 4: 반복")
    @Column(name = "mode", nullable = false)
    val mode: Int
) {

}