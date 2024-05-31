import {expect, test, describe} from 'vitest'

describe('Intl.Segmenter', () => {
    test('영어 테스트', () => {
        const segmenter = new Intl.Segmenter('en', {granularity: 'word'})
        const segments = segmenter.segment('Hello, World!')
        const result = Array.from(segments, ({segment}) => segment)

        expect(result).toEqual(['Hello', ',', ' ', 'World', '!'])
    });

    test('한글 테스트', () => {
        const segmenter = new Intl.Segmenter('ko', {granularity: 'word'})
        const segments = segmenter.segment('다람쥐 헌 쳇바퀴에 타고파')
        const result = Array.from(segments, ({segment}) => segment)

        expect(result).toEqual(['다람쥐', ' ', '헌', ' ', '쳇바퀴에', ' ', '타고파']);
    });

});
