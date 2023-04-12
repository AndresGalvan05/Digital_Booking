import React from 'react'
import './ProductDescription.css'

const ProductDescription = (props) => {

    return (
        <div className='container section-description-product'>
            <h3 className='titleDetailDescription'> Alójate en el corazón de {props.city} </h3>
            <p className='descriptionProduct'>{props.description}</p>
        </div>
    )
}

export default ProductDescription