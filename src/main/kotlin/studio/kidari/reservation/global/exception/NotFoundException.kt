package studio.kidari.reservation.global.exception

class NotFoundException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
) : KidariException(error, description, throwable) {

    constructor(error: ErrorCode) : this(error, error.getDescription())

    constructor(description: String) : this(ErrorCode.NOT_FOUND, description)
    constructor() : this(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.getDescription())
}