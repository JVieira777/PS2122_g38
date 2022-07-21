import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useParams, Navigate } from 'react-router-dom'
import { GetSellerByid } from './Seller'
import { End_date } from '../Utils/End_date'
import { useNavigate } from 'react-router-dom'


export function NewExchange(product, quant) {

    var id = 0;
    GetSellerByid(product.sid).then(response =>

        NewBlockchainExchange(response.data.wallet, product.price * quant, 99999)
    ).then(response =>
        NewExchangeDB(product, quant, id = response.data.exchange_id)
    ).then(() =>
        window.location = `payment/${id}`
    )


}



async function NewExchangeDB(product, quant, id) {
    const url = 'http://localhost:8082/api/exchange'
    axios.post(url, {
        id: id,
        client_id: "417c4a95-eaee-4b11-b6dd-a1267845b617",
        seller_id: product.sid,
        pid: product.id,
        value: product.price * quant,
        quantity: quant,
        end_Date: "99999"
    })
    /*.then(() => {
        return <Navigate to={`payment/${id}`} replace={true} />
        
    })*/
}





export async function GetExchangesFromUser() {
    const { id } = useParams()
    const url = `http://localhost:8082/api/exchange/user/${id}`
    const [exchanges, setExchanges] = useState()


    useEffect(() => {
        axios.get(url)
            .then(response => {
                setExchanges(response.data)
            })
    }, [url])


    if (exchanges) {
        return (
            <div>
                <h1>
                    {exchanges.map(ex => (
                        <div key={ex.id}>
                            <p>Product id: {ex.pid}</p>
                            <p>value: {ex.value}</p>
                            <p>quantity: {ex.quantity}</p>
                            <p>totalprice: {ex.quantity * ex.value}</p>
                            <p>end date: {ex.end_Date}</p>
                        </div>

                        //idprod nomeprod price quantity totalprice=quantity*price button comfirm cancel
                    ))}
                </h1>
            </div>

        )
    }
}

export async function GetExchange() {
    const { id } = useParams()
    const url = `http://localhost:8082/api/exchange/${id}`
    const [exchange, setExchange] = useState()


    useEffect(() => {
        axios.get(url)
            .then(response => {
                setExchange(response.data)
            })
    }, [url])


    if (exchange) {
        return (
            <div>
                <h1>
                    {
                        <div key={exchange.id}>
                            <p>Product id: {exchange.pid}</p>
                            <p>value: {exchange.value}</p>
                            <p>quantity: {exchange.quantity}</p>
                            <p>totalprice: {exchange.quantity * exchange.value}</p>
                            <p>end date: {exchange.end_Date}</p>
                        </div>

                        //idprod nomeprod price quantity totalprice=quantity*price button comfirm cancel
                    }
                </h1>
            </div>

        )
    }
}


export function NewBlockchainExchange(seller_wallet, value, date) {
    const url = 'http://localhost:8081/api/ExchangeManager/new'
    console.log(seller_wallet)
    return axios.put(url, {
        destination: seller_wallet,//"0xf6E1141cc92DC05c1179cCFe3aD3FCd95d28e590",
        value: value,
        expiration_date: date
    })
}


export async function GetBlockchainExchange(id) {

    const url = `http://localhost:8081/api/ExchangeManager/exchange/${id}/info`
    const [exchange, setExchange] = useState()


    useEffect(() => {
        axios.get(url)
            .then(response => {
                setExchange(response.data)
            })
    }, [url])


    if (exchange) {
        return (
            <div>
                <h1>
                    {
                        <div key={exchange.id}>
                            <p>Price: {exchange.price}</p>
                            <p>destination: {exchange.destination}</p>
                            <p>end date: {exchange.end_Date}</p>
                        </div>
                    }
                </h1>
            </div>
        )
    }
}

export async function GetBlockchainExchangeInfo(id) {

    const url = `http://localhost:8081/api/ExchangeManager/exchange/${id}/info`
    return axios.get(url)
}