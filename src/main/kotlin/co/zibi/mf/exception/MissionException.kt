package co.zibi.mf.exception

import co.zibi.mf.exception.error.DeclaredError

class MissionException: RuntimeException {

    val error: DeclaredError

    constructor(error: DeclaredError): super(error.message) {
        this.error = error
    }

    constructor(error: DeclaredError, message: String): super(message) {
        this.error = error
    }
}