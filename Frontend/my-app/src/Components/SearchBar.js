import React, { useEffect, useState, useRef } from 'react'
import axios from 'axios'

export function SearchBar({ placeholder }) {
    const [name, setName] = useState("")
    const [products, setProducts] = useState([])
    const url = `http://localhost:8082/api/product/search/${name}`
    const searchRef = useRef(null) //vai guardar o value writen


    useEffect(() => {
        console.log(name)
        if (name != "") {
            axios.get(url)
                .then(response => {
                    console.log(response.data)
                    setProducts(response.data)
                })
        }
    }, [name])


    function handleValues(e) {
        e.preventDefault()
        setName(searchRef.current?.value)
    }



    return (
        <div className='search'>
            <form onSubmit={(e) => { handleValues(e) }}>
                <div className='Input'>
                    <input type="text" placeholder={placeholder} ref={searchRef}></input>
                    <button>Search</button>
                </div>
            </form>

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










