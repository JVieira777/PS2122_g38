import { Header} from '../Components/Header';
import  { useWeb3Contract, useMoralis } from 'react-moralis'
import  {useEffect,useState, useRef} from 'react'
import axios from 'axios';
import  { useNavigate,  useParams } from 'react-router-dom'


export function RefundRequests(){
    const navigate = useNavigate()
    const searchRef = useRef(null)
    
   
    
    const url = 'http://localhost:8082/api/RefundRequest'
    const [Delete,setdelete] = useState(false)
    const [Completed,setcompleted] = useState(false)
    const [ExchangeId,setexchangeId] = useState(0)
    const [RefundRequest,setrefundRequest] = useState(
        {
            id: 0,
            client_id: 0,
            description: "",
            exchange_id : 0
        }
    )

    useEffect(() => {
        axios.get(url)
            .then(response => {
                setrefundRequest(response.data)
            })
    }, [url,setcompleted,Completed])

    useEffect(() => {
        const urlaux = `http://localhost:8082/api/RefundRequest/${RefundRequest.id}`
        if(Delete){
        axios.delete(urlaux)
            .then(() => {
                setcompleted(true)
            })
        }
    }, [Delete])

    useEffect(() => {
       
        refund(ExchangeId)
      
    }, [ExchangeId])
    

    function handleValues(e) {
        e.preventDefault()
        navigate(`/moderator/refundRequest/${(searchRef.current?.value)}`)
    }


    if (RefundRequest) {
        return (
            <div>
                <h1>
                    
                       
                    {
                        <div >
                        <p>Request_id: {RefundRequest.id}</p>
                        <p>User_id: {RefundRequest.client_id}</p>
                        <p>Exchange_id: {RefundRequest.exchange_id}</p>
                        <p>description: {RefundRequest.description}</p>
                        </div>
                    }
                   
                </h1>
                <form onSubmit={(e) => { handleValues(e) }}>
                <div className='Input'>
                    <input type="text" placeholder={"Request id ..."} ref={searchRef}></input>
                    <button>Get</button>
                </div>
                </form>

                (<button onClick = {() => {
                 setdelete(true)
                    }}
                    >
                    Not Aproved
                </button>)  
                (<button onClick = { () =>  {
                    setexchangeId(RefundRequest.exchange_id)
                    setdelete(false)    
                }}
                    >
                    Aproved
                </button>)  
                        </div>

                )
    }


    return (
        <div>
            <h1>No Requests</h1>
        </div>
    )

}


export function RefundRequestbyID(){
    const {id} = useParams()
    const searchRef = useRef(null)
    const navigate = useNavigate()
    const [Completed,setcompleted] = useState(false)

    const url = `http://localhost:8082/api/RefundRequest/${id}`
    const [Delete,setdelete] = useState(false)
    const [RefundRequest,setrefundRequest] = useState(
        {
            id: 0,
            client_id: 0,
            description: "",
            exchange_id : 0
        }
    )
  

    useEffect(() => {
        axios.get(url)
            .then(response => {
                setrefundRequest(response.data)
            })
    }, [url])


    useEffect(() => {
    if(Completed){
        navigate('/moderator/refundRequest/')
        }
    }, [setcompleted])

    useEffect(() => {
        const urlaux = `http://localhost:8082/api/RefundRequest/${id}`
        if(Delete){
        axios.delete(urlaux)
            .then(() => {
                setcompleted(true)
            })
        }
    }, [Delete])

   
    function handleValues(e) {
        e.preventDefault()
        navigate(`/moderator/refundRequest/${(searchRef.current?.value)}`)
    }


    if (RefundRequest) {
        return (
            <div>
                <h1>
                    
                       
                    {
                        <div >
                        <p>Request_id: {id}</p>
                        <p>User_id: {RefundRequest.client_id}</p>
                        <p>Exchange_id: {RefundRequest.exchange_id}</p>
                        <p>description: {RefundRequest.description}</p>
                        </div>
                    }
                   
                </h1>
                <form onSubmit={(e) => { handleValues(e) }}>
                <div className='Input'>
                    <input type="text" placeholder={"Request id ..."} ref={searchRef}></input>
                    <button>Get</button>
                </div>
                </form>

                (<button onClick = {() => {
                 setdelete(true)
                    }}
                    >
                    Not Aproved
                </button>)  
                (<button onClick = { () =>  {
                    setdelete(false)
                    refund(id)
                }}
                    >
                    Aproved
                </button>)  
                        </div>

                )
    }


    return (
        <div>
            <h1>No Requests</h1>
        </div>
    )

}

function refund(id){
    const url = `http://localhost:8081/api/ExchangeManager/exchange/${id}/refund`
    return axios.put(url,{ headers: {"Authorization" : `Bearer ${'f65d8b4a-8bfa-44d5-b985-cf07ab7fe4ee'}`} })

}