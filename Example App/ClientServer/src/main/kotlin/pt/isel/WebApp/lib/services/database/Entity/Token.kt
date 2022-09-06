package pt.isel.WebApp.lib.services.database.Entity


import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "token")
data class Token(
    @Id
    val id: UUID = UUID.randomUUID(),
    val uid: UUID

) {
    constructor() : this(UUID(0L, 0L), UUID(0L, 0L))
}