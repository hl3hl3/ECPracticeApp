package com.hl3hl3.ecpracticeapp.vo

/**
 * {
"status_code": 0,
"result": {
"messages": [
{
"title": "【PXPay通知】",
"msg": "提醒您這個月的模擬銀行(財金測試)信用卡費該繳費囉！如欲更改提醒日期，請至首頁>點擊其他繳費>綁定通知設定，即可針對各繳費項目設定提醒日~",
"ts": 1614391200000
},
{
"title": "【全聯行動會員消費通知】",
"msg": "【全聯行動會員消費通知】您於 2021/03/11 09:09 有消費交易，總計 3件，消費金額 $114元，詳細請至交易明細查詢，感謝您的購買。\n如需紙本消費明細，請洽收銀人員。",
"ts": 1615425006000
},
{
"title": "【全聯行動會員消費通知】",
"msg": "【全聯行動會員消費通知】您於 2021/03/04 09:07 有消費交易，總計 1件，消費金額 $28元，詳細請至交易明細查詢，感謝您的購買。\n如需紙本消費明細，請洽收銀人員。",
"ts": 1614820037000
},
{
"title": "【全聯行動會員消費通知】",
"msg": "【全聯行動會員消費通知】您於 2021/02/23 09:08 有消費交易，總計 1件，消費金額 $28元，詳細請至交易明細查詢，感謝您的購買。\n如需紙本消費明細，請洽收銀人員。",
"ts": 1614042555000
}
]
}
}
 */
class MessageResponse: Response<Messages>()

data class Messages(
    val messages: List<Message>
)

data class Message(
    val title: String,
    val msg: String,
    val ts: String,
)