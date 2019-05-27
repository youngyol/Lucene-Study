package util;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PropertyUtilTest {
    private PropertyUtil propertyUtil;

    @Before
    public void setUp() {
        this.propertyUtil = new PropertyUtil("test");
    }

    @Test
    public void 프로퍼티파일_값_읽기(){
        String expectedValue = "lucene";
        String valueFromProperty = propertyUtil.getValue("test.value");

        assertThat(valueFromProperty).isEqualTo(expectedValue);
    }
}