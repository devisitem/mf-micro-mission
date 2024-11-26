package co.zibi.mf.domain.schedule

import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.constant.Weeks
import co.zibi.mf.dto.AuthorizedMember
import co.zibi.mf.dto.MissionEventMessage
import co.zibi.mf.event.CreateMissionEvent
import co.zibi.mf.util.TemporalUtils
import jakarta.persistence.*
import lombok.Getter
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import java.io.Serializable
import java.util.Collections.singletonList

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

    @Comment("스케쥴 타입")
    var scheduleType: ScheduleType,

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

    companion object {
        private fun of(
            event: CreateMissionEvent,
            authorizedMember: AuthorizedMember,
            scheduleModeType: ScheduleModeType,
            scheduleTime: Long,
            startAt: Long,
            endAt: Long,
            repeatOption: RepeatOption,
            repeatValue: RepeatValue
        ): Schedule {
            return Schedule(
                0,
                ScheduleType(event.type),
                event.categoryId,
                authorizedMember.memberId,
                authorizedMember.familyId,
                ScheduleMode(scheduleModeType.value),
                scheduleTime,
                startAt,
                endAt,
                repeatOption,
                repeatValue
            )
        }

        fun forCreate(message: MissionEventMessage<CreateMissionEvent>): List<Schedule> {
            val event = message.event
            val authorizedMember = message.authorizedMember

            return when (val scheduleMode = ScheduleModeType.from(event.scheduleInfo.scheduleMode)) {
                ScheduleModeType.PERIOD ->
                    forPeriod(event, authorizedMember, scheduleMode)
                ScheduleModeType.MULTIPLE, ScheduleModeType.SINGLE ->
                    forMultiple(event, authorizedMember, scheduleMode)
                ScheduleModeType.REPEAT ->
                    forRepeat(event, authorizedMember, scheduleMode)
            }
        }

        private fun forPeriod(
            event: CreateMissionEvent,
            member: AuthorizedMember,
            mode: ScheduleModeType
        ): List<Schedule> {
            val scheduleInfo = event.scheduleInfo
            val schedule = of(event, member, mode,
                scheduleInfo.scheduleTime,
                scheduleInfo.startAt,
                scheduleInfo.endAt,
                RepeatOption.NONE,
                RepeatValue(0)
            )
            return listOf(schedule);
        }

        private fun forMultiple(
            event: CreateMissionEvent,
            member: AuthorizedMember,
            mode: ScheduleModeType
        ): List<Schedule> {
            val scheduleInfo = event.scheduleInfo
            return scheduleInfo.selected.map { timestamp ->
                of(event, member, mode,
                    scheduleInfo.scheduleTime,
                    timestamp,
                    timestamp + TemporalUtils.SECONDS_OF_DAY - 1,
                    RepeatOption.NONE,
                    RepeatValue(0)
                )
            }
        }

        private fun forRepeat(
            event: CreateMissionEvent,
            member: AuthorizedMember,
            mode: ScheduleModeType
        ): List<Schedule> {
            val scheduleInfo = event.scheduleInfo
            val repeatValue = if (RepeatOption.isWeek(scheduleInfo.repeatOption))
                Weeks.toSelected(scheduleInfo.repeatValues)
            else scheduleInfo.repeatValues[0]

            return listOf(of(event, member, mode,
                scheduleInfo.scheduleTime,
                scheduleInfo.startAt,
                scheduleInfo.endAt,
                RepeatOption(scheduleInfo.repeatOption),
                RepeatValue(repeatValue)
            ));
        }
    }
}