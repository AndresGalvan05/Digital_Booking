import React from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSquarePlus} from '@fortawesome/free-solid-svg-icons';

const CreateImages = (props) => {


  const handleChange =(string,index) =>{
    props.setImagenActual({titulo : props.selectedOptionImages[index].titulo, url: string}, index)
  }


  return (
    <div className='container-atribute'> 
                            <div className="select-box-atribute">
                            <label htmlFor="category">Link</label>
                                        <input className="dropdown-header-atribute"
                                            type="text"
                                            id="img"
                                            name="img"
                                            placeholder='Inserte una url'
                                            onChange={ (e) => handleChange(e.target.value, props.index)}
                                            onPaste={(e) => handleChange(e.target.value, props.index)}
                                        />
                                        <div>
                            <FontAwesomeIcon onClick={props.agregarImagen} className="faSquarePlus" icon={faSquarePlus} />
                        </div>
                            </div>
    </div>
  )
}

export default CreateImages