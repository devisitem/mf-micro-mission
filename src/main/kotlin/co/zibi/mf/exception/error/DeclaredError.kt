package co.zibi.mf.exception.error

import co.zibi.mf.constant.ServiceType

interface DeclaredError {

    val service: ServiceType
    val behavior: String
    val code: Int
    val message: String
}