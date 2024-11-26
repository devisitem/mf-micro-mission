package co.zibi.mf.constant

enum class MissionStatusType(
    val code: Int
) {

    CREATE(0),
    IN_PROGRESS(1),
    COMPLETED(2),
    DELETED(3),
    ALWAYS(4)
    ;

    companion object {
        private val CACHED = entries.associateBy { it.code }

        fun fromCode(code: Int): MissionStatusType? {
            return CACHED[code]
        }

        fun fromMissionType(missionType: Int): MissionStatusType {
            return if (MissionType.isScheduleType(missionType)) ALWAYS
            else CREATE
        }
    }
}