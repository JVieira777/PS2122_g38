package pt.isel.WebApp.Entity

import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.UUID
import javax.persistence.*



@Entity
@Table(name = "image")
data class Image (
    @Id
    val id : UUID = UUID.randomUUID(),
    var image_Name : String?,
    val image_Path : String,
    val pid : UUID
    ){
    constructor() : this(UUID(0L, 0L), "", "",UUID(0L, 0L) )
}
