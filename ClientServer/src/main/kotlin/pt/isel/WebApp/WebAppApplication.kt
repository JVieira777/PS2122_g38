	package pt.isel.WebApp


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication



@SpringBootApplication

class WebAppApplication

fun main() {
	runApplication<WebAppApplication>()
	print("Application running on port: 8082")
}
