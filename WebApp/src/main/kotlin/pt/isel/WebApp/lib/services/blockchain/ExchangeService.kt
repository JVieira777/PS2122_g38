package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import org.web3j.tuples.generated.Tuple6
import pt.isel.WebApp.lib.services.blockchain.interfaces.IExchangeHolder
import pt.isel.WebApp.lib.services.blockchain.utils.GasProvider
import pt.isel.WebApp.lib.services.blockchain.utils.setupGasProvider
import pt.isel.WebApp.lib.services.blockchain.wrappers.ExchangeHolder
import java.math.BigInteger
import java.util.concurrent.CompletableFuture

class ExchangeService(blockchain_url : String, contract_address: String? = null) : IExchangeHolder {

    private val web3j : Web3j = Web3j.build(HttpService(blockchain_url))
    //private val CREDENTIALS : Credentials = Credentials.create(System.getenv("private_key"))
    private val CREDENTIALS : Credentials = Credentials.create("293549cce579c185c287e254e2456b723f51a599f770e645245213c56604339f")
    private val gasProvider : GasProvider = setupGasProvider()

    private val exchangeHolder : ExchangeHolder =
       ExchangeHolder.load(contract_address?:deployContract(gasProvider), web3j, CREDENTIALS, gasProvider)

    fun deployContract(gasProvider: GasProvider) =
        ExchangeHolder.deploy(web3j, CREDENTIALS, gasProvider).send().contractAddress

    fun loadContract(address: String?) : ExchangeHolder {
        return ExchangeHolder.load(address, web3j, CREDENTIALS, ExchangeHolder.GAS_PRICE, ExchangeHolder.GAS_LIMIT)
    }

    override suspend fun newExchange(orderId: String, price: Long, destinationAddress: String, end_date: String) : TransactionReceipt = coroutineScope {
        return@coroutineScope exchangeHolder.newExchange(BigInteger(orderId),BigInteger(price.toString()) ,destinationAddress,BigInteger(end_date)).send()
    }
    override suspend fun getExchange(orderId: String)  = coroutineScope {
        return@coroutineScope exchangeHolder.exchanges(BigInteger(orderId)).send()
    }

    override suspend fun completeExchange(orderId: String) = coroutineScope {
        return@coroutineScope exchangeHolder.completeOrder(BigInteger(orderId)).send()
    }

}