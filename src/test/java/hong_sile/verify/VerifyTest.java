package hong_sile.verify;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Mock Object Verify Test")
@ExtendWith(MockitoExtension.class)
public class VerifyTest {

    @Test
    @DisplayName("메서드가 실행되었는지 검증 - verfiy(mock).method")
    void verifySimpleInvokeMock() {
        List<Integer> integerList = mock(List.class);
        integerList.size();
        verify(integerList).size();
    }

    @Test
    @DisplayName("메서드가 몇번 실행되었는지 검증하는 기능 - verfiy(mock, time).method")
    void verifyInvokeNTimesMock() {
        List<Integer> integerList = mock(List.class);

        integerList.size();
        integerList.size();

        verify(integerList, times(2)).size();
    }

    @Test
    @DisplayName("메서드가 실행이 안 되었는지 검증하는 기능 - verfiyNoInteractions(mock).method")
    void verifyNeverInvokeMock() {
        List<Integer> integerList = mock(List.class);

        verifyNoInteractions(integerList);
    }
}
