import matchers from "@testing-library/jest-dom/matchers";
import { expect } from "vitest";

expect.extend(matchers);

describe("Dummy test, return a string", () => {
    const value = "hello";
    test("function return a string", () => {
        const result = value;
        expect(typeof result).toBe('string');
    })
})