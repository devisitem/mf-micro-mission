package co.zibi.mf.dto

data class ScheduleInfo (

    val scheduleMode: Int,

    val selected: Array<Long>,

    val startAt: Long,

    val scheduleTime: Long,

    val endAt: Long,

    val repeatOption: Int,
    val repeatValues: Array<Int>
) {

    fun getFirstRepeatValue(): Int {
        return repeatValues[0]
    }

}