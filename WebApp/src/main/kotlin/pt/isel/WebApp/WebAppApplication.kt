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

	var httpService: HttpService = HttpService("HTTP://127.0.0.1:7545")
	var web3j = Web3j.build(httpService)
	var ethAcc: EthAccounts = web3j.ethAccounts().send()
	ethAcc.accounts.forEach({x->println(x)})




}
