package co.zibi.mf.dto

data class ScheduleInfo (

    val scheduleMode: Int,

    val selected: List<Long>,

    val startAt: Long,

    val scheduleTime: Long,

    val endAt: Long,

    val repeatOption: Int,
    val repeatValues: List<Int>
) {

}