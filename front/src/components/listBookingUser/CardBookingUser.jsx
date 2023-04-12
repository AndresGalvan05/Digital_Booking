import React, { useState,useEffect } from 'react'
import './CardBookingUser.css'
import  axios  from 'axios'
import {PRODUCTOS_ID, BORRAR_RESERVA} from '../../staticData/url'
import ProductCard from '../mainPage/productCards/productCard/ProductCard'
import { useContextProvider } from "../../context/UserContext"


const CardBookingUser = (props) => {

    const [productBooking, setProductBooking] = useState(null)
    const logState = useContextProvider().logState

    let id = props.producto.id

    
    const config = {
        headers: {
            "Authorization": `Bearer ${logState.jwt}`,
            'Accept' : 'application/json',
            'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "*"
        }
    }
    useEffect(()=>{
        axios.get(PRODUCTOS_ID+id).then(res =>{
            setProductBooking(res.data);

        }).catch(err => {
          console.log("entro al error" + err)})
    }, [id])

    const deleteBooking = (id_booking) =>{
        
        axios.delete(BORRAR_RESERVA+id_booking, config).then((res) =>{
            console.log(res);
            location.reload()
        }).catch(error =>{
            console.log(error);
        })
    }

  return (
      <div>
          {productBooking ? (
            <div className='booking_single_card_container'>
          <ProductCard      
                                path={productBooking.id}
                                key={productBooking.id}
                                crimg={productBooking.imagenes.length > 0 ? productBooking.imagenes[0].url : ''  }
                                category={productBooking.categoria.titulo}
                                title={productBooking.titulo}
                                location={productBooking.ciudad.nombre}
                                description={productBooking.descripcion}
                                icons={productBooking.caracteristicas}
                                avg={productBooking.promedio}
                                hideFav = {true}
                                direccion = {productBooking.direccion}
                            /> 
        <div className='cardBookingUser'>
        <div className='info-container-booking'>
                    <h2 className='info-booking-title'>Datos de la Reserva</h2>
                    <div className='product-info-booking'>
                        <div className="single-text-container">
                            <h4>Tu fecha inicial: </h4>
                            <p>{props.fechaInicial} </p>
                        </div>
                        <div className="single-text-container">
                            <h4>Tu fecha final:</h4>
                            <p> {props.fechaFinal} </p>
                        </div>
                        <div className="single-text-container">
                            <h4>Hora de llegada</h4>
                            <p> {props.horaReserva} </p>
                        </div>
                    </div>
                        <div className='reservaButtonContainer'>

                        <button onClick={() => deleteBooking(props.id)} className='button_main' >Cancelar Reserva</button>
                        </div>
        </div>
        </div>
    </div>): (<div></div>)}

      </div>
  )
}

export default CardBookingUser