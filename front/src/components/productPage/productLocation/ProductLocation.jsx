import React from 'react'
import "./ProductLocation.css";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faLocationDot, faStar} from '@fortawesome/free-solid-svg-icons'
import Stars from '../../stars/Stars';


const values = ['Regular','Regular', 'Bueno', 'Muy Bueno', 'Excelente']

const ProductLocation = (props) => {
    return (
        <div className='container detalle-location' >
            <div className='location-icon'>
                <FontAwesomeIcon className='location-faicon' icon={faLocationDot} />
                {props.location.nombre+" ,"+ props.direccion + ", " + props.location.pais}
            </div>
            <div className=''>
                <div className='info-calification-location'>
                    <div className='puntuacion'>
                        <p>{values[props.calification - 1]}</p>
                        <div className='star_category_container'>
                            <h2 className='category'>{props.categoria}</h2>
                            <Stars avg={props.calification} prodId={props.prodId}></Stars>
                        </div>
                    </div>
                    <div className='calification-location'> {props.calification*2} </div>
                </div>
            </div>
        </div>
    )
}

export default ProductLocation