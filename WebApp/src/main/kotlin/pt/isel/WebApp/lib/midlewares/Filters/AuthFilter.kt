package pt.isel.WebApp.lib.midlewares.Filters

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/*@Component
@Order(1)
class AuthFilter : Filter {


    override fun doFilter(request: ServletRequest, response : ServletResponse, filterChain : FilterChain) {
        println("\n\n\n\n\n\n +++++++++++++++++++++++++++Filter++++++++++++++++++++++++++++++++ \n\n\n\n\n")
        filterChain.doFilter(request,response)
    }
}*/