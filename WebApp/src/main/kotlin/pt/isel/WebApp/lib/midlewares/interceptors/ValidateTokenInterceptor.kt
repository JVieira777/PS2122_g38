package pt.isel.WebApp.lib.midlewares.interceptors


import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler

import pt.isel.WebApp.lib.midlewares.annotations.ValidateToken

import pt.isel.WebApp.lib.services.database.DBService
import pt.isel.WebApp.lib.services.database.Entities.Token
import java.sql.ResultSet

import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ValidateTokenInterceptor (/*val dbService: DBService*/) : HandlerInterceptor{

    @Autowired
    lateinit var dbService: DBService

    val x = UUID.randomUUID()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        //return super.preHandle(request, response, handler)

        println("\nentered token interceptor: $x \n")
        print("request: " + request.requestURI )

        if(handler is ResourceHttpRequestHandler){
            return true
        }

        val validateToken = (handler as HandlerMethod).method.getAnnotationsByType(ValidateToken::class.java)

        if(validateToken.isEmpty()){
            return true
        }

        request.setAttribute("tokenIsValid",false)

        val token = request.getHeader("token") ?: return true
        val hasToken = runBlocking{
           if(dbService.hasToken(UUID.fromString(token))){
               request.setAttribute("tokenIsValid",true)
               //return@runBlocking true
           }
            return@runBlocking true
            //return@runBlocking false
        }

        return hasToken
    }

}

