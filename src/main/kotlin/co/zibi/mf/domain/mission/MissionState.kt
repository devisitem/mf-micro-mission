package co.zibi.mf.domain.mission

import co.zibi.mf.constant.MissionStatusType
import co.zibi.mf.domain.schedule.Schedule
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Comment("미션 상태정보")
@Table(name = "mf_mission_state")
class MissionState(

    @Id
    @GeneratedValue
    val id: Long,

    @Comment("미션 ID")
    @Column(name = "mission_id", nullable = false)
    val missionId: Long,

    val missionStatus: MissionStatus,

    @Comment("미션 시작시간 (timestamp)")
    @Column(name = "start_stamp")
    val startStamp: Long,

    @Comment("미션 종료시간 (timestamp)")
    @Column(name = "end_stamp")
    var endStamp: Long,

    @Comment("실제 시작시간")
    @Column(name = "concrete_start_time", nullable = false)
    val startTime: Long,

    @Comment("실제 완료시간")
    @Column(name = "concrete_complete_time", nullable = false)
    val completeTime: Long

) {

    companion object {

        fun forCreateMission(mission: Mission, schedule: Schedule): MissionState {
            val status = MissionStatusType.fromMissionType(mission.missionType.type)
            return MissionState(
                0L,
                mission.id,
                MissionStatus(status.code),
                schedule.startAt,
                schedule.endAt,
                0L,
                0L
            )
        }
    }
}