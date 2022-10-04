package pt.isel.WebApp.lib.midlewares.interceptors


import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired


import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler

import pt.isel.WebApp.lib.midlewares.annotations.ValidateToken

import pt.isel.WebApp.lib.services.database.DBService


import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ValidateTokenInterceptor : HandlerInterceptor{

    @Autowired
    lateinit var dbService: DBService

    val x = UUID.randomUUID()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {


        if(handler is ResourceHttpRequestHandler){
            return true
        }
        if(request.method == "OPTIONS"){
            return true
        }

        val validateToken = (handler as HandlerMethod).method.getAnnotationsByType(ValidateToken::class.java)

        if(validateToken.isEmpty()){
            return true
        }


        println("token: " + request.getHeader("token"))
        val token = request.getHeader("token") ?: return false
        val hasToken = runBlocking{
           if(dbService.hasToken(UUID.fromString(token))){

               return@runBlocking true
           }
            return@runBlocking false

        }

        return hasToken
    }

}

