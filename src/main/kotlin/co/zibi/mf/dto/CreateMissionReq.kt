package co.zibi.mf.dto

data class CreateMissionReq (
    val name: String,

    val categoryId: Long,

    val subName: String,

    val type: Int,

    val deadline: Long?,

    val scheduleInfo: ScheduleInfo
) {
}