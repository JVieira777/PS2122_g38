package pt.isel.WebApp.lib.services.blockchain

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import pt.isel.WebApp.lib.services.blockchain.wrappers.ExchangeHolder

import java.math.BigInteger
import java.util.*
/*
/**
 *return javascript to interact with metamask on client side
 */

private val BLOCKCHAIN_NETWORK_URL = "HTTP://127.0.0.1:7545"

private val web3j: Web3j = Web3j.build(HttpService(BLOCKCHAIN_NETWORK_URL))

//val CREDENTIALS = Credentials.create(System.getenv().get("private_key"))
private val CREDENTIALS = Credentials.create("293549cce579c185c287e254e2456b723f51a599f770e645245213c56604339f") //mock private Key

//val CONTRACT_ADDRESS = "".-
//val exchangeContract = loadContract(CONTRACT_ADDRESS)

fun deployContract() = ExchangeHolder_v0.deploy(
    web3j,
    CREDENTIALS,
    ExchangeHolder_v0.GAS_PRICE,
    ExchangeHolder_v0.GAS_LIMIT
).send().contractAddress


fun loadContract(address: String?) = ExchangeHolder_v0
    .load(
        address,
        web3j,
        CREDENTIALS,
        ExchangeHolder_v0.GAS_PRICE,
        ExchangeHolder_v0.GAS_LIMIT
    )
fun addNewExchange(contractAddress : String, orderId : BigInteger, price: BigInteger, destinationAddress: String, end_date : Date) =
    loadContract(contractAddress).newExchange(orderId,price,destinationAddress, end_date.time.toBigInteger()).send()


fun getExchange(contractAddress: String,orderId: BigInteger) =
    loadContract(contractAddress).exchanges(orderId).send()

//fun refund(orderId: BigInteger) = ExchangeHolder.load(ADDRESS)*/

/*fun main()= runBlocking{
    val web3j = Web3j.build(HttpService("https://kovan.infura.io/v3/e9afeb1a354f45b3b6b76a0319b8bf8b"))
    val block = web3j.ethBlockNumber().send()
    delay(10000)
    print(block)
}*/

///C:\Users\Joao_\.web3j\web3j-cli-shadow-1.4.1\lib
