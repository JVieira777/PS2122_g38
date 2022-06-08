package pt.isel.WebApp


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource



@SpringBootApplication

class WebAppApplication

fun main() {
	runApplication<WebAppApplication>()
	print("Application running on port: 8081")
}
