package pt.isel.WebApp

import org.web3j.crypto.Credentials;
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.EthAccounts
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.TransactionManager
import pt.isel.WebApp.services.blockchain.ModeratedTransaction
import java.io.IOException
import java.math.BigInteger


@SpringBootApplication
class WebAppApplication

fun main() {
	runApplication<WebAppApplication>()


}
