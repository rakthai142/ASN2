package bec.c231371.u8_apis._services.api

data class WebServiceResultList<T>(
    var result: String = "",
    var count: Int = 0,
    var payload: List<T> = emptyList()
)