package co.zibi.mf.domain.mission

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class MissionStatus(

    @Comment("미션 상태[0: 생성, 1: 진행중, 2: 완료, 3: 삭제, 4: 항상진행(일정)]")
    @Column(name = "mission_status", nullable = false)
    val status: Int
) {
}