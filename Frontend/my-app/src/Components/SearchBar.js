import React, {  useRef } from 'react'
import './SearchBar.css'
import  { useNavigate } from 'react-router-dom'

export function SearchBar({ placeholder }) {

    const searchRef = useRef(null) //vai guardar o value writen
    const navigate = useNavigate()




    function handleValues(e) {
        e.preventDefault()
        navigate(`/search/${searchRef.current?.value}`)
        
    }



    return (
    <div className='wrap'>
        <div className='search'>
            <form onSubmit={(e) => { handleValues(e) }}>
            
                    <input type="text" className='searchInput' placeholder={placeholder} ref={searchRef}></input>
                     <button type="submit" className="searchButton">
                     <a href="#"><img className="search-icon" src="http://www.endlessicons.com/wp-content/uploads/2012/12/search-icon.png"/></a>
                     </button>
        
            </form>
        </div>
    </div>       
    )


}










