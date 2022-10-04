package pt.isel.WebApp.lib.midlewares.config

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import pt.isel.WebApp.lib.midlewares.interceptors.AuthInterceptor
import pt.isel.WebApp.lib.midlewares.interceptors.ValidateTokenInterceptor
import pt.isel.WebApp.lib.services.ConnectionManager

@RequiredArgsConstructor
@Component
class ConfigInterceptors : WebMvcConfigurerAdapter() {
    val connectionManager :ConnectionManager = ConnectionManager()

    val authInterceptor : AuthInterceptor = AuthInterceptor(connectionManager)




    @Autowired
    lateinit var validateTokenInterceptor: ValidateTokenInterceptor

    override fun addInterceptors(registry: InterceptorRegistry){
        registry.addInterceptor(authInterceptor)
        registry.addInterceptor(validateTokenInterceptor)
    }
}