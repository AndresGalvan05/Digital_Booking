import * as yup from "yup"

export const loginScheme = yup.object().shape({
    email: yup.string().email("Debe ingresar un email válido").required("Este campo es obligatorio"),
    password: yup.string().required("Este campo es obligatorio")
})