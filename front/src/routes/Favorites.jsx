import React, { useEffect, useState } from 'react'
import { useContextProvider } from '../context/UserContext'
import axios from 'axios'
import { PRODUCTOS_RANDOM, PRODUCTOS_ID } from '../staticData/url'
import ProductCard from '../components/mainPage/productCards/productCard/ProductCard'
import { Link } from "react-router-dom"
import { faChevronLeft } from '@fortawesome/free-solid-svg-icons'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';



const Favorites = () => {

    const favoritesState = useContextProvider().favoritesState
    const logState = useContextProvider().logState
    const setFavoritesState = useContextProvider().setFavoritesState

    useEffect(()=>{
        logState.jwt ? "" : "redirect"
        console.log(favoritesState)
    },[favoritesState])

    // useEffect(()=>{
    //     if  (logState.isLoged && favoritesState.length == 0)  {
    //         console.log("entro")
    //         axios.get(PRODUCTOS_ID + "favoritos/" + logState.user.id).then(res => setFavoritesState(res.data))
    //     }
    // },[])

    return (
        <div>
            <div className="container top-container">
            <div className="top">
                <div className='product-title'>
                    <h2 className="section-title">
                    Favoritos
                    </h2>
                </div>
                <div className="back-home">
                    <Link to={"/"} >
                        <FontAwesomeIcon className = "chevronLeft" icon={faChevronLeft} />
                    </Link>
                </div>
            </div>
            </div>
            <div className="recommendations container">
            
                <div className='ProductList'>
                    {favoritesState.map( product=>
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
                                        product = {product}
                                    />                        
                    )}
                </div>
            </div>
        </div>

    )
}

export default Favorites