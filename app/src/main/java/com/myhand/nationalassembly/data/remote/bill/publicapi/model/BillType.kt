package com.myhand.nationalassembly.data.remote.bill.publicapi.model

enum class BillType(val typeName: String, val typeCode: String) {
    B01("헌법개정", "B01"),
    B02("예산안", "B02"),
    B03("결산", "B03"),
    B04("법률안", "B04"),
    B05("동의안", "B05"),
    B06("승인안", "B06"),
    B07("결의안", "B07"),
    B08("건의안", "B08"),
    B09("규칙안", "B09"),
    B10("선출안", "B10"),
    B11("중요동의", "B11"),
    B12("의원징계", "B12"),
    B13("의원자격심사", "B13"),
    B14("윤리심사", "B14"),
    B15("기타안", "B15"),
    B16("기타", "B16"),
}