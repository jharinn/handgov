package com.myhand.nationalassembly.data.remote.base

enum class ResultCodePublicData(val resultCode: String, val errorMsg: String) {
    NORMAL("00", "NORMAL SERVICE"),
    APPLICATION_PublicData_Result("1", "어플리케이션 에러"),
    HTTP_Result("4", "HTTP 에러"),
    NO_OPENAPI_SERVICE_Result("12", "해당 오픈 API 서비스가 없거나 폐기됨"),
    SERVICE_ACCESS_DENIED_Result("20", "서비스 접근거부"),
    LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_Result("22", "서비스 요청제한횟수 초과에러"),
    SERVICE_KEY_IS_NOT_REGISTERED_Result("30", "등록되지 않은 서비스키"),
    DEADLINE_HAS_EXPIRED_Result("31", "활용기간 만료"),
    UNREGISTERED_IP_Result("32", "등록되지 않은 IP"),
    UNKNOWN_Result("99", "기타에러"),
}