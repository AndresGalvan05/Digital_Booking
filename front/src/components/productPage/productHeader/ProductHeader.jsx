import React from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faChevronLeft } from '@fortawesome/free-solid-svg-icons'
import { Link } from "react-router-dom"
import "./ProductHeader.css";

const ProductHeader = (props) => {
    return (
        <div className="container top-container">
            <div className="top">
                <div className='product-title' key={props.id}>
                    <h4>{props.category.titulo}</h4>
                    <h2>{props.title}</h2>
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

export default ProductHeader