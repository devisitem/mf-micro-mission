package co.zibi.mf.constant

enum class MissionType(
    val type: Int
) {
    SCHEDULE(1),
    MISSION(2)
    ;

    fun isSchedule(): Boolean {
        return this == SCHEDULE
    }

    companion object {
        private val CACHED = entries.associateBy { it.type }

        fun has(type: Int): Boolean {
            return CACHED.containsKey(type)
        }

        fun fromType(type: Int): MissionType? {
            return CACHED[type]
        }

        fun isScheduleType(missionType: Int): Boolean {
            return SCHEDULE.type == missionType
        }
    }
}