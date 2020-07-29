package com.crmapp.controller

import com.crmapp.entity.Customer
import com.crmapp.exception.CustomerNotFoundException
import com.crmapp.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
open class CustomerRestController {

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/customers")
    open fun getCustomers() : List<Customer>? {
        return customerService.getCustomers()
    }

    @GetMapping("/customers/{customerId}")
    open fun getCustomer(@PathVariable customerId : Long) : Customer {
        val customer : Customer? = customerService.getCustomer(customerId)
        if(customer == null)
            throw CustomerNotFoundException("Customer id not found: $customerId")
        return customer
    }

    @PostMapping("/customers")
    open fun addCustomer(@RequestBody customer : Customer) : Customer {
        customerService.saveCustomer(customer)
        return customer
    }

    @PutMapping("/customers")
    open fun updateCustomer(@RequestBody customer : Customer) : Customer {
        customerService.saveCustomer(customer)
        return customer
    }

    @DeleteMapping("/customers/{customerId}")
    open fun deleteCustomer(@PathVariable customerId: Long) : String {
        val customer : Customer? = customerService.getCustomer(customerId)
        if(customer == null)
            throw CustomerNotFoundException("Customer id not found: $customerId")

        customerService.deleteCustomer(customerId)
        return "deleted customer: $customerId"
    }
}