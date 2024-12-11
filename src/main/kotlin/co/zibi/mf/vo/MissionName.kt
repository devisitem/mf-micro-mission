package co.zibi.mf.vo

import co.zibi.mf.extension.removeSpaces
import com.fasterxml.jackson.annotation.JsonCreator

@JvmInline
value class MissionName private constructor(val value: String) {
    companion object {
        private val MISSION_NAME_REGEX = Regex("^[a-zA-Z0-9_]{1,20}\$")

        @JsonCreator
        fun from(missionName: String): MissionName {
            return MissionName(missionName.removeSpaces())
        }
    }

    init {
        validateMissionName(value)
    }

    private fun validateMissionName(name: String) {
        require(MISSION_NAME_REGEX.matches(name)) { "사용할 수 없는 미션 제목 입니다." }
    }
}