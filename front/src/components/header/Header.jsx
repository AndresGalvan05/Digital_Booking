
import React, { useState } from 'react'
import { Link, useLocation, useNavigate, useParams } from 'react-router-dom'
import logo from '../../assets/img/logo1.png'
import "./Header.css"
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import { faSortDown, faHeart} from '@fortawesome/free-solid-svg-icons';
import DropdownMenu from './dropDownMenu/DropdownMenu';
import Avatar from './avatar/Avatar';
import { useContextProvider } from '../../context/UserContext';
const Header= () => {
    const [mostrar, setMostrar] = useState(false);
    const logState  = useContextProvider().logState;
    const dispatchLogState = useContextProvider().dispatchLogState;
    const navigate = useNavigate()
    const location = useLocation();

    const menu = () =>{      
      setMostrar(!mostrar)
  }

  return (
    <header className='header container'>
      <div className='leftInfo'>
        <Link to={"/"}  onClick={() => { useParams("/") ?  window.location.reload() : "/"}} className='logoTitle'>
          <img src={ logo } alt="Logo" className="logo"/>
          <h1 className='mainTitle'>Sentite como en tu hogar</h1>
        </Link>
      </div>
      <div className="user">
        {  logState.isLoged ? (
          <>
          <Avatar logState={logState}/>
            <div className='display-menu-wrapper'>    
            <FontAwesomeIcon icon={faSortDown} onClick={menu}/>
            <div className={'menuMostrar'}>
                <Link to={"/favorites"} className={"menuMostrar-link"}><p className='text_button'>Favoritos</p></Link>
                <Link to={"/booking/list/"} className={"menuMostrar-link"}> <p className='text_button'>Reservas</p> </Link>
                <Link to={"/"} onClick={() => {dispatchLogState({type: 'logOut'})}} className={"menuMostrar-link"}><p className='text_button'>Log Out</p> </Link>
                
            </div>
            </div>

          </>
        ) :
          (<>
            {location.pathname != '/register' ? <Link className="login-button" to={"/register"}>
            <p className='text_button'>Crear cuenta</p>
          </Link> : '' } 
          {location.pathname != '/login' ? <Link className="login-button" to={"/login"}>
            <p className='text_button'>Iniciar sesión</p>
          </Link> : '' } 
        </>)
        }
      </div>
      <DropdownMenu/>
    </header>
  )
}

export default Header