import { render, screen } from "@testing-library/react";
import axios from "axios";
import { BrowserRouter } from "react-router-dom";
import ContextProvider from "../Components/utils/global.context";
import Card from "../Components/Card";

describe('Card component', () => {
    test('Verifica que Card reciba las props', async () => {
        await axios.get('https://jsonplaceholder.typicode.com/users').then(
            res => {
                render(
                    <BrowserRouter >
                        <ContextProvider >
                            <Card key={res.data[0].id} id={res.data[0].id} name={res.data[0].name} username={res.data[0].username} /> 
                        </ContextProvider>
                    </BrowserRouter>
                    )
                    const nameUser1 = screen.getByText(res.data[0].name)
                    expect(nameUser1.textContent).toBe("Leanne Graham")
                    expect(nameUser1).toBeInTheDocument();
            } 
        )
    })
})