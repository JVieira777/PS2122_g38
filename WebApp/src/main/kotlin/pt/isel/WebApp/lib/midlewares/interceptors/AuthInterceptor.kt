package pt.isel.WebApp.lib.midlewares.interceptors

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.util.JSONPObject
import kotlinx.coroutines.runBlocking
import okhttp3.Credentials
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler
import pt.isel.WebApp.lib.Controllers.CredentialDTO
import pt.isel.WebApp.lib.midlewares.annotations.AllowAnnonymous
import pt.isel.WebApp.lib.services.ConnectionManager
import pt.isel.WebApp.lib.services.database.DBService
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.Cookie

@Component
class AuthInterceptor(val connectionManager: ConnectionManager) : HandlerInterceptor{

    val x = UUID.randomUUID()


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        //return super.preHandle(request, response, handler)

        println("\nentered auth interceptor: $x \n")
        print("request: " + request.requestURI )

        if(handler is ResourceHttpRequestHandler){
            return true
        }

        // login in
        if(request.requestURI == "/auth/login"){
            val s = Scanner(request.inputStream, "UTF-8").useDelimiter("\\A")
            val body_data = (if (s.hasNext()) s.next() else "")
            println("\nrequest body: $body_data")
        }

        //doesn't need auth
        val allowAnnonymous = (handler as HandlerMethod).method.getAnnotationsByType(AllowAnnonymous::class.java)
        if(!allowAnnonymous.isEmpty()){
            print( "Has annotation @AllowAnnonymous \n")
            return true
        }

        //is not @AllowAnonymous
        val authCookie = request.cookies.filter { it.name == "connectionID" }//.first().value
        var connectionID = UUID(0,0)
        try {
            if(authCookie.isEmpty()){
                return false
            }
            connectionID = UUID.fromString(authCookie.first().value)
        }catch (e : Exception){
            return false
        }
        //if connectionID is valid
        if(connectionManager.connections.containsKey(connectionID)){
            return true
        }
        //if invalid
        return false
    }


    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        //super.postHandle(request, response, handler, modelAndView)
        //creating a connection with cookie
        if(response.status == 200){
            if(response.containsHeader("conID")){
                val conid = response.getHeader("conID")
                response.addCookie(Cookie("connectionID",connectionManager.newConnection(UUID.fromString(conid)).toString()))
            }
        }


    }

}