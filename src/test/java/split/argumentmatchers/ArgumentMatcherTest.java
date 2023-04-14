package split.argumentmatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArgumentMatcherTest {

    @Mock
    private ArgumentTestFixture argumentTestFixture;

    @DisplayName("인자에 anyString() 을 사용할 경우 어느 int 가 와도 같은 결과로 stubbing 된다.")
    @Test
    void useArgumentMatcherAnyInt() {
        when(argumentTestFixture.parseToEnglish(anyString())).thenReturn("one");

        final String oneResult = argumentTestFixture.parseToEnglish(anyString());
        final String twoResult = argumentTestFixture.parseToEnglish("일");
        final String thirdResult = argumentTestFixture.parseToEnglish("하나");

        verify(argumentTestFixture, times(3)).parseToEnglish(anyString());

        Assertions.assertThat(oneResult).isEqualTo("one");
        Assertions.assertThat(twoResult).isEqualTo("one");
        Assertions.assertThat(thirdResult).isEqualTo("one");
    }

    @DisplayName("인자에 isNull 를 사용할 경우 null 값을 대상으로 stubbing 할 수 있다.")
    @Test
    void useArgumentMatcherIsNull() {
        when(argumentTestFixture.parseToEnglish(isNull())).thenReturn("one");

        final String oneResult = argumentTestFixture.parseToEnglish(isNull());
        final String twoResult = argumentTestFixture.parseToEnglish(null);

        verify(argumentTestFixture, times(2)).parseToEnglish(isNull());

        Assertions.assertThat(oneResult).isEqualTo("one");
        Assertions.assertThat(twoResult).isEqualTo("one");
    }

    @DisplayName("인자에 eq() 를 사용하여 특정값을 정하여 stubbing 할 수 있다.")
    @Test
    void argumentMatcherWithoutDoReturn() {
        when(argumentTestFixture.parseToEnglish(eq("hi"))).thenReturn("one");

        String oneResult = argumentTestFixture.parseToEnglish("hi");
        String twoResult = argumentTestFixture.parseToEnglish(anyString());

        verify(argumentTestFixture, times(2)).parseToEnglish(anyString());
        verify(argumentTestFixture).parseToEnglish("hi");

        Assertions.assertThat(oneResult).isEqualTo("one");
        Assertions.assertThat(twoResult).isNotEqualTo("one");
    }
}
