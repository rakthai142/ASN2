package bec.c231371.u8_apis._services.api

data class WebServiceResult<T>(
    var result: String = "",
    var payload: T? = null
)