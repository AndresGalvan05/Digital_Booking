import React from 'react'
import './PersonalData.css'
import { useContextProvider } from '../../context/UserContext'



const PersonalData = () => {

    const logState = useContextProvider().logState
  return (
    <div className='formData'> 
    <div className='itemGroup'> 
        <label htmlFor="name" className='form_label'>Nombre</label>
        <div className='form_group-input-data'>
        <input
            className="inputsDate"
            label="Name"
            type="text"
            id="name"
            name="name"
            disabled 
            defaultValue={logState.user.name}
        />
        </div>
        </div>
        <div className="itemGroup" > 
        <label htmlFor="lastname" className='form_label'>Apellido</label>
            <div className='form_group-input-data'>
            <input
                className="inputsDate"
                label="lastname"
                type="text"
                id="lastname"
                name="lastname"
                disabled
                defaultValue={logState.user.lastName}
                
            />
            </div>
        </div>
        <div className="itemGroup" > 
        <label htmlFor="email" className='form_label'>Correo</label>
        <div className='form_group-input-data'>
        <input
            className="inputsDate"
            label="Email"
            type="email"
            id="email"
            name="email"
            disabled
            defaultValue={logState.user.email}
        />
        </div>
        </div>
        <div className="itemGroup" > 
        <label htmlFor="city" className='form_label'>Ciudad</label>
        <div className='form_group-input-data'>
        <input
            className="inputs"
            label="city"
            type="text"
            id="city"
            name="city" 
            defaultValue={logState.user.city.nombre}
        />
        </div>
        </div>
    </div>
  )
}

export default PersonalData