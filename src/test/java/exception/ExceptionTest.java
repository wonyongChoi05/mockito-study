package exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Exception Mocking Test")
@ExtendWith(MockitoExtension.class)
public class ExceptionTest {

    private static final String EXCEPTION_MESSAGE = "예외 발생";

    @DisplayName("반환값이 존재하는 메서드를 Exception Stubbing 할 때 - class")
    @Test
    void whenConfigNonVoidReturnMethodToThrowEx_thenExIsThrown() {
        List<String> strList = mock(List.class);
        when(strList.size()).thenThrow(CustomException.class);
        assertThatThrownBy(strList::size)
                .isInstanceOf(CustomException.class);
    }

    @DisplayName("반환값이 존재하지 않는 메서드를 Exception Stubbing 할 때 - class")
    @Test
    void whenConfigVoidReturnMethodToThrowEx_thenExIsThrown() {
        List<String> strList = mock(List.class);
        doThrow(CustomException.class).when(strList)
                .clear();
        assertThatThrownBy(strList::clear)
                .isInstanceOf(CustomException.class);
    }

    @DisplayName("반환값이 존재하는 메서드를 Exception Stubbing 할 때 - object")
    @Test
    void whenConfigNonVoidReturnMethodToThrowExWithNewExObj_thenExIsThrown() {
        List<String> strList = mock(List.class);
        when(strList.size()).thenThrow(new CustomException(EXCEPTION_MESSAGE));
        assertThatThrownBy(strList::size)
                .isInstanceOf(CustomException.class)
                .hasMessage(EXCEPTION_MESSAGE);
    }

    @DisplayName("반환값이 존재하지 않는 메서드를 Exception Stubbing 할 때 - object")
    @Test
    void whenConfigVoidReturnMethodToThrowExWithNewExObj_thenExIsThrown() {
        List<String> strList = mock(List.class);
        doThrow(new CustomException(EXCEPTION_MESSAGE)).when(strList)
                .clear();
        assertThatThrownBy(strList::clear)
                .isInstanceOf(CustomException.class)
                .hasMessage(EXCEPTION_MESSAGE);
    }
}
