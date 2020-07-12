package com.crmapp.jdbctest

import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/TestDbServlet")
class TestDbServlet : HttpServlet() {
    /**
     * @see HttpServlet.doGet
     */
    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val url = "jdbc:postgresql://localhost/crmapp"
            val props = Properties()
            props.setProperty("user", "postgres")
            props.setProperty("password", "admin")
            val conn : Connection? = DriverManager.getConnection(url, props)

            if(conn != null)
                println("connected to database!")
            else
                println("unable to connect to database!")

        } catch (e: SQLException) {
            System.err.format("SQL State: %s\n%s", e.sqlState, e.message)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}