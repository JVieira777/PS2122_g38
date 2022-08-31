import React, { useState, useEffect } from 'react'
import '../Components/ExchangeViewModal.css'
import axios from 'axios'
import { RefundRequestModal } from '../Components/RefundRequestModal'
import {DeleteExchange, GetBlockchainExchangeInfo} from '../pages/Exchange'
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import abi from '../Constants/abi.json'
import contractAddresses from '../Constants/contractAddress.json' 
import  { useNavigate } from 'react-router-dom'


export function ExchangeViewModal({exchange,setModal}){
    const navigate = useNavigate()

    const [product, setProduct] = useState(" ")

    const [refundable,setRefundable] = useState(false)
    const [pay,setPay] = useState(false)
    const [collect,setCollect] = useState(false)
    const [modalRefund,setModalRefund] = useState(false)




    const { chainId: chainIdHex} = useMoralis()
    const chainId = parseInt(chainIdHex)
    const ExchangeAddress = chainId in contractAddresses ? contractAddresses[chainId][0] : null

    const {runContractFunction : collectRefund} = useWeb3Contract({
        abi:abi,
        contractAddress:ExchangeAddress,
        functionName:"collectRefund",
        params: {_id : exchange.id},
    })







    useEffect(() => {
        const url = `http://localhost:8082/api/product/${exchange.pid}`
        axios.get(url)
            .then(response => {
                setProduct(response.data)
            })
    }, [setProduct,product])
    

    useEffect(() => {
        GetBlockchainExchangeInfo(exchange.id).then(response => {
              
            if(response.data.refundable && !response.data.completed){
                setCollect(true)
                
             
            }
            else if(!response.data.payed){
                setPay(true)
                
              
            }
            if(response.data.payed && response.data.completed && !response.data.refundable){
               setRefundable(true)
               
            }
        })
    }, [])
  







    return (
        <div className='ModalBackground1'>
        <div className='Content1'>
        <h2><p>Exchange: {exchange.id}</p></h2>
        <h2><p>name: {product.name}</p></h2>
        <h2><p>description: {product.description}</p></h2>
        <h2><p>Product Rating: {product.rate}</p></h2>
        <h2><p>Totalprice: {(exchange.value)}</p></h2>
    (<button onClick = {() => {
        setModal(false)
        }}
        >
        Close
    </button>)
    {refundable && RefundButton(setModalRefund)}
    {pay && PayDeleteButtons(exchange)}
    {collect && CollectRefundButton()}
    {modalRefund && <RefundRequestModal exchange = {exchange}  setModal = {setModalRefund}/>}
    </div>
    
    </div>
    )
    


    function  RefundButton(setModalRefund){
     return(
        
            <button onClick = {() => {
            setModalRefund(true)
            }}
            >
            Refund
            </button>
         
    )
    
    }


    function CollectRefundButton(){
    return(
        
        <button onClick = {() => {
        collectRefund()
        }}
        >
        Collect
        </button>
   
        )
    }
  


    function PayDeleteButtons(exchange){
            return(
                <>
            <button onClick = {() => {
           navigate(`/product/payment/${exchange.id}`)
            }}
            >
            pay
            </button>

            <button onClick = {() => {
                DeleteExchange(exchange.id)
                setModal(false)
              }}
            >
            Cancel
            </button>
              </>
        )

    }
}