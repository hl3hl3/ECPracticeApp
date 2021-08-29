package com.hl3hl3.ecpracticeapp.vo

/**
 * {
"status_code": 0,
"result": {
"qr_code": "VGhpc0lzQVRlc3RBUElGb3JJbnRlcnZpZXdIb21ld29yaw=="
}
}
 */
class QRCodeInfoResponse: Response<QRCodeInfo>()

data class QRCodeInfo(
    val qr_code: String
)
