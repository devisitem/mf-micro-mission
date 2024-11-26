package co.zibi.mf.domain.mission

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class EmbeddableMissionType(

    @Comment("미션 타입 1: 일반미션, 2: 미션팩")
    @Column(name = "mission_type", nullable = false)
    val type: Int

)