package com.hl3hl3.ecpracticeapp.ui.qrcodeinfo

import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

object GZxingEncoder {
    var characterEncoding = "ISO-8859-1"

    @Throws(WriterException::class)
    fun generateQRCode(data: String?, width: Int = 250, height: Int = 250): Bitmap? {
        val bm = QRCodeWriter().encode(
            Uri.encode(data, characterEncoding),
            BarcodeFormat.QR_CODE,
            width,
            height
        )
        val imageBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (i in 0 until width) {
            for (j in 0 until height) {
                imageBitmap.setPixel(i, j, if (bm[i, j]) Color.BLACK else Color.WHITE)
            }
        }
        return imageBitmap
    }

}
