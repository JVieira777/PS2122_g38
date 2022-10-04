package pt.isel.WebApp.lib.Controllers

import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import pt.isel.WebApp.lib.Controllers.CredentialDTO
import org.springframework.web.bind.annotation.*
import pt.isel.WebApp.lib.midlewares.annotations.AllowAnnonymous
import pt.isel.WebApp.lib.services.Services
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping("")
class ViewController {
    @Autowired
    private lateinit var services: Services

    //home
    @AllowAnnonymous
    @GetMapping("")
    fun home(request: HttpServletRequest, model: Model): String{
        prepareModelIfAuth(request, model)
        return "home"
    }
    //register
    @AllowAnnonymous
    @GetMapping("register")
    fun registerPage(request: HttpServletRequest, model: Model): String{
        prepareModelIfAuth(request, model)
        return "register"
    }
    @AllowAnnonymous
    @PostMapping("register")
    fun registerNewUser(@RequestParam("name") name: String, @RequestParam("email") email: String, @RequestParam("password") password : String, request: HttpServletRequest, model: Model): String = runBlocking{
        prepareModelIfAuth(request, model)
        try {
            val clientDTO = ClientDTO(name, CredentialDTO(email,password))
            val response = services.newClient(clientDTO)
            if(response.first){
                return@runBlocking "login"
            }
            return@runBlocking "register"

        }catch (e : Exception){
            return@runBlocking "register"
        }

        return@runBlocking "register"

    }
    //login
    @AllowAnnonymous
    @GetMapping("login")
    fun loginPage(request: HttpServletRequest, model: Model): String{
        prepareModelIfAuth(request, model)
        return "login"
    }

    @AllowAnnonymous
    @PostMapping("login")
    fun login(@RequestParam("email") email: String, @RequestParam("password") password : String, res: HttpServletResponse, request: HttpServletRequest, model: Model): String = runBlocking{
        prepareModelIfAuth(request, model)

        try {

            val credentialDTO = CredentialDTO(email, password)
            println(credentialDTO.toString())
            val response = services.validateCredentials(credentialDTO)
            val responseHeaders =  res.getHeaders("userId")
            if(response == null){
                res.sendError(HttpStatus.REQUEST_TIMEOUT.value(),"Invalid credentials!")
                return@runBlocking "login"
            }

            res.addHeader("conID",response.toString())
            res.addHeader("logged","false")
            res.addCookie(Cookie("userId",response.toString()))

            return@runBlocking "redirect:/"

        }catch (e : Exception){
            //return@runBlocking ResponseEntity("Something went Wrong!", HttpStatus.REQUEST_TIMEOUT)

            return@runBlocking "login"
        }
        return@runBlocking "login"
    }

    @AllowAnnonymous
    @GetMapping("logout")
    fun logout(res: HttpServletResponse, request: HttpServletRequest, model: Model): String{
        prepareModelIfAuth(request, model)
        val user_cookie = Cookie("userId","")
        user_cookie.maxAge = 0
        res.addCookie(user_cookie)
        return "redirect:/"
    }

    //tokens
    @AllowAnnonymous

    @GetMapping("/tokens")

    fun tokens(model: Model, res: HttpServletResponse, request: HttpServletRequest): String = runBlocking{
        prepareModelIfAuth(request, model)
        try {
            //val response = services.getUserTokens(userId)

            if(request.cookies == null) return@runBlocking "redirect:/login"

            val userId = request.cookies.filter { it.name == "userId" }
            if(userId.isEmpty()){
                return@runBlocking "redirect:/login"
            }

            val response = services.getUserTokens(UUID.fromString(userId.first().value))

            model.addAttribute("userId",userId)
            model.addAttribute("tokens",response)

            if(response.isNotEmpty()){
                return@runBlocking "tokens"
            }
            return@runBlocking "tokens"

        }catch (e : Exception){
            return@runBlocking "tokens"
        }
    }

    @GetMapping("/tokens/new")
    fun addToken(request: HttpServletRequest, model: Model) : String = runBlocking{
    //fun addToken(@RequestParam("id") userId: UUID, request: HttpServletRequest, model: Model)  = runBlocking{
        prepareModelIfAuth(request, model)
        try {
            if(request.cookies == null) return@runBlocking "redirect:/login"

            val userId = request.cookies.filter { it.name == "userId" }
            if(userId.isEmpty()){
                return@runBlocking "redirect:/login"
            }

            val response = services.newToken(UUID.fromString(userId.first().value))

            if(response != null){
                return@runBlocking "redirect:/tokens"
            }
            return@runBlocking "redirect:/tokens"

        }catch (e : Exception){
            return@runBlocking "redirect:/tokens"
        }
    }


    //guide
    @AllowAnnonymous
    @GetMapping("guide")
    fun guide(request: HttpServletRequest, model: Model): String{
        prepareModelIfAuth(request, model)
        return "guide"
    }

    fun prepareModelIfAuth(request: HttpServletRequest, model: Model){
        if(request.cookies == null) {
            model.addAttribute("authenticated","login")
            return
        }
        if(request.cookies.filter { it.name == "connectionID" }.isEmpty()){
            model.addAttribute("authenticated","login")
        }else{
            model.addAttribute("authenticated","logout")
        }
    }


}