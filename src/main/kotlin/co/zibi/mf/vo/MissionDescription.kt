package co.zibi.mf.vo

import com.fasterxml.jackson.annotation.JsonCreator

@JvmInline
value class MissionDescription private constructor(val value: String) {
    companion object {
        private val MISSION_DESCRIPTION_REGEX = Regex("^[a-zA-Z0-9_]{1,20}\$")

        @JsonCreator
        fun from(description: String): MissionDescription {
            return MissionDescription(description)
        }
    }

    init {
        validateMissionDescription(value)
    }

    private fun validateMissionDescription(description: String) {
        require(MISSION_DESCRIPTION_REGEX.matches(description)) { "사용할 수 없는 미션 설명 입니다." }
    }
}