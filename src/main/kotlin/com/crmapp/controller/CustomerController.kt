package com.crmapp.controller

import com.crmapp.entity.Customer
import com.crmapp.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/customer")
open class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/list")
    open fun listCustomers(theModel : Model) : String {
        val theCustomers = customerService.getCustomers()

        theModel.addAttribute("customers", theCustomers)

        return "list-customers"
    }

    @GetMapping("/showFormForAdd")
    open fun showFormForAdd(theModel : Model) : String {
        val theCustomer = Customer()
        theModel.addAttribute("customer", theCustomer)
        return "customer-form"
    }

    @PostMapping("/saveCustomer")
    open fun saveCustomer(@ModelAttribute("customer") theCustomer: Customer) : String {
        customerService.saveCustomer(theCustomer)
        return "redirect:/customer/list"
    }

    @GetMapping("/showFormForUpdate")
    open fun showFormForUpdate(@RequestParam("customerId") theId : Long, theModel: Model) : String{
        var theCustomer = customerService.getCustomer(theId)

        theModel.addAttribute("customer", theCustomer)

        return "customer-form"
    }

    @GetMapping("/delete")
    open fun delete(@RequestParam("customerId") theId : Long) : String {
        customerService.deleteCustomer(theId)

        return "redirect:/customer/list"
    }

    @GetMapping("/search")
    open fun searchCustomers(@RequestParam("theSearchName") theSearchName: String?,
                        theModel: Model): String? {

        // search customers from the service
        val theCustomers = customerService.searchCustomers(theSearchName)

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers)
        return "list-customers"
    }
}