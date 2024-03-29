package pt.isel.WebApp.lib.midlewares.interceptors


import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler

import pt.isel.WebApp.lib.midlewares.annotations.AllowAnnonymous
import pt.isel.WebApp.lib.services.ConnectionManager

import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.Cookie

@Component
class AuthInterceptor(val connectionManager: ConnectionManager) : HandlerInterceptor{




    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {


        print("request: " +request.method + " " + request.requestURI + "\n")
        if(handler is ResourceHttpRequestHandler){
            return true
        }
        if(request.method == "OPTIONS"){
            return true
        }

        if(request.requestURI == "/logout"){
            //kill connection and clear cookies
            if(request.cookies != null){
                val cookie = request.cookies.filter { it.name == "connectionID" }.first().value
                if(cookie != "")connectionManager.killConnection(UUID.fromString(cookie))
                return true
            }
            return true
        }


        //FOR DEBUG PURPOSES ONLY, CONSUMES BODY
        /*val expectedURI ="/login"
        if(request.requestURI == expectedURI){
            val s = Scanner(request.inputStream, "UTF-8").useDelimiter("\\A")
            val body_data = (if (s.hasNext()) s.next() else "")
            println("\nrequest body: $body_data")
        }*/

        //doesn't need auth
        if(handler !is HandlerMethod){
            println("handler is not HandlerMethod")
            return true
        }
        val allowAnnonymous = (handler as HandlerMethod).method.getAnnotationsByType(AllowAnnonymous::class.java)
        if(!allowAnnonymous.isEmpty()){
            print( "Has annotation @AllowAnnonymous \n")
            return true
        }

        //is not @AllowAnonymous
        if (request.cookies == null) return false
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
            val userid = request.cookies.filter { it.name == "userId"}.first().value

            if(connectionManager.connections.get(connectionID)!!.userId.toString() == userid){
                return true
            }

        }
        //if invalid
        return false
    }


    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {

        //creating a connection with cookie
        if(request.requestURI == "/logout"){

            val con_cookie = Cookie("connectionID","")
            con_cookie.maxAge = 0
            response.addCookie(con_cookie)

        }else if(response.status == 200){


            println("headerNames:")
            response.headerNames.map { it->print(it) }
            if(response.getHeader("logged") == "false"){

                if(response.containsHeader("conID")){

                    val conid = response.getHeader("conID")
                    response.addCookie(Cookie("connectionID",connectionManager.newConnection(UUID.fromString(conid)).toString()))

                }
                response.addHeader("logged","true")
            }

        }
    }

}