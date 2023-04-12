import React, {useEffect} from 'react'
import './CardContainer.css'
import ProductCard from '../productCard/ProductCard'
import { useContextProvider } from '../../../../context/UserContext';

import {PRODUCTOS_RANDOM,PRODUCTOS} from "../../../../staticData/url.js"

import axios from "axios"

const CardContainer = (props) => {

    const logState  = useContextProvider().logState;

logState.isLoged ? (     useEffect(() =>{
    props.productos.length !== 0 ? null :
    axios.get(PRODUCTOS).then((response) =>{
        
        props.setProductos(response.data)
        
    }).catch(err => console.log(err)) 
} , [])):(     useEffect(() =>{
    props.productos.length !== 0 ? null :
    axios.get(PRODUCTOS_RANDOM).then((response) =>{
        
        props.setProductos(response.data)
        
    }).catch(err => console.log(err)) 
} , []))

    return (
        
    <div>
        <div className="recommendations container">
        <h2 className="section-title">
            {props.title}
        </h2>
        <div className='ProductList'>

            {props.productos.map( product=>
                            <ProductCard
                                path={product.id}
                                key={product.id}
                                crimg={product.imagenes.length > 0 ? product.imagenes[0].url : ''  }
                                category={product.categoria.titulo}
                                title={product.titulo}
                                location={product.ciudad.nombre}
                                description={product.descripcion}
                                icons={product.caracteristicas}
                                avg={product.promedio}
                                product={product}
                                direccion = {product.direccion}
                            />                        
            )}
        </div>
        </div>

        
    </div>
)
}

export default CardContainer