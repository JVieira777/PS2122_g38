import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useParams } from 'react-router-dom'
import '../pages/SearchPage.css'

export function SearchPage(){
    
    const [products, setProducts] = useState([])
    const { name } = useParams()
    
    const url = `http://localhost:8082/api/product/search/${name}`
    
    useEffect(() => {
        if (name != "") {
            axios.get(url)
                .then(response => {
                    setProducts(response.data)
                })
        }
    }, [url])

    useEffect(() => {
        if (name != "") {
            axios.get(url)
                .then(response => {
                    setProducts(response.data)
                })
        }
    }, [products])

    return (
                <div className='wrapProduct>'>
                <div className='products'>
                    <h1>
                        {products.map(prod => (
                            <div key={prod.id} className="card text-center" style={{ width: 18 }} >
                                <img className="card-img-top" src="https://new.custamenos.com/images/thumbs/default-image_450.png" alt="Card image cap" ></img>
                                <div className="card-body">
                                    <p className="card-title">{prod.name}</p>
                                    <h5 className="card-text">{prod.description}</h5>
    
                                    <a href={'http://localhost:3000/product/' + prod.id} className="btn btn-primary">info</a>
                                </div>
                            </div>
                        ))}
                    </h1>
    
                </div>
            </div>
            
      
        )
}