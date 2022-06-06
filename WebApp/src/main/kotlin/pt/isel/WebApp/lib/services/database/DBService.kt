package pt.isel.WebApp.lib.services.database

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pt.isel.WebApp.lib.services.database.Entity.*
import pt.isel.WebApp.lib.services.database.Repository.*
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
    fun addImage(image : Image) : String{
        return try {
            imageRepository.save(image)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Image Exception: ${e.message}"
        }
    }

    fun getImages() : List<Image>? = imageRepository.findAll()

    fun getImage(id: UUID) : java.util.Optional<Image> = imageRepository.findById(id)


    fun getProductImages(id: UUID) : List<Image>? = imageRepository.findAllImagesFromProduct(id)

    fun deleteImage(id: UUID) : String{
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

    fun getModerators() : List<Moderator>? = moderatorRepository.findAll()

    fun getModerator(id: UUID) : java.util.Optional<Moderator> =  moderatorRepository.findById(id)

    fun updateModerator(id: UUID, newmod: Moderator) : String{
        return try {
            val mod = moderatorRepository.findById(id).orElseThrow() { EntityNotFoundException()}
            mod.apply {
                this.description = newmod.description
            }
            moderatorRepository.save(mod)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Moderator Exception: ${e.message}"
        }
    }

    fun deleteModerator(id: UUID) : String{
        return try {
            val mod = moderatorRepository.getById(id)
            mod.apply {
                this.terminated=true
            }
            moderatorRepository.save(mod)
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

    fun getProducts() : List<Product>? =  productRepository.findAll()


    fun getProduct(id: UUID) : java.util.Optional<Product> = productRepository.findById(id)

    fun getSellerProducts(id : UUID) : List<Product>? = productRepository.findAllProductsFromSeller(id)

    fun updateProduct(id: UUID, product: Product): String {
        return try {
            val prod = productRepository.findById(id).orElseThrow() { EntityNotFoundException()}
            prod.apply {
                this.name = product.name
                this.description = product.description
                this.price = product.price
                this.rate = product.rate
            }
            productRepository.save(prod)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Product Exception: ${e.message}"
        }

    }


    fun deleteProduct(id: UUID) : String{
        return try {
            productRepository.deleteById(id)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Product Exception: ${e.message}"
        }
    }


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

    fun getSellers() : List<Seller>? = sellerRepository.findAll()

    fun getSeller(id: UUID) : java.util.Optional<Seller> = sellerRepository.findById(id)

    fun updateSeller(id: UUID, newseller: Seller) : String{
        return try {
            val seller = sellerRepository.findById(id).orElseThrow() { EntityNotFoundException()}
            seller.apply {
                this.rate = newseller.rate
                this.contry = newseller.contry
                this.description = newseller.description
            }
            sellerRepository.save(seller)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Seller Exception: ${e.message}"
        }
    }

    fun deleteSeller(id: UUID) : String{
        return try {
            val seller = sellerRepository.getById(id)
            seller.apply {
                this.terminated=true
            }
            sellerRepository.save(seller)
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

    fun getUsers() : List<User> = userRepository.findAll()

    fun getUser(id: UUID) : java.util.Optional<User> = userRepository.findById(id)

    fun deleteUser(id: UUID) : String{
        return try {
            val user = userRepository.getById(id)
            user.apply {
                this.terminated=true
            }
            userRepository.save(user)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "User Exception: ${e.message}"
        }
    }
    fun updateUser(id: UUID, newuser: User) : String{
        return try {
            val user = userRepository.findById(id).orElseThrow() { EntityNotFoundException()}
            user.apply {
                    this.rate = newuser.rate
                    this.emailAddress = newuser.emailAddress
                    this.password = newuser.password
                    this.profilePicture = newuser.profilePicture
                    this.wallet = newuser.wallet
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

    fun getExchanges() : List<Exchange> = exchangeRepository.findAll()

    fun getExchange(id: UUID) : java.util.Optional<Exchange> = exchangeRepository.findById(id)

    fun getUserExchanges(id: UUID) : List<Exchange> = exchangeRepository.GetAllUserExchanges(id)

     fun deleteExchange(id: UUID) : String{
        return try {
            val exchange = exchangeRepository.getById(id)
            exchange.apply {
                this.terminated=true
            }
            exchangeRepository.save(exchange)
            return "Success"
        }catch (e : Exception){
            e.printStackTrace()
            return "Exchange Exception: ${e.message}"
        }
    }



}