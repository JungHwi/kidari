package studio.kidari.reservation.global.exception

open class KidariException(
    val error: ErrorCode,
    val description: String,
    throwable: Throwable?
) : RuntimeException(error.name, throwable) {

    constructor() : this(ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getDescription(), null)
    constructor(error: ErrorCode) : this(error, error.getDescription(), null)

    constructor(error: ErrorCode, description: String) : this(error, description, null)
}