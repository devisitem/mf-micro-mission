package co.zibi.mf.domain.mission

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class MissionType (

    @Column(name = "mission_type", nullable = false)
    val type: Int

)