import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './CreateProductSuccess.css'


const CreateProductSuccess = () => {
    const navigate = useNavigate()
    return (
        <div className='createProductContainer'>
            <div className='card'>
                <div className='svg'>
                    <svg width="85"
                        height="79"
                        viewBox="0 0 78 74"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                    <path
                    d="M78 36.9823L69.3491 27.1534L70.5545 14.1424L57.7555 11.2432L51.0545 0L39 5.16197L26.9455 0L20.2445 11.2432L7.44545 14.107L8.65091 27.118L0 36.9823L8.65091 46.8113L7.44545 59.8576L20.2445 62.7568L26.9455 74L39 68.8027L51.0545 73.9646L57.7555 62.7215L70.5545 59.8223L69.3491 46.8113L78 36.9823ZM31.9091 54.6603L17.7273 40.5179L22.7264 35.5327L31.9091 44.6546L55.2736 21.355L60.2727 26.3755L31.9091 54.6603Z"
                    fill="var(--naranja)"
                    />

                        </svg>
                </div>
                <div className='text'>
                    <p className='message'>Tu propiedad se ha creado con éxito.</p>
                </div>
                <Link to={"/"}>
                    <button className='button_main' >
                        Volver
                    </button>
                </Link>
            </div>
        </div>
    )
}

export default CreateProductSuccess