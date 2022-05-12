package pt.isel.WebApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthAccounts
import org.web3j.protocol.http.HttpService

@SpringBootApplication
class WebAppApplication

fun main(args: Array<String>) {
	runApplication<WebAppApplication>(*args)


	var web3j= Web3j.build(HttpService("127.0.0.1:7545"))
	var ethAcc: EthAccounts = web3j.ethAccounts().sendAsync().get()

	print(ethAcc.accounts)
}
