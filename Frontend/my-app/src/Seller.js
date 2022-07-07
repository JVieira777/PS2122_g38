import React, {useEffect,useState} from 'react'
import axios from 'axios'
import { useParams } from 'react-router-dom'


export function GetSeller() {
    const {id} = useParams()
    const url = `http://localhost:8081/api/seller/${id}`
    const [seller,setseller] = useState()
    
    useEffect(() =>{
        axios.get(url)
        .then(response => {
            setseller(response.data)
        })
    },[url])
    if(seller){return(
        <div>
            <h1>
                
                    <div key={seller.id}>
                    <h2><p>name: {seller.name}</p></h2>
                    <p>contry: {seller.contry}</p>
                    <p>description: {seller.description}</p>
                    <p>Rating: {seller.rate}</p>
                    
                    </div>
                
            </h1>
        </div>

    )
    }
    
    return(
        <div>
            <h1>Seller does not exist</h1>
        </div>
    )
}