package co.zibi.mf.domain.schedule

import co.zibi.mf.constant.RepeatOptionType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class RepeatOption (

    @Comment("반복 옵션[-1: 없음, 0: 매주, 1: 매월, 2: 매년]")
    @Column(name = "repeat_option", nullable = false)
    val option: Int,
) {
    constructor(optionType: RepeatOptionType): this(optionType.value) {
        validate(optionType.value)
    }

    fun isWeekly(): Boolean {
        return RepeatOptionType.WEEKLY.value == this.option
    }

    private final fun validate(optionType: Int) {
        require(RepeatOptionType.has(optionType)) { "반복 옵션 값이 잘못되었습니다." }
    }
}