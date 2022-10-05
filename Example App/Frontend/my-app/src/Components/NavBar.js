import '../Components/NavBar.css'
import { SearchBar } from './SearchBar'
import { Header } from '../Components/Header'
import Cookies from 'universal-cookie'
import  {useEffect,useState} from 'react'
import  { Link, useNavigate } from 'react-router-dom'


export function NavBar(){

    const navigate = useNavigate()
    const cookie_name = "User_Cookie"

    const cookie = new Cookies()

    const cookie_values = cookie.get(cookie_name)
    console.log(cookie_values)

    const [login,setLogin] = useState(false)
    const [seller,setSeller] = useState(false)
    const [moderator,setModerator] = useState(false)
    
    
    useEffect(() =>{
        if(cookie_values!==undefined){
        if(cookie_values!==null){
            setLogin(true)
        }
        if(cookie_values.type==='Seller'){
            setSeller(true)
        }
        if(cookie_values.type==='Moderator'){
            setModerator(true)
        }
        if(cookie_values===null){
            setLogin(false)
        }
        }
    },[cookie_values])

   

  
    return(

        <div >
            <nav className="navbar navbar-inverse" role="navigation">
            <div className="container-fluid">
                <div className="navbar-header">
                <button type="button" className="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span className="sr-only">Toggle navigation</span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                </button>
                <h2> <a href="/">Brand </a></h2>
                </div>
                <div>
                    <SearchBar placeholder="Enter the Product..." />
                </div>
       
                <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                

                <ul className="nav navbar-nav navbar-right">

                <div className='Header'>
                    <Header />
                </div>
                    {login &&
                        <li className="dropdown">
                        <a href="#" className="dropdown-toggle" data-toggle="dropdown">Menu <b className="caret"></b></a>
                        <ul className="dropdown-menu">
                            <li><Link to={"/product"}>Products</Link></li>
                            <li><Link to={`/user/profile/${cookie_values.uid}`}>User Profile</Link></li>
                            {seller && <li><Link to={`/seller/profile/${cookie_values.sid}`} >Seller Profile</Link></li>}
                            {moderator && <li><Link to={`/moderator/profile/${cookie_values.mid}`}>Moderator Profile</Link></li>}
                            <li className="divider"></li>
                            <li><a href="/logout">Logout</a></li>
                        </ul>
                        </li>
                    }
                    {!login &&
                    
                    <button className="custom-btn3 LoginButton" onClick={
                     () => {
                       navigate('/login')
                        }
                    }>
                     Login
                    </button>
                    }
                </ul>
            </div>
      
        </div>
        </nav>
        </div>
 

    
    )
    



}