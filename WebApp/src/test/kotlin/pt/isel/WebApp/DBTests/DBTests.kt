package pt.isel.WebApp.DBTests

import Services
import User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*



internal class DBTests {
    @Autowired
   private lateinit var services: Services

//User

    @Test
    fun CreateUserTest() {
        if(::services.isInitialized) {
            val expectedUser = User(
                UUID(0L, 0L),
                "jj",
                "test.user@email.com",
                "testPASSWORD",
                0.0f,
                "",
                ""
            )
            services.DBcreateUser(expectedUser)

            val actualUser: Optional<User> = services.DBgetUser(expectedUser.id)

            assertThat(actualUser).isEqualTo(expectedUser)
        }
    }
}