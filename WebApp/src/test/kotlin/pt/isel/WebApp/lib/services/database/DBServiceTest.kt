package pt.isel.WebApp.lib.services.database


import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.Query
import pt.isel.WebApp.WebAppApplication
import pt.isel.WebApp.lib.services.database.Entity.Image
import pt.isel.WebApp.lib.services.database.Entity.Moderator
import pt.isel.WebApp.lib.services.database.Entity.Seller
import pt.isel.WebApp.lib.services.database.Entity.User
import java.util.*
import kotlin.streams.asSequence

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@SpringBootTest(
    classes = arrayOf(WebAppApplication::class),
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class DBServiceTest {


    @Autowired
    private lateinit var dbService: DBService


    @Test
    fun connectionTest(){

    }


    @Test
    fun addImage() {

    }

    @Test
    fun getImages() {
    }

    @Test
    fun getImage() {
    }

    @Test
    fun getProductImages() {
    }

    @Test
    fun deleteImage() {
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
    }

    @Test
    fun createProduct() {
    }

    @Test
    fun getProducts() {
    }

    @Test
    fun getProduct() {
    }

    @Test
    fun getSellerProducts() {
    }

    @Test
    fun deleteProduct() {
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
            "",
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
        "",
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
        "",
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
        "",
        ""
    )


    val seller = Seller(
        UUID.randomUUID(),
        "asdasd",
        "EU",
        "im a seller",
        0.0f,
        false,
        user.id
    )

    val seller2 = Seller(
        UUID.randomUUID(),
        "asdaasdasdsd",
        "EU",
        "im a seller",
        4.0f,
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




}