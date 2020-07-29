package com.crmapp.exception

class CustomerErrorResponse(var status : Int? = null,
                            var message : String? = null,
                            var timeStamp : Long? = null) {
}