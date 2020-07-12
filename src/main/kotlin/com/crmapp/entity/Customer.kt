package com.crmapp.entity

import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(@Column(name = "first_name") var firstName : String? = null,
                    @Column(name = "last_name") var lastName : String? = null,
                    @Column(name = "email") var email : String? = null) {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
}