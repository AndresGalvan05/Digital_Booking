import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./routes/Layout";
import { routes } from "./navigation/Routes";
import './styles/variables.css';
import UserContext from "./context/UserContext";

function App() {
    return (
        <BrowserRouter>
            <UserContext>
                <Routes>
                    <Route element={<Layout />}>
                    {routes.map(({ id, path, Element }) => (<Route key={id} path={path} element={<Element/>} />))}
                    </Route>
                </Routes>
            </UserContext>
        </BrowserRouter>
    )
}

export default App
