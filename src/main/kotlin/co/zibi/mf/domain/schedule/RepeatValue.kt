package co.zibi.mf.domain.schedule

import co.zibi.mf.constant.Weeks
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class RepeatValue (

    @Comment("반복 값[repeat_option 0: 각 요일별 Bit 위치 합, 2 또는 3: 기준일]")
    @Column(name = "repeat_value", nullable = false)
    val value: Int
) {
    fun toWeeks(): List<Int> {
        return Weeks.toIndexes(this.value)
    }


}