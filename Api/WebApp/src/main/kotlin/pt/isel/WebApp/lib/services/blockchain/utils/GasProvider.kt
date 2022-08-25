package pt.isel.WebApp.lib.services.blockchain.utils

import org.web3j.tx.gas.ContractGasProvider
import java.math.BigInteger

class GasProvider : ContractGasProvider {
    var functionsPrice : HashMap<String, GasInfo> = HashMap<String, GasInfo>()
    val GAS_PRICE = BigInteger("2500000009")
    val GAS_LIMIT = BigInteger("900000")


    fun addNewGasInfo(contractFunc: String, gasPrice: BigInteger?, gasLimit: BigInteger?) =
        functionsPrice.put(contractFunc, GasInfo(gasPrice,gasLimit))

    override fun getGasPrice(contractFunc: String?): BigInteger? {
        return functionsPrice.get(contractFunc)?.gasPrice
    }

    override fun getGasPrice(): BigInteger {
        return GAS_PRICE
    }

    override fun getGasLimit(contractFunc: String?): BigInteger? {
        return functionsPrice.get(contractFunc)?.gasLimit
    }

    override fun getGasLimit(): BigInteger {
        return GAS_LIMIT
    }
}
fun setupGasProvider() : GasProvider {
    val toret = GasProvider()
    //toret.addNewGasInfo("newExchange" , BigInteger("1500000000"), BigInteger("38894"))
    toret.addNewGasInfo("newExchange" , BigInteger("1500000000"), BigInteger("150000"))
    toret.addNewGasInfo("refund" , BigInteger("1500000000"), BigInteger("39854"))
    toret.addNewGasInfo("completeOrder" , BigInteger("1500000000"), BigInteger("29242"))
    toret.addNewGasInfo("deploy",BigInteger("1500000000"), BigInteger("880972"))
    toret.addNewGasInfo("load",BigInteger("1500000000"), BigInteger("1000"))
    return toret
}

data class GasInfo(val gasPrice: BigInteger?, var gasLimit: BigInteger?)