import Home from '../routes/Home'
import Product from '../routes/Product'
import Booking from '../routes/Booking'
import ErrorComponent from '../routes/ErrorComponent'
import RegisterForm from '../components/forms/register/RegisterForm'
import LoginForm from '../components/forms/login/LoginForm'
import BookingSucces from '../components/bookingPage/BookingSucces'
import CreateProductSuccess from '../components/createProductPage/CreateProductSuccess'
import ViewBooking from '../routes/ViewBooking'
import Favorites from '../routes/Favorites'
import ConfirmEmail from '../components/forms/confirmEmail/ConfirmEmail'
import SentEmailConfirm from '../components/forms/confirmEmail/SentEmailConfirm'
import Admin from '../routes/Admin'
import UpdateProduct from '../routes/UpdateProduct'


export const routes = [
    { id: 1, path: "/", Element: Home, title: "Home" },
    { id: 2, path: "/product/:id", Element: Product, title: "Detalle container" },
    { id: 3, path: "/product/:id/booking", Element: Booking, title: "Booking" },
    { id: 4, path: "*", Element: ErrorComponent, title: "Error" },
    { id: 5, path: "register", Element: RegisterForm, title: "Crear cuenta" },
    { id: 6, path: "login", Element: LoginForm, title: "Iniciar sesión" },
    { id: 7, path: "booking/succes", Element : BookingSucces , title : "Booking Succes"},
    { id: 8, path: "/admin/createProduct/success", Element : CreateProductSuccess , title : "Create-product Success"},
    { id: 9, path: "/admin", Element: Admin, title: "Crear Producto" },
    { id: 10, path: "booking/list/",  Element: ViewBooking, title : " Reservas del usuario"},
    { id: 11, path: "/favorites", Element: Favorites, title: "Favoritos" },
    { id: 12, path: "/usuarios/confirmar-cuenta", Element : ConfirmEmail , title: "Confirmar email"},
    { id: 13, path: "/sent-email-confirm", Element : SentEmailConfirm , title: "Envio de correo de verificación"},
    { id: 14, path: "/admin/product-update/:id", Element : UpdateProduct , title : "Update del producto"}
]
