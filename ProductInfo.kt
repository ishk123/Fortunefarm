package com.example.farmbee

import android.icu.text.CaseMap
import android.webkit.RenderProcessGoneDetail

data class ProductInfo(var title:String ) {
    var email=""
    var detail = ""
    var quantity = 0
    var price = 0
    var spin = ""

    constructor(title:String,email:String,detail:String,quantity:Int,price:Int,spin:String):this(title){
        this.email = email
        this.detail = detail
        this.quantity = quantity
        this.price = price
       this.spin = spin
    }
}