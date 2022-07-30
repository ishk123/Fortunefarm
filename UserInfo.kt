package com.example.farmbee

class UserInfo(var CATEGORY:String) {

    var USERID = ""
    var F_NAME = ""
    var L_NAME = ""
    var PHONE_NO = ""
    var PASSWORD = ""

    constructor(CATEGORY:String,USERID:String,F_NAME:String,L_NAME:String,PHONE_NO:String,PASSWORD:String):this(CATEGORY){

       this.USERID = USERID
        this.F_NAME = F_NAME
        this.L_NAME = L_NAME
        this.PHONE_NO = PHONE_NO
        this.PASSWORD = PASSWORD
    }
}