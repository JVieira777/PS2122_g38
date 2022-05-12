package pt.isel.ps2122g38WebApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthAccounts

import org.web3j.protocol.http.HttpService
import java.io.IOException


@SpringBootApplication
class Ps2122G38WebAppApplication

fun main(args: Array<String>) {
	runApplication<Ps2122G38WebAppApplication>(*args)


	var ethAcc: EthAccounts


	val web3j = Web3j.build(HttpService("127.0.0.1:7545"))
	try {
		ethAcc = web3j.ethAccounts().sendAsync().get()
		println(ethAcc.accounts)
	}catch (e: IOException){
		print("Exception thrown")
	}





}
