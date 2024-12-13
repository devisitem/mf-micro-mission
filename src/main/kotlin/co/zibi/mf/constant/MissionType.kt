package co.zibi.mf.constant

import com.fasterxml.jackson.annotation.JsonValue

enum class MissionType(
    @JsonValue val value: Int
) {
    SCHEDULE(1),
    MISSION(2)
    ;

    companion object {
        private val CACHED = entries.associateBy { it.value }

        fun has(type: Int): Boolean {
            return CACHED.containsKey(type)
        }

        fun fromType(type: Int): MissionType? {
            return CACHED[type]
        }

        fun isScheduleType(missionType: Int): Boolean {
            return SCHEDULE.value == missionType
        }
    }

    fun isSchedule(): Boolean {
        return SCHEDULE == this
    }
}