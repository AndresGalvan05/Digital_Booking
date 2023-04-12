import React from 'react'
import './ReservaConfirm.css'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
faStar,
faLocationDot
} from "@fortawesome/free-solid-svg-icons";
import { convert } from '../../utils/utils';
import Stars from "../stars/Stars"
import { useContextProvider } from '../../context/UserContext'




const ReservaConfirm = (props) => {
  const dateState = useContextProvider().dateState

  return (
      <div className='reserveDetailContainer'>
          <h2>Detalle de la reserva</h2>
          <div className='cardDetailContainer ' >
            <div className='productImageContainer' >
              <img className='productImage'
                src={props.product.categoria.urlImagen}
              />
            </div>

            <div className='cardDetailRigth' >
            
              <div className='categoryTitleContainer' >
                <p className='productCategory' > {props.product.categoria.titulo} </p>
                <h3 className='productTitle' >{props.product.titulo}</h3>
                <Stars avg = {props.product.promedio}  propsId={props.product.id} />
              </div>
         
              <div className='fullAddress' >
                <FontAwesomeIcon icon={faLocationDot} />
                <p>
                {props.product.ciudad.nombre + ","+ props.product.ciudad.pais}
                </p>
              </div>
       
              <div className='containerCheck' >
                <div className='lineBooking' ></div>
                <div className='checkDate' >
                  <p>Check in</p>
                  <p> {convert(dateState.start)} </p>
                </div>
                <div className='lineBooking' ></div>
                <div className='checkDate' >
                  <p>Check out</p>
                  <p> {convert(dateState.end)} </p>
                </div>
                <div className='lineBooking'></div>
              </div>
              <button type='submit' 
                className="button_main"
              >
                Confirmar reserva
              </button>
            </div>
          </div>
        </div>

  )
}

export default ReservaConfirm