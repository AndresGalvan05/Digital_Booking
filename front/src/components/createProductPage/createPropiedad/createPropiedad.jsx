import React, {useState}from 'react'
import './createPropiedad.css'

const CreatePropiedad = (props) => {

    const [open, setOpen] = useState(true)
    const [openCity, setOpenCity] =useState(true)
    const handleChange = (option) => {
        setOpen(false)
        props.setSelectedOption(option);
    }  
    
    const handleChangeCity = (option) => {
        setOpenCity(false)
        props.setSelectedOptionCity(option);
    } 

    
  return (
    <div className="form-container" id='form-propiedades'>
    <div className="create-product-form-row">
       <div className="field">
            <label htmlFor="name">Nombre de la propiedad</label>
           <input
               id="name"
               type="text"
               name="name"
               onChange={ e => props.setTitulo(e.target.value) }
           />
       </div>

       <div className='field'>

       <label htmlFor="address">Dirección</label>
           <input
               id="address"
               type="text"
               name="address"
               onChange={ e => props.setDireccion(e.target.value) }
           />
           
       </div>
   </div>


   <div className="create-product-form-row">
       <div className="field">
            <label htmlFor="latitud">Latitud</label>
           <input
               id="latitud"
               type="text"
               name="latitud"
               onChange={ e => props.setLatitud(e.target.value) }
           />
       </div>

       <div className='field'>

       <label htmlFor="long">Longitud</label>
           <input
               id="long"
               type="text"
               name="long"
               onChange={ e => props.setLongitud(e.target.value) }
           />
           
       </div>
   </div>




   <div className="create-product-form-row">
       <div className="field">
       <label htmlFor="category">Categoria</label>
        <div className='select-box-categoria'>
            <div className='dropdown-header-categoria' onClick={() =>setOpen(!open)}>
                <div>
                    <p> {props.selectedOption.titulo} </p>
                </div>
            </div>

            {open ? <ul className='dropdown-list-categoria'> 
                {props.categorias.map( (item) =>{
                    return (
                        <li className='option-content' onClick={() => {handleChange(item)}} key= {item.id}  >
                            <div className='option-text'>
                                <p>{item.titulo} </p>
                            </div>
                        </li>

                    )
                } )}
            </ul> : null }
        </div>
       </div>

<div className='field'>
        <label htmlFor="category">Ciudades</label>
<div className='select-box-propiedad'>
            <div className='dropdown-header-propiedad' onClick={() =>setOpenCity(!openCity)}>
                <div>
                    <p> {props.selectedOptionCity.nombre} {props.selectedOptionCity.pais} </p>
                </div>
            </div>

            {openCity ? <ul className='dropdown-list-propiedad'> 
                {props.ciudades.map( (item) =>{
                    return (
                        <li className='option-content' onClick={() => {handleChangeCity(item)}} key= {item.id}  >
                            <div className='option-text'>
                                <p>{item.nombre}, {item.pais} </p>
                            </div>
                        </li>

                    )
                } )}
            </ul> : null }
        </div>
</div>
       
       
   </div>
   
   <div className="field description">
       <label htmlFor="description">Descripción</label>
       <textarea
       id="description"
       name="description"
       placeholder="Escribir aquí..."
       onChange={ e => props.setDescripcion(e.target.value) }
       />
   </div>
   
</div>
  )
}

export default CreatePropiedad