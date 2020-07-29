package com.crmapp.service

import com.crmapp.dao.CustomerDAO
import com.crmapp.entity.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
open class CustomerServiceImpl : CustomerService {
    @Autowired
    lateinit var customerDAO: CustomerDAO

    @Transactional
    override fun getCustomers(): List<Customer>? {
        return customerDAO.getCustomers()
    }

    @Transactional
    override fun saveCustomer(theCustomer: Customer) {
        customerDAO.saveCustomer(theCustomer)
    }

    @Transactional
    override fun getCustomer(theId: Long): Customer? {
        return customerDAO.getCustomer(theId)
    }

    @Transactional
    override fun deleteCustomer(theId: Long) {
        return customerDAO.deleteCustomer(theId)
    }

    @Transactional
    override fun searchCustomers(theSearchName: String?): List<Customer?>? {
        return customerDAO.searchCustomers(theSearchName)
    }
}