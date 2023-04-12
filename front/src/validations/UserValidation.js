import * as yup from "yup"

const passwordRules = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
// min 5 characters, 1 upper case letter, 1 lower case letter, 1 numeric digit.

export const userScheme = yup.object().shape({
    name: yup.string().required("Este campo es obligatorio"),
    lastname: yup.string().required("Este campo es obligatorio"),
    email: yup.string().email("Debe ingresar un email válido").required("Este campo es obligatorio"),
    password: yup
    .string()
    .matches(passwordRules, { message: "Minimo 6 caracteres, una mayuscula y un numero" })
    .required("Este campo es obligatorio"),
    confirmPassword: yup
    .string()
    .oneOf([yup.ref("password"), null], "Las contraseñas deben coincidir")
    .required("Este campo es obligatorio"),
})