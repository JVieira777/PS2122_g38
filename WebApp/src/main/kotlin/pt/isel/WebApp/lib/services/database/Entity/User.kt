package pt.isel.WebApp.lib.services.database.Entity


import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
@Entity
@Table(name = "New_User")
 data class User (
  @Id
  val id : UUID = UUID.randomUUID(),
  val username : String,
  var emailAddress : String,
  var password : String,
  var rate : Float?,
  var profilePicture : String?,
  var wallet : String,
  var terminated : Boolean = false
 ) {

 constructor() : this(UUID(0L, 0L), "", "", "", 0.0f, "", "",false)

}

