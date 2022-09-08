
import  {useEffect,useState} from 'react'
import axios from 'axios'
import '../Components/SellerProductsInfoModal.css'


export function SellerProductsInfoModal({product,setModal}){
    

   

    const [Delete,setdelete] = useState(false)



    useEffect(() => {
        const urlaux = `http://localhost:8082/api/product/${product.id}`
        if(Delete){
        axios.delete(urlaux)
        }
    }, [Delete])
   
    return (
        <div className='ModalBackground6'>
        <div className='Content6'>
        <h2><p>id: {product.id}</p></h2>
        <h2><p>name: {product.name}</p></h2>
        <h2><p>description: {product.description}</p></h2>
        <h2><p>price: {product.price}Wei</p></h2>
        <h2><p>Product Rating: {product.rate}</p></h2>
        
        <button onClick = {() => {
        setdelete(true)
        }}
        >
        Delete
        </button>

    <button onClick = {() => {
        setModal(false)
        }}
        >
        Close
    </button>

    </div>
    
    </div>
    )
    


   
}