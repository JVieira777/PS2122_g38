import '../Components/NavBar.css'
import { SearchBar } from './SearchBar'
import { Header } from '../Components/Header'


export function NavBar(){
    
    return(
    <div classNameName="body-wrap">


        <div classNameName="container">
            <nav className="navbar navbar-inverse" role="navigation">
            <div className="container-fluid">
                <div className="navbar-header">
                <button type="button" className="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span className="sr-only">Toggle navigation</span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                </button>
                <a className="navbar-brand" href="#">Brand</a>
                </div>
                <div>
                    <SearchBar placeholder="Enter the Product..." />
                </div>
       
                <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                

                <ul className="nav navbar-nav navbar-right">

                <div className='Header'>
                    <Header />
                </div>
                        <li className="dropdown">
                        <a href="#" className="dropdown-toggle" data-toggle="dropdown">Menu <b className="caret"></b></a>
                        <ul className="dropdown-menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li className="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                        </li>
                </ul>
            </div>
      
        </div>
        </nav>
        </div>
    </div>


    )




}