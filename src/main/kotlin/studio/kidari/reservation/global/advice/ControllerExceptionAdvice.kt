package studio.kidari.reservation.global.advice

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import studio.kidari.reservation.global.exception.BadRequestException
import studio.kidari.reservation.global.exception.ErrorCode
import studio.kidari.reservation.global.exception.NotFoundException
import studio.kidari.reservation.global.wrapper.ErrorResponse

@RestControllerAdvice
class ControllerExceptionAdvice {

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNotFoundException(exception: BadRequestException): ErrorResponse {
        return ErrorResponse(exception.error, exception.description)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(exception: NotFoundException): ErrorResponse {
        return ErrorResponse(exception.error, exception.description)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        return ErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getDescription())
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleDataIntegrityViolationException(exception: DataIntegrityViolationException): ErrorResponse {
        return ErrorResponse(ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getDescription())
    }
}