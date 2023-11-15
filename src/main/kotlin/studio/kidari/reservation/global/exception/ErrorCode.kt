package studio.kidari.reservation.global.exception

import studio.kidari.reservation.global.code.CodeValue

enum class ErrorCode(private val description: String) : CodeValue {

    // COMMON
    INTERNAL_SERVER_ERROR("서버 에러"),
    BAD_REQUEST("잘못된 요청"),
    NOT_FOUND("찾을 수 없는 요청");

    override fun getDescription(): String {
        return description
    }

    override fun getName(): String {
        return name
    }
}