package pt.isel.WebApp.lib

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.isel.WebApp.lib.blockchain.ExchangeService

import pt.isel.WebApp.lib.database.Entity.*
import pt.isel.WebApp.lib.database.Entity.Moderator
import pt.isel.WebApp.lib.database.Entity.Product
import java.math.BigInteger
import java.util.*


@Component
class Services {

    @Autowired
    private lateinit var dbService: DBService

    private lateinit var exchangeService: ExchangeService

    @Autowired
    private fun setExchangeHolder(){
        exchangeService = ExchangeService("HTTP://127.0.0.1:7545")
    }

    //Image
    fun addImage(image: Image) = dbService.createImage(image)

    fun getImages() = dbService.getAllImages()

    fun getImage(id: UUID) = dbService.getImage(id)


    fun getProductImages(id: UUID) = dbService.GetallImageFromAProduct(id)

    fun deleteImage(id: UUID) = dbService.DeleteImage(id)


    //Moderator
    fun createModerator(mod: Moderator) = dbService.createModerator(mod)

    fun getModerators() = dbService.getAllModerators()

    fun getModerator(id: UUID) = dbService.getModerator(id)

    fun deleteModerator(id: UUID) = dbService.DeleteModerator(id)

    //Product
    fun addProduct(product: Product) = dbService.createProduct(product)


    fun getProducts() = dbService.getAllProducts()


    fun getProduct(id: UUID) = dbService.getProduct(id)

    fun deleteProduct(id: UUID) = dbService.DeleteProduct(id)


    //Seller
    fun createSeller(seller: Seller) = dbService.createSeller(seller)

    fun getSellers() = dbService.getAllSellers()

    fun getSeller(id: UUID) = dbService.getSeller(id)

    fun deleteSeller(id: UUID) = dbService.DeleteSeller(id)


    //User
    fun createUser(user: User) = dbService.createUser(user)

    fun getUsers() = dbService.getAllUsers()

    fun getUser(id: UUID) = dbService.getUser(id)

    fun deleteUser(id: UUID) = dbService.DeleteUser(id)
    fun updateUser(id: UUID) = dbService.UpdateUser(id)

    //Exchange
    fun createExchange(exchange: Exchange) : String {
        if(dbService.createExchange(exchange) == "sucess"){
            val user = getUser(exchange.uidB)
            if(exchangeService.newExchange(
                BigInteger(exchange.id.toString()),
                BigInteger(exchange.Exchange_Value.toString()),
                user.get().Wallet,
                BigInteger(exchange.End_Date.time.toString())
            ).isStatusOK) return exchange.id.toString()
        }
        return "failed";
    }

    fun getExchanges() = dbService.getAllExchanges()

    fun getExchange(id: UUID) = dbService.getExchange(id)

    fun getUserExchanges(id: UUID) = dbService.getAllExchangesFromUser(id)

    fun deleteExchange(id: UUID) = dbService.DeleteExchange(id)
}