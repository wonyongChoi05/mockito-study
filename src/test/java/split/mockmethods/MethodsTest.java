package split.mockmethods;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.TooFewActualInvocations;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Mock 메서드 테스트")
@ExtendWith(MockitoExtension.class)
public class MethodsTest {

    @DisplayName("Mock 의 이름을 커스텀하면 오류 메시지에 해당이름이 사용된다.")
    @Test
    void mockWithName() {
        final MockList mockedList = mock(MockList.class, "customName");

        when(mockedList.add(anyString())).thenReturn(false);
        mockedList.add(anyString());

        Assertions.assertThatThrownBy(() ->
                verify(mockedList, times(2)).add(anyString()))
            .isInstanceOf(TooFewActualInvocations.class)
            .hasMessageContaining("customName.add");
    }

    @DisplayName("Answer 인터페이스를 사용하여 when,thenReturn 없이 스터빙이 가능하다 ( false )")
    @Test
    void answerFalse() {
        final MockList mockedList = mock(MockList.class, new FalseAnswerFixture());
        final boolean addResult = mockedList.add(anyString());
        verify(mockedList).add(anyString());
        Assertions.assertThat(addResult).isFalse();
    }

    @DisplayName("Answer 인터페이스를 사용하여 when,thenReturn 없이 스터빙이 가능하다 ( true )")
    @Test
    void answerTrue() {
        final MockList mockedList = mock(MockList.class, new TrueAnswerFixture());
        final boolean addResult = mockedList.add(anyString());
        verify(mockedList).add(anyString());
        Assertions.assertThat(addResult).isTrue();
    }

    @DisplayName("MockSettings 을 사용하여 스터빙이 가능하다 ( false )")
    @Test
    void settingFalse() {
        final MockSettings customSettings = Mockito.withSettings().defaultAnswer(new FalseAnswerFixture());
        final MockList mockedList = mock(MockList.class, customSettings);

        boolean addResult = mockedList.add(anyString());
        verify(mockedList).add(anyString());

        Assertions.assertThat(addResult).isFalse();
    }

    @DisplayName("MockSettings 을 사용하여 스터빙이 가능하다 ( true )")
    @Test
    void settingTrue() {
        final MockSettings customSettings = Mockito.withSettings().defaultAnswer(new TrueAnswerFixture());
        final MockList mockedList = mock(MockList.class, customSettings);

        boolean addResult = mockedList.add(anyString());
        verify(mockedList).add(anyString());

        Assertions.assertThat(addResult).isTrue();
    }
}
