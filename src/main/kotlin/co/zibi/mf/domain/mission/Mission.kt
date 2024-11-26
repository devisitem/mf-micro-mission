package co.zibi.mf.domain.mission

import co.zibi.mf.constant.MissionType
import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.event.CreateMissionEvent
import jakarta.persistence.*
import lombok.Getter
import org.hibernate.annotations.Comment
import java.io.Serializable

@Getter
@Comment("미션 메인정보")
@Entity
@Table(
    name = "mf_mission",
    indexes = [
        Index(name = "idx_schedule_id", columnList = "schedule_id"),
        Index(name = "idx_place_id", columnList = "place_id")
    ]
)
class Mission (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Comment("공개 여부")
    @Column(name = "is_public", nullable = false)
    val isPublic: Boolean,

    @Comment("미션명")
    @Column(name = "mission_name", nullable = false)
    val name: String,

    @Comment("미션 설명")
    @Column(name = "description", length = 500)
    val description: String,

    @Comment("스케쥴 ID")
    @Column(name = "schedule_id", nullable = false)
    val scheduleId: Long,

    @Comment("장소 ID")
    @Column(name = "place_id")
    val placeId: Long? = null,

    val missionType: EmbeddableMissionType,

    @Comment("관람자, 스케쥴일 경우 참가자")
    @Column(name = "watcher", nullable = false)
    val watchers: String,

    @Comment("제한시간(초): 시작 시 적용")
    @Column(name = "deadline", nullable = false)
    val deadline: Long,

    @Comment("수정일시")
    @Column(name = "updated_at", nullable = false)
    val updatedAt: Long,

    @Comment("생성일시")
    @Column(name = "created_at", nullable = false)
    val createdAt: Long
) : Serializable {

    companion object {
        fun forCreate(event: CreateMissionEvent, schedule: Schedule, timestamp: Long): Mission {
            val deadline = if (MissionType.isScheduleType(event.type))
                event.deadline ?: 0 else 0L
            return Mission(
                0,
                true,
                event.name,
                event.subName,
                schedule.id,
                0L,
                EmbeddableMissionType(event.type),
                "$schedule.reporter",
                deadline,
                timestamp,
                timestamp
            )
        }
    }
}