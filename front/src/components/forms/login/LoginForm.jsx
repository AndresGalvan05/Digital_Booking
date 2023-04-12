import { useState, useEffect, useContext} from "react";
import '../form.css';
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useFormik } from 'formik'
import { loginScheme } from "../../../validations/LoginValidation";
import { testUser } from "./../TestUser"
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faEye, faEyeSlash} from '@fortawesome/free-solid-svg-icons';
import axios from "axios";
import { useContextProvider } from '../../../context/UserContext';
import { AGREGAR_USUARIO_LOGIN, BUSCAR_USUARIO_POR_EMAIL} from "../../../staticData/url";

const LoginForm = () => {
    const [visible, setVisible] = useState(false);
    const [message, setMessage] = useState("");
    const [viewPass, setViewPass] = useState(false)
    const loginFailed = document.querySelector("#errorLogin");
    
    const navigate = useNavigate();
    const location = useLocation();

    const { bookingMessage } = location.state ? location.state : false;

    const logState  = useContextProvider().logState;
    const dispatchLogState = useContextProvider().dispatchLogState;

    /** MANEJO DEL LOGIN **/

    useEffect(() => {
        if (logState.isLoged) {
            navigate(-1);
        }
    }, [navigate, logState]);


    function parseJwt (token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    
        return JSON.parse(jsonPayload);
    }

    const onSubmit = async (values, actions) => {
        await new Promise((resolve) => setTimeout(resolve, 1000));
        
        const data={
            email: values.email,
            password: values.password
        }
        axios.post(AGREGAR_USUARIO_LOGIN, data)
        .then((res) => {
            const jwt = res.data.jwt;
            // const separeJwt = jwt.substring(6)
            localStorage.setItem('jwt', jwt)
            const payload = parseJwt(jwt)
            axios.get(BUSCAR_USUARIO_POR_EMAIL+payload.sub).then(res =>{
                const user = {
                    id: res.data.id,
                    name: res.data.nombre,
                    lastName: res.data.apellido,
                    email: res.data.email,
                    city: res.data.ciudad,
                    role: res.data.role
                }
                localStorage.setItem('user', JSON.stringify(user))
                dispatchLogState({type: 'logIn'})
            })
        })
        .catch((err) =>{
            loginFailed.innerText = "Por favor vuelva a intentarlo, sus credenciales son inválidas"
            console.log(err)})
        }



    /** MANEJO DE ERRORES **/
    useEffect(() => {
        if (bookingMessage) {
        setMessage(bookingMessage);
        }
        }, [navigate]);

    const { values, errors, touched, isSubmitting, handleBlur, handleChange, handleSubmit } = useFormik({
        initialValues: {
            email: '',
            password: '',
        },
        validationSchema: loginScheme,
        onSubmit,
    })

    const checkVisible = () =>{
        setVisible(!visible)
        setViewPass(!viewPass)
    }

    return (
        <div className="containerForms">
            
            <form action="" className="form" onSubmit={handleSubmit}>
                <div className={message ? "mje": ""}>
                    {message ? <span>!</span> : ""}
                    <p >{message}</p>
                </div>
                <h1 className="titleForm">Iniciar Sesión</h1>
                <div>
                    <div className="itemGroup" > 
                        <label htmlFor="email" className='form_label'>Email</label>
                        <div className='form_group-input'>
                            <input
                                className={`${"inputs"} ${errors.email && touched.email ? "inputError" : ''
                            } `}
                                label="Email"
                                type="email"
                                id="email"
                                name="email"
                                onChange={handleChange}
                                onBlur={handleBlur} 
                                error = {errors.email && touched.email}
                            />
                        </div>
                        {errors.email && touched.email && <p className="error">{errors.email}</p>}
                    </div>
                    <div className="itemGroup">
                        <label htmlFor="Contraseña"className='form_label'>Contraseña</label>
                        <div className='form_group-input'>
                            <input 
                            className={`${"inputs"} ${errors.password && touched.password ? "inputError" : ''} `}
                            label="Contraseña"
                            type={visible ? 'text' : 'password'}
                            id="password"
                            name="password"
                            onChange={handleChange}
                            onBlur={handleBlur} 
                            error = {errors.password && touched.password}
                            />
                            <div id="eye-login">
                                {viewPass ? <FontAwesomeIcon className="iconoFaEye" icon={faEye} onClick={checkVisible}/>: <FontAwesomeIcon className="iconoFaEye" icon={faEyeSlash} onClick={checkVisible}/>}
                            </div>
                        </div>
                        {errors.password && touched.password && <p className="error">{errors.password}</p>}
                    </div>
                    <div className="sectionButton">
                     <button type="submit" className="buttonForm" onClick={handleSubmit} disabled={isSubmitting}>
                         Ingresar
                     </button>
                    <p className="link">
                        ¿Aún no tienes una cuenta? 
                        <Link className="linkAction" to="/register">
                        Registrate 
                        </Link>
                    </p>
                    
                    </div>
                </div>
            </form>
            <div className="loginFailed" id="errorLogin">
            </div>
        </div>
    );
};

export default LoginForm;
