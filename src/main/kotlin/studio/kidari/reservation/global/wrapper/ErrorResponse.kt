package studio.kidari.reservation.global.wrapper

import studio.kidari.reservation.global.exception.ErrorCode

data class ErrorResponse(val error: ErrorCode, val description: String) {

}