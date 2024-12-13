package co.zibi.mf.domain.schedule

import co.zibi.mf.constant.CategoryType
import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.constant.Weeks
import co.zibi.mf.event.CreateMissionEvent
import co.zibi.mf.util.TemporalUtils
import co.zibi.mf.vo.AuthorizedMember
import jakarta.persistence.*
import lombok.Getter
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import java.io.Serializable

@Getter
@Comment("스케쥴 정보")
@Entity
@Table(
    name = "mf_schedule",
    indexes = [
        Index(name = "idx_family", columnList = "family"),
        Index(name = "idx_reporter", columnList = "reporter"),
        Index(name = "idx_between_period", columnList = "start_at, end_at")
    ]
)
class Schedule (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Comment("스케쥴 카테고리")
    var scheduleCategory: ScheduleCategory,

    @Comment("카테고리 ID")
    @ColumnDefault("0")
    @Column(name = "category_id", nullable = false)
    var categoryId: Long,

    @Comment("생성 멤버")
    @Column(name = "reporter", nullable = false)
    val reporter: Long,

    @Comment("패밀리 ID")
    @Column(name = "family")
    val family: Long,

    val scheduleMode: ScheduleMode,

    @Comment("스케쥴 시간")
    @Column(name = "schedule_time")
    val scheduleTime: Long,

    @Comment("시작일")
    @Column(name = "start_at")
    val startAt: Long,

    @Comment("종료일")
    @Column(name = "end_at")
    val endAt: Long,

    val repeatOption: RepeatOption,

    val repeatValue: RepeatValue

): Serializable {
    fun repeatValues(): List<Int> {
        if (repeatOption.isWeekly()) {
            return repeatValue.toWeeks()
        }

        return listOf(this.repeatValue.value)
    }

    companion object {
        private fun of(
            event: CreateMissionEvent,
            authorizedMember: AuthorizedMember,
            category: CategoryType,
            mode: ScheduleModeType,
            scheduleTime: Long,
            startAt: Long,
            endAt: Long,
            repeatOption: RepeatOption,
            repeatValue: RepeatValue
        ): Schedule {
            return Schedule(
                0,
                ScheduleCategory(category.value),
                event.categoryId,
                authorizedMember.memberId,
                authorizedMember.familyId,
                ScheduleMode(mode.value),
                scheduleTime,
                startAt,
                endAt,
                repeatOption,
                repeatValue
            )
        }

        fun forPeriod(
            event: CreateMissionEvent,
            member: AuthorizedMember,
            mode: ScheduleModeType,
            category: CategoryType
        ): List<Schedule> {
            val scheduleInfo = event.scheduleInfo
            val schedule = of(event, member, category, mode,
                scheduleInfo.scheduleTime,
                scheduleInfo.startAt,
                scheduleInfo.endAt,
                RepeatOption.NONE,
                RepeatValue(0)
            )
            return listOf(schedule);
        }

        fun forMultiple(
            event: CreateMissionEvent,
            member: AuthorizedMember,
            mode: ScheduleModeType,
            category: CategoryType
        ): List<Schedule> {
            val scheduleInfo = event.scheduleInfo
            return scheduleInfo.selected.map { timestamp ->
                of(event, member, category, mode,
                    scheduleInfo.scheduleTime,
                    timestamp,
                    timestamp + TemporalUtils.SECONDS_OF_DAY - 1,
                    RepeatOption.NONE,
                    RepeatValue(0)
                )
            }
        }

        fun forRepeat(
            event: CreateMissionEvent,
            member: AuthorizedMember,
            mode: ScheduleModeType,
            category: CategoryType
        ): List<Schedule> {
            val scheduleInfo = event.scheduleInfo
            val repeatValue = if (scheduleInfo.repeatOption.isWeekly())
                Weeks.toSelected(scheduleInfo.repeatValues)
            else scheduleInfo.repeatValues[0]

            return listOf(of(event, member, category, mode,
                scheduleInfo.scheduleTime,
                scheduleInfo.startAt,
                scheduleInfo.endAt,
                RepeatOption(scheduleInfo.repeatOption),
                RepeatValue(repeatValue)
            ));
        }
    }
}