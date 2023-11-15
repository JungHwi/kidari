package studio.kidari.reservation.global.exception

class BadRequestException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
) : KidariException(error, description, throwable) {

    constructor(error: ErrorCode) : this(error, error.getDescription())

    constructor(description: String) : this(ErrorCode.BAD_REQUEST, description)
    constructor() : this(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getDescription())
}