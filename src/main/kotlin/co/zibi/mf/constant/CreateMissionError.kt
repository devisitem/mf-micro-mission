package co.zibi.mf.constant

import co.zibi.mf.exception.error.DeclaredError

enum class CreateMissionError (
    override val code: Int
): DeclaredError {
    NOT_FOUND_SCHEDULE_MODE(1000),
    ;

    override val service: ServiceType
        get() = ServiceType.MISSION
    override val behavior: String = "create"
    override val message: String
        get() = this.name
}