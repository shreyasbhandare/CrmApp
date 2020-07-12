package com.crmapp.dao

import com.crmapp.entity.Customer
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.Query


@Repository
class CustomerDAOImpl : CustomerDAO {

    @Autowired
    lateinit var sessionFactory : SessionFactory

    override fun getCustomers(): List<Customer>? {
        val session = sessionFactory.currentSession

        val query = session.createQuery("from Customer order by lastName", Customer::class.java)

        val listOfCustomers = query.resultList

        return listOfCustomers
    }

    override fun saveCustomer(theCustomer: Customer) {
        val session = sessionFactory.currentSession

        session.saveOrUpdate(theCustomer)
    }

    override fun getCustomer(theId: Long): Customer {
        val session = sessionFactory.currentSession

        return session.get(Customer::class.java, theId)
    }

    override fun deleteCustomer(theId: Long) {
        val session = sessionFactory.currentSession

        val query = session.createQuery("delete from Customer where id = :theCustomerId")
        query.setParameter("theCustomerId", theId)
        query.executeUpdate()
    }

    override fun searchCustomers(theSearchName: String?): List<Customer?>? {
        val currentSession = sessionFactory.currentSession

        var theQuery: Query? = null

        if (theSearchName != null && theSearchName.trim().length > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer::class.java)
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%")
        } else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Customer", Customer::class.java)
        }

        return theQuery.getResultList()
    }
}