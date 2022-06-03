package pt.isel.WebApp.lib

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import pt.isel.WebApp.lib.database.Entity.*
import pt.isel.WebApp.lib.database.Entity.Moderator
import pt.isel.WebApp.lib.database.Entity.Product
import java.util.*


@Component
class Services {

    @Autowired
    private lateinit var dbService: DBService

    //Image
    fun DBcreateImage(image: Image) = dbService.createImage(image)

    fun DBgetAllImages() = dbService.getAllImages()

    fun DBgetImage(id: UUID) = dbService.getImage(id)


    fun DBGetallImageFromAProduct(id: UUID) = dbService.GetallImageFromAProduct(id)

    fun DBDeleteImage(id: UUID) = dbService.DeleteImage(id)


    //Moderator
    fun DBcreateModerator(mod: Moderator) = dbService.createModerator(mod)

    fun DBgetAllModerators() = dbService.getAllModerators()

    fun DBgetModerator(id: UUID) = dbService.getModerator(id)

    fun DBDeleteModerator(id: UUID) = dbService.DeleteModerator(id)

    //Product
    fun DBcreateProduct(product: Product) = dbService.createProduct(product)


    fun DBgetAllProducts() = dbService.getAllProducts()


    fun DBgetProduct(id: UUID) = dbService.getProduct(id)

    fun DBDeleteProduct(id: UUID) = dbService.DeleteProduct(id)


    //Seller
    fun DBcreateSeller(seller: Seller) = dbService.createSeller(seller)

    fun DBgetAllSellers() = dbService.getAllSellers()

    fun DBgetSeller(id: UUID) = dbService.getSeller(id)

    fun DBDeleteSeller(id: UUID) = dbService.DeleteSeller(id)


    //User
    fun DBcreateUser(user: User) = dbService.createUser(user)

    fun DBgetAllUsers() = dbService.getAllUsers()

    fun DBgetUser(id: UUID) = dbService.getUser(id)

    fun DBDeleteUser(id: UUID) = dbService.DeleteUser(id)
    fun DBUpdateUser(id: UUID) = dbService.UpdateUser(id)

    //Exchange
    fun DBcreateExchange(exchange: Exchange) = dbService.createExchange(exchange)

    fun DBgetAllExchanges() = dbService.getAllExchanges()

    fun DBgetExchange(id: UUID) = dbService.getExchange(id)

    fun DBgetAllExchangesFromUser(id: UUID) = dbService.getAllExchangesFromUser(id)

    fun DBDeleteExchange(id: UUID) = dbService.DeleteExchange(id)
}