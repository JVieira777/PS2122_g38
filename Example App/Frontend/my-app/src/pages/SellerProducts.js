import  {useEffect,useState} from 'react'
import axios from 'axios'
import  { useNavigate,  useParams } from 'react-router-dom'
import { SellerProductsInfoModal } from '../Components/SellerProductsInfoModal'



export function SellerProducts(){
    const {id} = useParams()
    const navigate = useNavigate()
    
    const url = `http://localhost:8082/api/seller/${id}/products`
    const [products, setProducts] = useState([])
    const [EditModal,setEditModal] = useState(false)
    const [product, setProduct] = useState()

    useEffect(() => {
        axios.get(url)
            .then(response => {
                setProducts(response.data)
            })
    }, [url])




    if (products) {
        return (
            <div>
                <h1>
                    {products.map(prod => (
                        <div key={prod.id} className="card text-center" style={{ width: 18 }} >
                            <img className="card-img-top" src="https://new.custamenos.com/images/thumbs/default-image_450.png" alt="Card image cap" ></img>
                            <div className="card-body">
                                <p className="card-title">{prod.name}</p>
                                <h5 className="card-text">{prod.description}</h5>
                            
                                <button  className="btn btn-primary" onClick = {() => {
                                    setProduct(prod)
                                    setEditModal(true)
                                }}
                                >
                                 Edit
                                </button>  
                                <div>
                            </div>
                        </div>
                           
                        </div>
                        
                    ))}
                         {EditModal && <SellerProductsInfoModal product={product}  setModal = {setEditModal}/>}
                        
                </h1>

            </div>

        )
    }


    return (
        <div>
            <h1>No products</h1>
        </div>
    )

   
    
}