package com.myhand.nationalassembly.util

import com.myhand.nationalassembly.BuildConfig

object Const {
    val NA_Secretariat_API_BASE_URL = "http://apis.data.go.kr/9710000/"
    val OPEN_API_BASE_URL = "https://open.assembly.go.kr/portal/openapi/"
    val MEMBER_PHOTO_API_KEY = BuildConfig.MEMBER_PHOTO_API_KEY
    val MEMBER_INFO_API_KEY = BuildConfig.MEMBER_INFO_API_KEY
    val BILL_API_KEY = BuildConfig.BILL_API_KEY
    const val SEARCH_BOOKS_TIME_DELAY = 100L
    const val PAGING_SIZE = 10
    const val DATASTORE_NAME = "MyHandNA"
    const val DATABASE_MEMBER_PHOTO = "member_photo"
}