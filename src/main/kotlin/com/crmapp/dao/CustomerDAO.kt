package com.crmapp.dao

import com.crmapp.entity.Customer

interface CustomerDAO {
    fun getCustomers() : List<Customer>?
    fun saveCustomer(theCustomer: Customer)
    fun getCustomer(theId : Long) : Customer?
    fun deleteCustomer(theId: Long)
    fun searchCustomers(theSearchName: String?): List<Customer?>?
}