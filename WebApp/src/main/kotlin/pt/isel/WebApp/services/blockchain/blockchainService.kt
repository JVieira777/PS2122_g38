package pt.isel.WebApp.services.blockchain

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

import java.io.IOException
import java.math.BigInteger

//val web3j

fun main(){

    val httpService: HttpService = HttpService("HTTP://127.0.0.1:7545")
    val web3j = Web3j.build(httpService)
    //var ethAcc: EthAccounts = web3j.ethAccounts().send()
    //ethAcc.accounts.forEach({x->println(x)})

    try{
        val clientVersion = web3j.web3ClientVersion().send()

        val str = clientVersion.web3ClientVersion
        print("clientVersion: $str")
    }catch ( e: IOException){
        print("FAILED TO CONNECT TO BLOCKCHAIN")
        //e.printStackTrace()
    }

    val pk = System.getenv("private_key")
    val credentials = Credentials.create(pk)


    //var contractAddres = deployContract(web3j,credentials); //0x0eafa10c0b2ba4c78be5539a0f87090cf6346eba
    //print("contract deployed on address: $contractAddres")

    val moderatedTransactionContract = loadContract("0x0eafa10c0b2ba4c78be5539a0f87090cf6346eba",web3j,credentials)
    val price =moderatedTransactionContract.price().send()
    print("price sent: $price")
}

fun deployContract(web3j : Web3j, credentials: Credentials) = ModeratedTransaction.deploy(
    web3j,
    credentials,
    BigInteger("20000000000") ,
    BigInteger("6721975"),
    "0xD77cE67c26aD58c63B6e0c471080bB5945D8746e",
    BigInteger("999")
).send().contractAddress

fun loadContract(address: String,web3j: Web3j,credentials: Credentials): ModeratedTransaction{
    return ModeratedTransaction.load(address,web3j,credentials, BigInteger("20000000000") , BigInteger("6721975"));
}
///C:\Users\Joao_\.web3j\web3j-cli-shadow-1.4.1\lib