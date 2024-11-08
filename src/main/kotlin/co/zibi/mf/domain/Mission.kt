package co.zibi.mf.domain

import jakarta.persistence.*
import lombok.Getter
import org.hibernate.annotations.Comment
import org.hibernate.annotations.DynamicUpdate
import java.io.Serial
import java.io.Serializable
import kotlin.jvm.Transient

@Getter
@DynamicUpdate
@Comment("미션 메인정보")
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
    val isPublic: Boolean = false,

    @Comment("미션명")
    @Column(name = "mission_name", nullable = false)
    val name: String,

    @Comment("미션 설명")
    @Column(name = "description", length = 500)
    val description: String? = null,

    @Comment("스케쥴 ID")
    @Column(name = "schedule_id", nullable = false)
    val scheduleId: Long,

    @Comment("장소 ID")
    @Column(name = "place_id")
    val placeId: Long? = null,

    @Comment("미션 타입 1: 일반미션, 2: 미션팩, 3: 스텝미션")
    @Column(name = "mission_type", nullable = false)
    val missionType: Int,

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

    @Transient
    var watcherList: List<Long> = emptyList()

    @PostLoad
    fun init() {
        watcherList = if (watchers.isNotEmpty()) {
            watchers.split(",").mapNotNull { it.toLongOrNull() }
        } else {
            emptyList()
        }
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = 1
    }
}