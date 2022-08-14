import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useParams, useNavigate } from 'react-router-dom'


export function GetSeller() {
    const { id } = useParams()
    const url = `http://localhost:8082/api/seller/${id}`
    const [seller, setseller] = useState()

    useEffect(() => {
        axios.get(url)
            .then(response => {
                setseller(response.data)
            })
    }, [url])
    if (seller) {
        return (
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

    return (
        <div>
            <h1>Seller does not exist</h1>
        </div>
    )
}

export function GetSellerByid(id) {
    const url = `http://localhost:8082/api/seller/${id}`
    return axios.get(url)


}


export function CreateSeller(id) {
    const url = 'http://localhost:8082/api/seller'
    
    const navigate = useNavigate()
    const [seller, setSeller] = useState({
         name : "",
         country : "",
         description : "",
         wallet : "",
         uid : ""
    })

    function handleSignup(e) {
        e.preventDefault()
        
        axios.post(url, {
            name : seller.name,
            country : seller.country,
            description : seller.description,
            wallet : seller.wallet,
            uid : id,
            rate: 0.0
        }).then(() => {
            console.log("seller successfully created with the name:" + seller.name)
            navigate("/")
        })
    }

    function handleValues(e) {
        const newseller = { ...seller }
        newseller[e.target.id] = e.target.value
        setSeller(newseller)
    }


    return (
        <div>
            <form onSubmit={(e) => handleSignup(e)}>
                <label>Name</label>
                <input type="text" id='name' value={seller.name} onChange={(e) => handleValues(e)}></input>
                <label>Country</label>
                <input type="text" id='country' value={seller.country} onChange={(e) => handleValues(e)}></input>
                <label>Description</label>
                <input type="text" id='description' value={seller.description} onChange={(e) => handleValues(e)}></input>
                <label>Wallet</label>
                <input type="text" id='wallet' value={seller.wallet} onChange={(e) => handleValues(e)}></input>
                <button>Became a Seller</button>
            </form>
        </div>
    )

}
