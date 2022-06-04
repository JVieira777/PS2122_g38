package pt.isel.WebApp.lib

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pt.isel.WebApp.lib.database.Entity.*
import pt.isel.WebApp.lib.database.Repository.*
import java.util.UUID
import javax.persistence.EntityNotFoundException


@Service
class DBService () {
    @Autowired
  lateinit var imageRepository: ImageRepository
    @Autowired
  lateinit var moderatorRepository: ModeratorRepository
    @Autowired
   lateinit var productRepository: ProductRepository
    @Autowired
   lateinit var sellerRepository: SellerRepository
    @Autowired
   lateinit var userRepository: UserRepository
    @Autowired
    lateinit var exchangeRepository: ExchangeRepository




    //Image
    fun createImage(image : Image) : String{
        return try {
            imageRepository.save(image)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Image Exception: ${e.message}"
        }
    }

    fun getAllImages() : List<Image>? = imageRepository.findAll()

    fun getImage(id: UUID) : java.util.Optional<Image> = imageRepository.findById(id)


    fun GetallImageFromAProduct(id: UUID) : List<Image>? = imageRepository.findAllImagesFromProduct(id)

    fun DeleteImage(id: UUID) : String{
        return try {
            imageRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Image Exception: ${e.message}"
        }
    }


    //Moderator
    fun createModerator(mod : Moderator) : String{
        return try {
            moderatorRepository.save(mod)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Moderator Exception: ${e.message}"
        }
    }

    fun getAllModerators() : List<Moderator>? = moderatorRepository.findAll()

    fun getModerator(id: UUID) : java.util.Optional<Moderator> =  moderatorRepository.findById(id)

    fun DeleteModerator(id: UUID) : String{
        return try {
            moderatorRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Moderator Exception: ${e.message}"
        }
    }

    //Product
    fun createProduct(product: Product) : String{
        return try {
            productRepository.save(product)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Product Exception: ${e.message}"
        }
    }

    fun getAllProducts() : List<Product>? =  productRepository.findAll()


    fun getProduct(id: UUID) : java.util.Optional<Product> = productRepository.findById(id)

    fun DeleteProduct(id: UUID) : String{
        return try {
            productRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Product Exception: ${e.message}"
        }
    }

    // TODO: 03/06/2022 é necessario esta funcionalidade
    fun getUserProducts(id: UUID): java.util.Optional<Product> = //productRepository.findBy

    //Seller
    fun createSeller(seller : Seller) : String{
        return try {
            sellerRepository.save(seller)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Seller Exception: ${e.message}"
        }
    }

    fun getAllSellers() : List<Seller>? = sellerRepository.findAll()

    fun getSeller(id: UUID) : java.util.Optional<Seller> = sellerRepository.findById(id)

    fun DeleteSeller(id: UUID) : String{
        return try {
            sellerRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Seller Exception: ${e.message}"
        }
    }



    //User
    fun createUser(user : User) : String{
        return try {
            userRepository.save(user)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "User Exception: ${e.message}"
        }
    }

    fun getAllUsers() : List<User> = userRepository.findAll()

    fun getUser(id: UUID) : java.util.Optional<User> = userRepository.findById(id)

    fun DeleteUser(id: UUID) : String{
        return try {
            userRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "User Exception: ${e.message}"
        }
    }
    fun UpdateUser(id: UUID) : String{
        return try {
            val user = userRepository.findById(id).orElseThrow() { EntityNotFoundException()}
            user.apply {
                    this.User_rate = user.User_rate
                    this.EmailAddress = user.EmailAddress
                    this.Password = user.Password
                    this.ProfilePicture = user.ProfilePicture
                    this.Wallet = user.Wallet
            }
            userRepository.save(user)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "User Exception: ${e.message}"
        }
    }

    //Exchange
    fun createExchange(exchange: Exchange) : String{
        return try {
            exchangeRepository.save(exchange)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Exchange Exception: ${e.message}"
        }
    }

    fun getAllExchanges() : List<Exchange> = exchangeRepository.findAll()

    fun getExchange(id: UUID) : java.util.Optional<Exchange> = exchangeRepository.findById(id)

    fun getAllExchangesFromUser(id: UUID) : List<Exchange> = exchangeRepository.GetAllUserExchanges(id)

     fun DeleteExchange(id: UUID) : String{
        return try {
            exchangeRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Exchange Exception: ${e.message}"
        }
    }




}