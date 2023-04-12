import { describe, it } from "vitest"
import { render, screen } from "@testing-library/react"
import App from "../App"

describe("App", () => {
    it("renders a conditioned string", () => {
        render(<App isMale={trure} />)
        
        expect(
            screen.getByRole("heading", {
                level: 1
            })
        ).toHaveTextContent("Hola señor")
    })
})