import React from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faChevronLeft } from '@fortawesome/free-solid-svg-icons'
import { Link } from "react-router-dom"
import "./CreateProductHeader.css";

const CreateProductHeader = (props) => {
    return (
        <div className="container top-container">
            <div className="top">
                <div className='product-title'>
                    {/* <h4>Admin</h4> */}
                    <h2>Administración</h2>
                </div>
                <div className="back-home">
                    <Link to={"/"} >
                        <FontAwesomeIcon className = "chevronLeft" icon={faChevronLeft} />
                    </Link>
                </div>
            </div>
        </div>
    )
}

export default CreateProductHeader

