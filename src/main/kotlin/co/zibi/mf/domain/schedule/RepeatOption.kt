package co.zibi.mf.domain.schedule

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class RepeatOption (

    @Comment("반복 옵션[-1: 없음, 0: 매주, 1: 매월, 2: 매년]")
    @Column(name = "repeat_option", nullable = false)
    val option: Int,
) {
    fun isWeekly(): Boolean {
        return WEEKLY.option == this.option
    }

    companion object {
        val NONE = RepeatOption(-1)
        val WEEKLY = RepeatOption(0)
        val MONTHLY = RepeatOption(1)
        val YEARLY = RepeatOption(2)

        fun isWeek(option: Int): Boolean {
            return WEEKLY.option == option
        }
    }
}