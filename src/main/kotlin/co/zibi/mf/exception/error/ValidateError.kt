package co.zibi.mf.exception.error

import co.zibi.mf.constant.ServiceType

enum class ValidateError (
    override val code: Int
): DeclaredError {
    NONE_DECLARED_TYPE(1)
    ;

    override val service: ServiceType
        get() = ServiceType.COMMON
    override val behavior: String = "validate"
    override val message: String
        get() = this.name
}