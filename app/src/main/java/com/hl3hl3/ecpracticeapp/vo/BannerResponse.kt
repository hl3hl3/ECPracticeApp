package com.hl3hl3.ecpracticeapp.vo

/**
 *
 * {
"status_code": 0,
"result": {
"banners": [
{
"title": "【最新活動】在家吃早餐",
"image": "https://i.imgur.com/l03k6Vw.png",
"target_url": "https://www.google.com/"
},
{
"title": "【最新活動】早餐吃堡堡",
"image": "https://i.imgur.com/lCdkQZ1.jpg",
"target_url": "https://www.google.com/"
},
{
"title": "【最新活動】蛋白質專區",
"image": "https://i.imgur.com/TrcyNhL.png",
"target_url": "https://www.google.com/"
},
{
"title": "【最新活動】OFF COFFEE",
"image": "https://i.imgur.com/LrST1ZF.png",
"target_url": "https://www.google.com/"
},
{
"title": "【最新活動】IRIS抗菌防黴預購",
"image": "https://i.imgur.com/dDFt9ho.png",
"target_url": "https://www.google.com/"
}
]
}
}
 */
class BannerResponse: Response<Banners>()

data class Banners(
    val banners: List<Banner>
)

data class Banner(
    val title: String,
    val image: String,
    val target_url: String,
)