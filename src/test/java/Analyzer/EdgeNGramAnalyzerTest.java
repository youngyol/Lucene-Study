package Analyzer;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EdgeNGramAnalyzerTest implements AnalyzerTest {

    @Test
    public void 띄어쓰기_없는_한단어() throws IOException {
        // given
        String testText = "맥북프로";
        List<String> expectedResult = Arrays.asList("맥","맥북","맥북프","맥북프로");

        // when
        List result = analyze(testText);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void 띄어쓰기_존재하는_구문() throws IOException {
        // given
        String testText = "the sky is blue";
        List<String> expectedResult = Arrays.asList("t","th","the","s","sk","sky","i","is","b","bl","blu","blue");

        // when
        List result = analyze(testText);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void 대문자는_소문자로_변환() throws IOException {
        // given
        String testText = "APPLE";
        List<String> expectedResult = Arrays.asList("a","ap","app","appl","apple");

        // when
        List result = analyze(testText);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void 특수문자는_제외() throws IOException {
        // given
        String testText ="사과,배";
        List<String> expectedResult = Arrays.asList("사","사과","배");

        // when
        List result = analyze(testText);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }
}