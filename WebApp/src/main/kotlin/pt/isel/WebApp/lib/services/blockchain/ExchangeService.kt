package pt.isel.WebApp.lib.services.blockchain

import kotlinx.coroutines.*
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import pt.isel.WebApp.lib.services.blockchain.interfaces.IExchangeHolder
import pt.isel.WebApp.lib.services.blockchain.utils.GasProvider
import pt.isel.WebApp.lib.services.blockchain.utils.setupGasProvider
import pt.isel.WebApp.lib.services.blockchain.wrappers.ExchangeHolder
import java.math.BigInteger
import java.util.concurrent.CompletableFuture

class ExchangeService(blockchain_url : String, contract_address: String? = null) : IExchangeHolder {

    private val web3j : Web3j = Web3j.build(HttpService(blockchain_url))

    //val contract_address = "0x01cF80D38d8C7196cd9bc2651073d4728BE3D9e9" //contract deployed using remix
    //private val CREDENTIALS : Credentials = Credentials.create(System.getenv("private_key"))
    //private val CREDENTIALS : Credentials = Credentials.create("293549cce579c185c287e254e2456b723f51a599f770e645245213c56604339f")
    private val CREDENTIALS : Credentials = Credentials.create("0xfdcba3185e2eae5283bb2eab190b5007a946358da89ee07025c924fc0e95b5d6") //kovan testnet key

    private val gasProvider : GasProvider = setupGasProvider()

    private val exchangeHolder : ExchangeHolder =
       ExchangeHolder.load(contract_address?:deployContract(gasProvider), web3j, CREDENTIALS, gasProvider)

    fun deployContract(gasProvider: GasProvider) =
        ExchangeHolder.deploy(web3j, CREDENTIALS, gasProvider).send().contractAddress

    fun loadContract(address: String?) : ExchangeHolder {
        return ExchangeHolder.load(address, web3j, CREDENTIALS, ExchangeHolder.GAS_PRICE, ExchangeHolder.GAS_LIMIT)
    }

    override suspend fun newExchange(orderId: String, price: Long, destinationAddress: String, end_date: String)  = coroutineScope {
        return@coroutineScope exchangeHolder.newExchange(BigInteger(orderId),BigInteger(price.toString()) ,destinationAddress,BigInteger(end_date)).sendAsync()
    }
    /*suspend fun newExchange2(orderId: String, price: Long, destinationAddress: String, end_date: String)  = coroutineScope {
        return@coroutineScope exchangeHolder.newExchange(BigInteger(orderId),BigInteger(price.toString()) ,destinationAddress,BigInteger(end_date)).send()
    }*/
    override suspend fun getExchange(orderId: String)  = coroutineScope {
        return@coroutineScope exchangeHolder.exchanges(BigInteger(orderId)).sendAsync()
    }

    override suspend fun completeExchange(orderId: String) = coroutineScope {
        val ex = getExchange(orderId).join()
        if(ex.component4() <= System.currentTimeMillis().toBigInteger()){
            return@coroutineScope exchangeHolder.completeOrder(BigInteger(orderId)).sendAsync()
        }
        return@coroutineScope CompletableFuture<TransactionReceipt>()
    }


}
/*
fun main() {
    val web3j : Web3j = Web3j.build(HttpService("https://kovan.infura.io/v3/e9afeb1a354f45b3b6b76a0319b8bf8b"))
    val CREDENTIALS : Credentials = Credentials.create("fdcba3185e2eae5283bb2eab190b5007a946358da89ee07025c924fc0e95b5d6")
    val gasProvider = setupGasProvider()

    /*println("load")
    val deployedContract = ExchangeHolder.load("0x01cF80D38d8C7196cd9bc2651073d4728BE3D9e9",web3j,CREDENTIALS,gasProvider)
    println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ${deployedContract.contractAddress} >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
    val ex = deployedContract.exchanges(BigInteger("1")).sendAsync()
    print(ex.join())
    println("<<<<<< add new Exchange>>>>>")
    val newExchange = deployedContract.newExchange(
        BigInteger("5"),
        BigInteger("30"),
        "0x0000000000000000000000000000000000000001",
        BigInteger(System.currentTimeMillis().toString())
    ).sendAsync()
    while (newExchange.isDone){
        print(".")
    }
    println("\n new exchange: ${newExchange.get()}")
    //try to deploy
    //val newContract = ExchangeHolder.load()*/
    val contract = ExchangeHolder.deploy(web3j,CREDENTIALS,gasProvider).sendAsync().join()
    print("contract: $contract")

}*/