import  axios  from 'axios'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { BUSCAR_RESERVAS_DEL_USUARIO} from '../staticData/url'
import '../components/listBookingUser/CardBookingUser.css'
import CardBookingUser from '../components/listBookingUser/CardBookingUser'
import { useContextProvider } from '../context/UserContext'

const ViewBooking = () => {
    const [booking, setBooking] = useState([])
    const [title, setTitle] = useState("")
    const logState = useContextProvider().logState
    

    useEffect(()=>{
        axios.get(BUSCAR_RESERVAS_DEL_USUARIO+logState.user.id).then(res =>{
            setBooking(res.data);
            console.log(booking);
            setTitle( booking.length !=0 ? "Tus reservas" : "No se encontro ninguna reserva")
        }).catch(err => {
                    console.log("entro al error" + err)})
    }, [booking])

  return (
    <div className='container-view-booking'>
          <h1> {title} </h1>
          <div className='container-booking-user'>
          {booking.map( (item) =>
            <CardBookingUser
                key = {item.id}
                id= {item.id}
                fechaInicial = {item.fechaInicial}
                fechaFinal = {item.fechaFinal}
                horaReserva = {item.horaReserva}
                producto = {item.producto}
            />
            )}
          </div>
    </div>
  )
}

export default ViewBooking