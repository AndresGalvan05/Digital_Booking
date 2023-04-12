import React, { useEffect, useState } from 'react'
import { BUSCARDOR_PRODUCTOS_POR_CIUDAD, PRODUCTOS, PRODUCTOS_ID } from '../../../staticData/url'
import axios from "axios"
import "./listProductAdmin.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEdit,
    faPrescriptionBottle} from "@fortawesome/free-solid-svg-icons"
import { useContextProvider } from "../../../context/UserContext"
import { Link } from 'react-router-dom'

const ListProductAdmin = () => {
    const logState = useContextProvider().logState
    const [products, setProducts] = useState([])
    const [estadoModal, cambiarEstadoModal] = useState(false);



    useEffect(()=>{
        axios.get(PRODUCTOS) .then( (res) =>{
                setProducts(res.data)
        }).catch(err=>console.log(err))
    },[])


const searchProducts = (value) =>{
    axios.get( BUSCARDOR_PRODUCTOS_POR_CIUDAD+value).then(
        res =>{
            console.log(res.data);
            setProducts(res.data)
        }
    )
}

const config = {
    headers: {
        "Authorization": `Bearer ${logState.jwt}`,
        'Accept' : 'application/json',
        'Content-Type': 'application/json',
        "Access-Control-Allow-Origin": "*"
    }
}

const deleteProduct = (id_product) =>{   
    axios.delete(PRODUCTOS_ID+id_product, config).then((res) =>{
        console.log(res);
        location.reload()
    }).catch(error =>{
        console.log(error);
    })
}
  return (
    <div className='main-container-list'>
        
        <div className='form-product-admin'>   
            <form onSubmit={searchProducts}>
                <input type="text" onChange={e => searchProducts(e.target.value) } />
                <button className='button_main'>Buscar</button>
            </form>
        </div>


        <div className='table'>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tipo</th>
                        <th>Nombre</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                {products.map( product => 
                            <tr>
                                <td>
                                {product.id}
                                </td>
                            <td>
                             {product.categoria.titulo} 
                            </td>
                            <td>
                             {product.titulo} 
                            </td>
                            <td>
                                <Link to={"/admin/product-update/" + product.id}>
                                <FontAwesomeIcon className='icons-admin' icon={faEdit}/>
                                </Link>
                                
                            </td>
                            <td>
                                <FontAwesomeIcon className='icons-admin-delete' onClick={() => deleteProduct(product.id)} icon={faPrescriptionBottle}/>
                            </td>
                             </tr>
                    )}
                    {/* {products.length > 1 ? "menor" : "mayor" } */}
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default ListProductAdmin