import React from 'react'

const CreatePolitics = (props) => {
  return (
    <div>
         <h3>Políticas del producto</h3>
                            <div className="details-container">
                                <div className="detail">
                                    <label htmlFor="rules">Normas de la casa</label>
                                    <p>Descripción</p>
                                    <textarea
                                    id="rules"
                                    name="rules"
                                    placeholder="Escriba aquí..."
                                    onChange={ e => props.setDescripcionNormas(e.target.value)}
                                    />
                                </div>
                                <div className="detail">
                                    <label htmlFor="health">Salud y seguridad</label>
                                    <p>Descripción</p>
                                    <textarea
                                    id="health"
                                    name="health"
                                    placeholder="Escriba aquí..."
                                    onChange={ e => props.setDescripcionSeguridad(e.target.value)}
                                    />
                                </div>
                                <div className="detail">
                                    <label htmlFor="cancellation">Política de cancelación</label>
                                    <p>Descripción</p>
                                    <textarea
                                    id="cancellation"
                                    name="cancellation"
                                    placeholder="Escriba aquí..."
                                    onChange={ e => {props.setDescripcionPolitica(e.target.value)
                                      console.log(e.target.value);
                                      }}
                                    />
                                </div>
                            </div>
    </div>
  )
}

export default CreatePolitics