import { fireEvent, render, screen } from '@testing-library/react'
import LoginForm from '../components/forms/login'

describe('Form component', () => {
    let inputName;
    let inputEmail;
    let button;
    beforeEach(() => {
        render(<LoginForm />);
        inputEmail = screen.getByLabelText('Email');
        inputPassword = screen.getByLabelText('Contraseña');
        button = screen.getByRole('button', { name: 'Enviar' })
    })

    test('Verifica que el valor del campo "Email" cambie tras el input del usuario', async () => {
        fireEvent.change(inputEmail, {
            target: { value: 'user@prueba.com' }
        })
        expect(inputEmail.value).toBe('user@prueba.com')
    })

    test('Verifica que el formulario envíe la información de los inputs', async () => {
        fireEvent.change(inputEmail, {
            target: { value: 'user@prueba.com' }
        })
        fireEvent.change(inputPassword, {
            target: { value: 'Pass123' }
        })
        fireEvent.click(button)

        const formRes = await screen.findByText(/Hola/i)
        expect(formRes).toBeInTheDocument()
    })
})