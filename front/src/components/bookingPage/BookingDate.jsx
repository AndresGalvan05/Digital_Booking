import React, { useState } from 'react'
import './BookingDate.css'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
faCircleCheck
} from "@fortawesome/free-solid-svg-icons";



const BookingDate = (props) => {


  return (
   
<div className='reserva-hora-container'>
    <h3>Tu horario de llegada</h3>
    <div className='reserva-hora' >
        <div className='hora'>
          <div className='check'>
              <FontAwesomeIcon icon={faCircleCheck} />
              <p >Tu habitacion va a estar lista</p>
          </div>
        
        <label htmlFor="">Indica tu horario estimado de llegada</label>
          <select defaultValue={""} value={props.value} onChange={ e => props.setValue(e.target.value)} className='reserva-select' name="select" placeholder='Selecciona hora de llegada'>
              <option disabled value="">Selecciona hora de llegada</option>
              <option value="01:00:00">01:00 AM</option>
              <option value="02:00:00">02:00 AM</option>
              <option value="03:00:00">03:00 AM</option>
              <option value="04:00:00">04:00 AM</option>
              <option value="05:00:00">05:00 AM</option>
              <option value="06:00:00">06:00 AM</option>
              <option value="07:00:00">07:00 AM</option>
              <option value="08:00:00">08:00 AM</option>
              <option value="09:00:00">09:00 AM</option>
              <option value="10:00:00">10:00 AM</option>
              <option value="11:00:00">11:00 AM</option>
              <option value="12:00:00">12:00 PM</option>
              <option value="13:00:00">01:00 PM</option>
              <option value="14:00:00">02:00 PM</option>
              <option value="15:00:00">03:00 PM</option>
              <option value="16:00:00">04:00 PM</option>
              <option value="17:00:00">05:00 PM</option>
              <option value="18:00:00">06:00 PM</option>
              <option value="19:00:00">07:00 PM</option>
              <option value="20:00:00">08:00 PM</option>
              <option value="21:00:00">09:00 PM</option>
              <option value="22:00:00">10:00 PM</option>
              <option value="23:00:00">11:00 PM</option>
              <option value="00:00:00">12:00 PM</option>
          </select>
        </div>
    </div>
</div>
  )
}

export default BookingDate