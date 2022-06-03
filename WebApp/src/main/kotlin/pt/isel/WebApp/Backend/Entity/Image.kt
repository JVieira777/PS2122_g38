package pt.isel.WebApp.Backend.Entity

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.UUID
import javax.persistence.*



@Entity
@Table(name = "image")
data class Image (
    @Id
    val id : UUID = UUID.randomUUID(),
    val Path : String,
    val pid : UUID
    ){
    constructor() : this(UUID(0L, 0L), "",UUID(0L, 0L) )
}
