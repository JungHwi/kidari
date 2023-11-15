package studio.kidari.reservation.global.wrapper

import com.fasterxml.jackson.annotation.JsonIgnore

interface CursorInterface {

    @JsonIgnore
    fun getCursor(): String
}