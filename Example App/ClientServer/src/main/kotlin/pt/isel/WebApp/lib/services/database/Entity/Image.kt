package pt.isel.WebApp.lib.services.database.Entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "image")
data class Image(
    @Id
    val id: UUID = UUID.randomUUID(),
    val Path: String,
    val pid: UUID
) {
    constructor() : this(UUID(0L, 0L), "", UUID(0L, 0L))
}
