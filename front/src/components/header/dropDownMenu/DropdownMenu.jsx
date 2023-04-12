import { Link, useLocation, useNavigate } from 'react-router-dom'
import './DropdownMenu.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faFacebook, faLinkedin, faTwitter, faInstagram} from '@fortawesome/free-brands-svg-icons';
import {faBars, faTimes} from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState } from 'react';
import { useContextProvider } from '../../../context/UserContext';
import Avatar from '../avatar/Avatar';

const DropdownMenu = () => {

    const[clicked, setClicked] = useState(false);
    const logState  = useContextProvider().logState;
    const dispatchLogState = useContextProvider().dispatchLogState;
    const navigate = useNavigate();

    // const handleClick = () =>{
    //     setClicked(!clicked)
    // }

    const location = useLocation();
    return (
        <div className='nav-header'>
            <div className='menu-header'>
                <FontAwesomeIcon icon={faBars} onClick={() =>setClicked(!clicked) }/>
            </div>
        <div className={`burguer ${clicked ? 'active' : 'inactive'}`}>
            { logState.isLoged ? (
            <>
                <div className='superiorDropdownAvatar'>
                <div className={`closeMenu ${clicked ? 'active':'inactive'}`}>
                <FontAwesomeIcon icon={faTimes}  onClick={() => setClicked(!clicked)}/>
                </div>
                <Avatar logState={logState}/>
                </div>
                <div className='links-burger'>
                <Link to={"/favorites"} className='links-user' onClick={() => setClicked(false)} >Favoritos</Link>
                <Link to={"/booking/list/"} className='links-user' onClick={() => setClicked(false)}>Reservas</Link>
                {logState.user.role.roleName == "ROLE_ADMIN" ? (<Link to={"/admin"} className='links-user' onClick={() => setClicked(false)}>Administrador</Link>):null }
                </div>
                <div className='linkContainer'>
                <button className='logoutLink' onClick={() => { navigate('/'); dispatchLogState({type: 'logOut'})}}>
                ¿Deseas <span> cerrar sesion</span>? 
                </button>
                </div>
                <div className='icons'>
                <FontAwesomeIcon icon={faFacebook} />
                <FontAwesomeIcon icon={faLinkedin} />
                <FontAwesomeIcon icon= {faTwitter}/>
                <FontAwesomeIcon icon= {faInstagram}/>
                </div>
            </>
            ) : (
            <>
                <div className='superiorDropdown'>
                <div className={`closeMenu ${clicked ? 'active':''}`}>
                <FontAwesomeIcon icon={faTimes}  onClick={() => setClicked(!clicked)}/>
                </div>
                <h3 className="menuTitle">Menú</h3>
                </div>
                <div className="links">
                {location.pathname != '/register' ? <Link to={"/register"}>
                <p className='text_button'>Crear cuenta</p>
                </Link> : '' } 
                <hr/>
                {location.pathname != '/login' ? <Link to={"/login"}>
                    <p className='text_button'>Iniciar sesión</p>
                </Link> : '' } 
                </div>
                <div className='icons'>
                <FontAwesomeIcon icon={faFacebook} />
                <FontAwesomeIcon icon={faLinkedin} />
                <FontAwesomeIcon icon= {faTwitter}/>
                <FontAwesomeIcon icon= {faInstagram}/>
                </div>
            </>
            )}
        </div>
        </div>
    )
}
export default DropdownMenu