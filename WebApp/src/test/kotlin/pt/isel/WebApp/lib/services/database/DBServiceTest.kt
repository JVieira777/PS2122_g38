package pt.isel.WebApp.lib.services.database



import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pt.isel.WebApp.WebAppApplication
import pt.isel.WebApp.lib.services.database.Entity.*
import java.util.*
import kotlin.streams.asSequence


@SpringBootTest(
    classes = arrayOf(WebAppApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class DBServiceTest {


    @Autowired
    private lateinit var dbService: DBService



/*
    @Test
    fun addImage() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        dbService.addImage(image)
        val actualImage: Optional<Image> = dbService.getImage(image.id)
        print("actual Image: " + actualImage.toString())
        assert(image.equals(actualImage.get()))
    }

    @Test
    fun getImages() {
        imageSetup()
        val testImages : List<Image> = listOf(image,image1,image2)
        println("testImages: " + testImages.toString())
        val allImages: List<Image>? = dbService.getImages()
        println("allImages: " + allImages.toString())
        assert(testImages.equals(allImages))

    }

    @Test
    fun getImage() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        dbService.addImage(image)
        val actualImage: Optional<Image> = dbService.getImage(image.id)
        print("actual Image: " + actualImage.toString())
        assert(image.equals(actualImage.get()))
    }

    @Test
    fun getProductImages() {
        imageSetup()
        val testImages : List<Image> = listOf(image,image1)
        println("testImages: " + testImages.toString())
        val allImages: List<Image>? = dbService.getProductImages(product.id)
        println("allImages: " + allImages.toString())
        assert(testImages.equals(allImages))
    }

    fun imageSetup(){
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        dbService.addImage(image)
        dbService.addImage(image1)
        dbService.addImage(image2)
    }
    @Test
    fun deleteImage() {
        imageSetup()
        val testImages : List<Image> = listOf(image,image1)
        println("testImages: " + testImages.toString())
        dbService.deleteImage(image2.id)
        val allImages: List<Image>? = dbService.getImages()
        println("allImages: " + allImages.toString())
        assert(testImages.equals(allImages))

    }

    @Test
    fun createModerator() {
        dbService.createUser(user)
        dbService.createModerator(moderator)
        val actualmoderator: Optional<Moderator> = dbService.getModerator(moderator.id)
        print("actual seller: " + actualmoderator.toString())
        assert(moderator.equals(actualmoderator.get()))
    }

    @Test
    fun getModerators() {
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createModerator(moderator)
        dbService.createModerator(moderator1)
        val testModerators : List<Moderator> = listOf(moderator,moderator1)
        println("testModerators: " + testModerators.toString())
        val allModerators: List<Moderator>? = dbService.getModerators()
        println("allModerators: " + allModerators.toString())
        assert(testModerators.equals(allModerators))
    }

    @Test
    fun getModerator() {
        dbService.createUser(user)
        dbService.createModerator(moderator)
        val actualmoderator: Optional<Moderator> = dbService.getModerator(moderator.id)
        print("actual seller: " + actualmoderator.toString())
        assert(moderator.equals(actualmoderator.get()))
    }

    @Test
    fun deleteModerator() {
        dbService.createUser(user)
        dbService.createModerator(moderator)
        dbService.deleteModerator(moderator.id)
        val actualModerator: Optional<Moderator> = dbService.getModerator(moderator.id)
        print("actual Moderator: " + actualModerator.toString())
        assert(actualModerator.get().terminated)
    }

    @Test
    fun createProduct() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        val actualProduct: Optional<Product> = dbService.getProduct(product.id)
        print("actual product: " + actualProduct.toString())
        assert(product.equals(actualProduct.get()))
    }

    @Test
    fun getProducts() {
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        val testProducts : List<Product> = listOf(product,product1,product2)
        println("testProducts: " + testProducts.toString())
        val actualProducts: List<Product>? = dbService.getProducts()
        print("actual products: " + actualProducts.toString())
        assert(testProducts.equals(actualProducts))
    }

    @Test
    fun getProduct() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.createProduct(product)
        val actualProduct: Optional<Product> = dbService.getProduct(product.id)
        print("actual product: " + actualProduct.toString())
        assert(product.equals(actualProduct.get()))
    }

    @Test
    fun getSellerProducts() {
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        val testProducts : List<Product> = listOf(product,product1)
        println("testProducts: " + testProducts.toString())
        val actualProducts: List<Product>? = dbService.getSellerProducts(seller.id)
        print("actual products: " + actualProducts.toString())
        assert(testProducts.equals(actualProducts))
    }

    @Test
    fun deleteProduct() {
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        dbService.createProduct(product)
        dbService.createProduct(product1)
        dbService.createProduct(product2)
        val testProducts : List<Product> = listOf(product,product1)
        println("testProducts: " + testProducts.toString())
        dbService.deleteProduct(product2.id)
        val actualProducts: List<Product>? = dbService.getProducts()
        print("actual products: " + actualProducts.toString())
        assert(testProducts.equals(actualProducts))
    }

    @Test
    fun createSeller() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        val actualSeller: Optional<Seller> = dbService.getSeller(seller.id)
        print("actual seller: " + actualSeller.toString())
        assert(seller.equals(actualSeller.get()))

    }

    @Test
    fun getSellers() {
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createSeller(seller)
        dbService.createSeller(seller2)
        val testSellers : List<Seller> = listOf(seller,seller2)
        println("testSellers: " + testSellers.toString())
        val allSellers: List<Seller>? = dbService.getSellers()
        println("allSellers: " + allSellers.toString())
        assert(testSellers.equals(allSellers))

    }

    @Test
    fun getSeller() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        val actualSeller: Optional<Seller> = dbService.getSeller(seller.id)
        print("actual seller: " + actualSeller.toString())
        assert(seller.equals(actualSeller.get()))
    }

    @Test
    fun updateSeller() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        val sellerup = Seller(
            seller.id,
            seller.name,
            "EU",
            "im a seller",
            8.0f,
            seller.wallet,
            false,
            user1.id
        )
        dbService.updateSeller(seller.id,sellerup)
        val actualSeller: Optional<Seller> = dbService.getSeller(seller.id)
        print("actual Seller: " + actualSeller.toString())
        assert(actualSeller.get().rate ==8.0f)
    }

    @Test
    fun deleteSeller() {
        dbService.createUser(user)
        dbService.createSeller(seller)
        dbService.deleteSeller(seller.id)
        val actualSeller: Optional<Seller> = dbService.getSeller(seller.id)
        print("actual seller: " + actualSeller.toString())
        assert(actualSeller.get().terminated)
    }

    @Test
    fun createUser() {
        dbService.createUser(user)
        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(user.equals(actualUser.get()))

    }

    @Test
    fun getUsers() {
        val testUsers : List<User> = listOf(user,user1,user2)
        println("testusers: " + testUsers.toString())
        dbService.createUser(user)
        dbService.createUser(user1)
        dbService.createUser(user2)

        val allUsers: List<User> = dbService.getUsers()
        println("allusers: " + allUsers.toString())
        assert(testUsers.equals(allUsers))
    }

    @Test
    fun getUser() {
        dbService.createUser(user)
        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(user.equals(actualUser.get()))
    }

    @Test
    fun deleteUser() {

        dbService.createUser(user)
        dbService.deleteUser(user.id)
        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(actualUser.get().terminated)
    }

    @Test
    fun updateUser() {
        dbService.createUser(user)
        dbService.getUser(user.id)
        val userup = User(
            user.id,
            user.username,
            user.emailAddress,
            user.password,
            8.0f,
            ""
        )
        dbService.updateUser(user.id,userup)
        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(actualUser.get().rate ==8.0f)
    }

    @Test
    fun createExchange() {
        dbService.createUser(user)
        dbService.createUser(user2)
        val actualUser: Optional<User> = dbService.getUser(user.id)
        print("actual user: " + actualUser.toString())
        assert(user.equals(actualUser.get()))
    }

    @Test
    fun getExchanges() {
    }

    @Test
    fun getExchange() {
    }

    @Test
    fun getUserExchanges() {
    }

    @Test
    fun deleteExchange() {
    }
    val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val user = User(
        UUID.randomUUID(),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        "testPASSWORD",
        0.0f,
        ""
    )
    private val user1 = User(
        UUID.randomUUID(),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        "testPASSWORD",
        0.0f,
        ""
    )
    private val user2 = User(
        UUID.randomUUID(),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        "testPASSWORD",
        0.0f,
        ""
    )


    val seller = Seller(
        UUID.randomUUID(),
        "asdasd",
        "EU",
        "im a seller",
        0.0f,
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        false,
        user.id
    )

    val seller2 = Seller(
        UUID.randomUUID(),
        "asdaasdasdsd",
        "EU",
        "im a seller",
        4.0f,
        Random().ints(15, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString(""),
        false,
        user1.id
    )

    val moderator = Moderator(
        UUID.randomUUID(),
        "asdasd",
        "im a mod",
        false,
        user.id
    )
    val moderator1 = Moderator(
        UUID.randomUUID(),
        "asdsadsaasd",
        "im a mod1",
        false,
        user1.id
    )

    val product = Product(
        UUID.randomUUID(),
        "asdasd",
        "cards",
        35,
        2.0f,
        seller.id
    )
    val product1 = Product(
        UUID.randomUUID(),
        "asdassadd",
        "plushness",
        25,
        2.0f,
        seller.id
    )
    val product2 = Product(
        UUID.randomUUID(),
        "asdaswwwsadd",
        "plushnasdess",
        245,
        2.0f,
        seller2.id
    )
    val image = Image(
        UUID.randomUUID(),
        "asdasd",
        product.id
    )
    val image1 = Image(
        UUID.randomUUID(),
        "asdazxcxzcsd",
        product.id
    )
    val image2 = Image(
        UUID.randomUUID(),
        "asd",
        product1.id
    )

*/

}