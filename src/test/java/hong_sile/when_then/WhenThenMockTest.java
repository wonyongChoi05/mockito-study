package hong_sile.when_then;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import hong_sile.exception.CustomException;
import java.security.SecureRandom;
import java.util.AbstractList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Mock Object When Then Test")
@ExtendWith(MockitoExtension.class)
public class WhenThenMockTest {

    private static final int STUB_RETURN_VALUE = 10;
    private static final int CAPTOR_ARGUMENT_VALUE = 5;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Test
    @DisplayName("반환값이 존재하는 메서드를 stubbing - when_thenReturn")
    void whenConfigNonVoidReturnMethodStub1() {
        List<Integer> listMock = mock(List.class);
        when(listMock.get(anyInt())).thenReturn(STUB_RETURN_VALUE);

        final Integer getValue = listMock.get(SECURE_RANDOM.nextInt());
        assertThat(getValue)
                .isEqualTo(STUB_RETURN_VALUE);
    }

    @Test
    @DisplayName("반환값이 존재하지 않는 메서드를 stubbing - doReturn_when")
    void whenConfigNonVoidReturnMethodStub2() {
        List<Integer> listMock = mock(List.class);
        doReturn(STUB_RETURN_VALUE).when(listMock).get(anyInt());

        final Integer getValue = listMock.get(SECURE_RANDOM.nextInt());
        assertThat(getValue)
                .isEqualTo(STUB_RETURN_VALUE);
    }

    /*
    void add(int index, E element)
     */
    @Test
    @DisplayName("반환값이 존재하지 않는 메서드를 stubbing - doNothing_when")
    void whenConfigVoidReturnMethodStub() {
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        List<Integer> listMock = mock(List.class);

        doNothing().when(listMock).add(anyInt(), captor.capture());

        listMock.add(SECURE_RANDOM.nextInt(), CAPTOR_ARGUMENT_VALUE);

        assertThat(captor.getValue())
                .isEqualTo(CAPTOR_ARGUMENT_VALUE);
    }

    @Test
    @DisplayName("반환값이 존재하는 메서드에 여러개의 반환값을 stubbing - chaining")
    void whenConfigNonVoidReturnMethodStubMultiValue1() {
        List<Integer> listMock = mock(List.class);
        when(listMock.get(anyInt()))
                .thenReturn(STUB_RETURN_VALUE)
                .thenThrow(CustomException.class);

        assertThat(listMock.get(SECURE_RANDOM.nextInt()))
                .isEqualTo(STUB_RETURN_VALUE);
        assertThatThrownBy(() -> listMock.get(SECURE_RANDOM.nextInt()))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("반환값이 존재하는 메서드에 여러개의 반환값을 stubbing - chaining 설정값보다 더 많이 호출할 때")
    void whenConfigNonVoidReturnMethodStubMultiValue2() {
        List<Integer> listMock = mock(List.class);
        when(listMock.get(anyInt()))
                .thenReturn(STUB_RETURN_VALUE)
                .thenThrow(CustomException.class);

        assertThat(listMock.get(SECURE_RANDOM.nextInt()))
                .isEqualTo(STUB_RETURN_VALUE);
        assertThatThrownBy(() -> listMock.get(SECURE_RANDOM.nextInt()))
                .isInstanceOf(CustomException.class);
        assertThatThrownBy(() -> listMock.get(SECURE_RANDOM.nextInt()))
                .isInstanceOf(CustomException.class);
        assertThatThrownBy(() -> listMock.get(SECURE_RANDOM.nextInt()))
                .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("Mock 객체의 실제 메서드 호출")
    void whenConfigCallRealMethod() {
        final TestList listMock = mock(TestList.class);

        when(listMock.size()).thenCallRealMethod();

        assertThat(listMock.size())
                .isEqualTo(TestList.CUSTOM_SIZE);
    }

    @Test
    @DisplayName("메서드에 Custom Answer 지정")
    void whenConfigMethodCustomAnswer() {
        final int randomArgument = SECURE_RANDOM.nextInt();
        List<Integer> listMock = mock(List.class);
        doAnswer(invocation -> invocation.getArgument(0)).when(listMock).get(anyInt());

        final Integer returnValue = listMock.get(randomArgument);
        assertThat(returnValue)
                .isEqualTo(randomArgument);
    }

    static class TestList extends AbstractList<Integer> {

        public static final int CUSTOM_SIZE = 20;

        @Override
        public Integer get(final int index) {
            return -1;
        }

        @Override
        public int size() {
            return CUSTOM_SIZE;
        }
    }
}
