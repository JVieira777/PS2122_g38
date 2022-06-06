package pt.isel.WebApp.lib.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pt.isel.WebApp.lib.services.database.DBService
import pt.isel.WebApp.lib.services.database.Entity.*
import java.util.*


@Component
class Services {

    @Autowired
    private lateinit var dbService: DBService

    //Image
    fun addImage(image: Image) = dbService.addImage(image)

    fun getImages() = dbService.getImages()

    fun getImage(id: UUID) = dbService.getImage(id)


    fun getProductImages(id: UUID) = dbService.getProductImages(id)

    fun deleteImage(id: UUID) = dbService.deleteImage(id)


    //Moderator
    fun createModerator(mod: Moderator) = dbService.createModerator(mod)

    fun getModerators() = dbService.getModerators()

    fun getModerator(id: UUID) = dbService.getModerator(id)

    fun deleteModerator(id: UUID) = dbService.deleteModerator(id)

    //Product
    fun addProduct(product: Product) = dbService.createProduct(product)


    fun getProducts() = dbService.getProducts()

    fun getSellerProducts(id: UUID) = dbService.getSellerProducts(id)

    fun updateProduct(id: UUID, product: Product) = dbService.updateProduct(id,product)

    fun getProduct(id: UUID) = dbService.getProduct(id)

    fun deleteProduct(id: UUID) = dbService.deleteProduct(id)


    //Seller
    fun createSeller(seller: Seller) = dbService.createSeller(seller)

    fun getSellers() = dbService.getSellers()

    fun getSeller(id: UUID) = dbService.getSeller(id)

    fun deleteSeller(id: UUID) = dbService.deleteSeller(id)


    //User
    fun createUser(user: User) = dbService.createUser(user)

    fun getUsers() = dbService.getUsers()

    fun getUser(id: UUID) = dbService.getUser(id)

    fun deleteUser(id: UUID) = dbService.deleteUser(id)
    fun updateUser(id: UUID, user: User) = dbService.updateUser(id,user)

    //Exchange
    fun createExchange(exchange: Exchange) = dbService.createExchange(exchange)

    fun getExchanges() = dbService.getExchanges()

    fun getExchange(id: UUID) = dbService.getExchange(id)

    fun getUserExchanges(id: UUID) = dbService.getUserExchanges(id)

    fun deleteExchange(id: UUID) = dbService.deleteExchange(id)

}