package com.crmapp.service

import com.crmapp.entity.Customer


interface CustomerService {
    fun getCustomers() : List<Customer>?
    fun saveCustomer(theCustomer: Customer)
    fun getCustomer(theId : Long) : Customer?
    fun deleteCustomer(theId: Long)
    fun searchCustomers(theSearchName: String?): List<Customer?>?
}