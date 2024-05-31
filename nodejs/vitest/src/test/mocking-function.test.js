import {describe, test, expect, vi} from "vitest";

function sum(a, b) {
    return a + b;
}

describe('sum() mock test', () => {
    test('not mocking, it returns a + b', () => {
        expect(sum(1, 2)).toBe(3);
    });

    test('with mocking, it returns 0', () => {
        const mock = vi.fn().mockImplementation(sum)
        mock.mockImplementationOnce(() => 0);

        expect(mock(1, 2)).toBe(0);
        expect(mock(1, 2)).toBe(3);
    });

    test('with mocking, it returns 0', () => {
        const mock = vi.fn().mockImplementation(sum)
        mock.mockImplementation(() => 0);

        expect(mock(1, 2)).toBe(0);
        expect(mock(1, 2)).toBe(0);
    });
});
