import  {useEffect,useState,Suspense, lazy} from 'react'

import {DeleteExchange, GetBlockchainExchangeInfo, GetExchangesFromUser} from './Exchange'
import  { useNavigate,  useParams } from 'react-router-dom'
import { Header} from '../Components/Header';


import  {ExchangeViewModal} from '../Components/ExchangeViewModal'

//todo button
export  function UserProfile(){
    const {id} = useParams()
    const navigate = useNavigate()
   
    const [Exchange,setexchange] = useState(0)
    const [Exchanges,setexchanges] = useState([])
    const [modal,setModal] = useState(false)
    
    const exchangesviews = []
    const [Exchangeviews,setexchangeviews] = useState(0)
    const [refundable,setRefundable] = useState(true)
    const [pay,setPay] = useState(false)
    const [collect,setCollect] = useState(false)
   
   
    useEffect(() => {
        GetExchangesFromUser(id).then( response => {
            setexchanges(response.data)

        })
        
    },[setexchanges])

   
   
 

  /*  
    if(Exchanges){
        
        Exchanges.map(exchange => {
          
            return(

                <div>
    
                <h1>
                {
                
                console.log(ExchangBCCheck (exchange))
                
                }
                       
                </h1>
                {modal && <RefundRequestModal exchange = {Exchange}  setModal = {setModal}/>}
                </div>
           
            )
            }
        )
    }
        
     
      
        
    
    /*if (exchangesviews.length>0){
        
        const view = exchangesviews.map(viewResult => {

            return(
                <div >
                <viewResult/>
                </div> 
            )
        })
        console.log(view)
            return (
               
                <div>
    
                <h1>
                {view}
                       
                    </h1>
                {modal && <RefundRequestModal exchange = {Exchange}  setModal = {setModal}/>}
                </div>
    
            )}*/
        if(Exchanges){

                return(
                    
                    <div>

                    <h1>
                        {Exchanges.map(exchange => 

                            
                        (
                            
                            <div key={exchange.id}>
                            <p>Exchange id: {exchange.id}</p>
                            <p>Product id: {exchange.pid}</p>
                            <p>value: {exchange.value}</p>
                            <p>quantity: {exchange.quantity}</p>
                            <p>totalprice: {exchange.quantity*exchange.value}</p>
                            <p>end date: {exchange.end_Date}</p>
                            (<button onClick = {() => {
                                setexchange(exchange)
                                setModal(true)
                                 }}
                                     >
                                Info
                                </button>)
                            </div> 
                        ))}
                    </h1>
                    {modal && <ExchangeViewModal exchange = {Exchange}  setModal = {setModal}/>}
                    
                </div>
        
            )
        }
        

   
/*
    async function RefundisValid(exchange){
        const valid = await CheckRefundBC(exchange)
        console.log(valid)
        if(valid){
            console.log("ola")
            return true
        }else{
            return false
        }

    }
    async function CheckRefundBC(exchange){
        return  GetBlockchainExchangeInfo(exchange.id).then(response => {   
            if(response.data.value5 &&  !response.data.value6){
              return true
            }else{
               return false
            }
        })

    }

    async function ExchangBCCheck(exchange){

        //setCollect(false)
        //setPay(false)
        //setRefundable(false)
         //refundable = false
         //pay = false
            //collect = false
          
  
    
        return  GetBlockchainExchangeInfo(exchange.id).then(response => {
              
            if(response.data.value6 && !response.data.value7){
                 ExchangeViewcollect(exchange)
                //collect = true
            }
            else if(!response.data.value5){
                 ExchangeViewpay(exchange)
                //pay = true
            }
            if(response.data.value5 && !response.data.value7 && !response.data.value6){
                ExchangeViewRefund(exchange)
                //refundable = true
            }
        })
        
    }

    async function ExchangeViewRefund(exchange){
        console.log(exchange)
        return   (
            <div key={exchange.id}>
            <p>Exchange id: {exchange.id}</p>
            <p>Product id: {exchange.pid}</p>
            <p>value: {exchange.value}</p>
            <p>quantity: {exchange.quantity}</p>
            <p>totalprice: {exchange.quantity*exchange.value}</p>
            <p>end date: {exchange.end_Date}</p>
            <RefundButton exchange = {exchange}  />
            </div> 

        )
    }

    async function ExchangeViewpay(exchange){

        return   (
            <div key={exchange.id}>
            <p>Exchange id: {exchange.id}</p>
            <p>Product id: {exchange.pid}</p>
            <p>value: {exchange.value}</p>
            <p>quantity: {exchange.quantity}</p>
            <p>totalprice: {exchange.quantity*exchange.value}</p>
            <p>end date: {exchange.end_Date}</p>
            <PayDeleteButtons  exchange = {exchange} />
            </div> 

        )
    }

    async function ExchangeViewcollect(exchange){

        return   (
            <div key={exchange.id}>
            <p>Exchange id: {exchange.id}</p>
            <p>Product id: {exchange.pid}</p>
            <p>value: {exchange.value}</p>
            <p>quantity: {exchange.quantity}</p>
            <p>totalprice: {exchange.quantity*exchange.value}</p>
            <p>end date: {exchange.end_Date}</p>
            <CollectRefundButton exchange = {exchange}  />
            </div> 

        )
    }*/
}
