import React, { useContext, useState } from "react";
import "../form.css";
import { Await, Link, useNavigate } from "react-router-dom";
import { useFormik } from "formik";
import { userScheme } from "../../../validations/UserValidation";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash} from "@fortawesome/free-solid-svg-icons";
import { AGREGAR_USUARIO } from "../../../staticData/url";

import axios, { Axios } from "axios";
const RegisterForm = () => {
    const [visible, setVisible] = useState(false);
    const [viewPass, setViewPass] = useState(false)
    const navigate = useNavigate();
    
    const onSubmit = async (values, actions) => {
        await new Promise((resolve) => setTimeout(resolve, 1000));
        const data = {
            nombre: values.name,
            apellido: values.lastname,
            email: values.email,
            password: values.password,
            ciudad:{id:1},
            role:{id:3}
        }
        axios.post(AGREGAR_USUARIO, data)
        .then((res) =>{
            if(res.status === 200 || res.status === 201){
            navigate("/sent-email-confirm")
            }
            
        }) 
        .catch(err =>{
            console.log(err);
        })
    };

    const {
        values,
        errors,
        touched,
        isSubmitting,
        handleBlur,
        handleChange,
        handleSubmit,
    } = useFormik({
        initialValues: {
        name: "",
        lastname: "",
        email: "",
        password: "",
        confirmPassword: "",
        },
        validationSchema: userScheme,
        onSubmit,
    });

    const checkVisible = () => {
        setVisible(!visible);
        setViewPass(!viewPass)
    };

    return (
    <div className="containerForms">
        <form action="" className="form" onSubmit={handleSubmit}>
            <h1 className="titleForm">Crear cuenta</h1>
            <div>
                <div className="inputGroup">
                    <div className="form_group-input">
                        <label htmlfor="name" className="form_label">
                            {" "}
                            Nombre
                        </label>
                        <input
                            className={`${"inputs"} ${
                            errors.name && touched.name ? "inputError" : ""
                            } `}
                            type="text"
                            id="name"
                            name="name"
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {errors.name && touched.name && (
                        <p className="error">{errors.name}</p>
                        )}
                    </div>
                    <div className="form_group-input">
                        <label htlmFor="lastname" className="form_label">
                            Apellido
                        </label>
                        <input
                            className={`${"inputs"} ${
                            errors.lastname && touched.lastname ? "inputError" : ""
                            } `}
                            type="text"
                            id="lastname"
                            name="lastname"
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {errors.lastname && touched.lastname && (
                            <p className="error">{errors.lastname}</p>
                        )}
                    </div>
                </div>
            </div>
            <div className="inputContainer2">
                <label htmlfor="email" className="form_label">
                    Email
                </label>
                <div className="form_group-input">
                    <input
                    className={`${"inputs"} ${
                        errors.email && touched.email ? "inputError" : ""
                    } `}
                    label="Email"
                    type="email"
                    id="email"
                    name="email"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    />
                </div>
                {errors.email && touched.email && (
                    <p className="error">{errors.email}</p>
                )}

                <label htmlfor="Contraseña" className="form_label">
                    Contraseña
                </label>
                <div className="form_group-input">
                    <input
                    className={`${"inputs"} ${
                        errors.password && touched.password ? "inputError" : ""
                    } `}
                    label="Contraseña"
                    id="password"
                    name="password"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    type={visible ? "text" : "password"}
                    />
                    <div id="eye-register">
                    {viewPass ? <FontAwesomeIcon className="iconoFaEye" icon={faEye} onClick={checkVisible}/>: <FontAwesomeIcon className="iconoFaEye" icon={faEyeSlash} onClick={checkVisible}/>}
                    </div>
                </div>
                {errors.password && touched.password && (
                    <p className="error">{errors.password}</p>
                )}

                <label htmlfor="Confrimar contraseña" className="form_label">
                    {" "}
                    Confirmar Contraseña
                </label>
                <div className="form_group-input">
                    <input
                    className={`${"inputs"} ${
                        errors.confirmPassword && touched.confirmPassword
                        ? "inputError"
                        : ""
                    } `}
                    label="Confirmar contraseña"
                    type={'password'}
                    id="confirmPassword"
                    name="confirmPassword"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    />
                </div>
                {errors.confirmPassword && touched.confirmPassword && (
                    <p className="error">{errors.confirmPassword}</p>
                )}
            </div>

            <button
            type="submit"
            className="buttonForm"
            onClick={handleSubmit}
            disabled={isSubmitting}
            >
            Crear cuenta
            </button>
            <p className="link">¿Ya tienes una cuenta?
                <Link className="linkAction" to="/login">
                    Iniciar sesión
                </Link>
            </p>
        </form>
    </div>
    );
};

export default RegisterForm;
