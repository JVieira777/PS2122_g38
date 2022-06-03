package pt.isel.WebApp.lib.blockchain.utils

import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger

class GasProvider : ContractGasProvider {

    var functionsPrice : HashMap<String,GasInfo> = HashMap<String,GasInfo>()


    fun addNewGasInfo(contractFunc: String, gasPrice: BigInteger?, gasLimit: BigInteger?) =
        functionsPrice.put(contractFunc,GasInfo(gasPrice,gasLimit))

    override fun getGasPrice(contractFunc: String?): BigInteger? {
        return functionsPrice.get(contractFunc)?.gasPrice
    }

    override fun getGasPrice(): BigInteger {
        TODO("Not yet implemented")
    }

    override fun getGasLimit(contractFunc: String?): BigInteger? {
        return functionsPrice.get(contractFunc)?.gasLimit
    }

    override fun getGasLimit(): BigInteger {
        TODO("Not yet implemented")
    }
}
fun setupGasProvider() : GasProvider{
    val toret = GasProvider()
    toret.addNewGasInfo("newExchange" , BigInteger("20000"), BigInteger("210000"))
    toret.addNewGasInfo("refund" , BigInteger("20000"), BigInteger("210000"))
    toret.addNewGasInfo("completeOrder" , BigInteger("20000"), BigInteger("210000"))
    return toret
}

data class GasInfo(val gasPrice: BigInteger?, var gasLimit: BigInteger?)