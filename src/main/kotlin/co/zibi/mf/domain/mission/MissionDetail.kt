package co.zibi.mf.domain.mission

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Comment("미션 상세정보")
@Table(name = "mf_mission_detail")
class MissionDetail (

    @Id
    @Column(name = "mission_id")
    val missionId: Long
) {

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "mission_id", insertable = false, updatable = false)
    lateinit var mission: Mission

    companion object {
        fun forCreate(mission: Mission): MissionDetail {
            return MissionDetail(mission.id)
        }
    }
}