package split.gettingstart;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Captor 테스트")
@ExtendWith(MockitoExtension.class)
public class CaptorTest {

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @DisplayName("ArgumentCaptor 를 사용하여 메서드 호출에 사용된 인자를 저장하여 검증한다 ( 호출을 한번 했을 때 )")
    @Test
    public void useArgumentCaptorOnce() {
        final List<String> mockList = mock(List.class);
        final ArgumentCaptor<String> args = ArgumentCaptor.forClass(String.class);
        mockList.add("first");
        verify(mockList).add(args.capture());
        Assertions.assertThat(args.getValue()).isEqualTo("first");
    }

    @DisplayName("ArgumentCaptor 를 사용하여 메서드 호출에 사용된 인자를 저장하여 검증한다 ( 호출을 여러번 했을 때 )")
    @Test
    public void useArgumentCaptorMany() {
        final List<String> mockList = mock(List.class);
        final ArgumentCaptor<String> args = ArgumentCaptor.forClass(String.class);
        mockList.add("first");
        mockList.add("second");
        verify(mockList, times(2)).add(args.capture());
        Assertions.assertThat(args.getAllValues()).containsExactly("first", "second");
    }

    @DisplayName("@Captor 를 사용하여 메서드 호출에 사용된 인자를 저장하여 검증한다 ( 호출을 한번 했을 때 )")
    @Test
    public void useAnnotationCaptorOnce() {
        final List<String> mockList = mock(List.class);
        mockList.add("first");
        verify(mockList).add(argumentCaptor.capture());
        Assertions.assertThat(argumentCaptor.getValue()).isEqualTo("first");
    }

    @DisplayName("@Captor 를 사용하여 메서드 호출에 사용된 인자를 저장하여 검증한다 ( 호출을 여러번 했을 때 )")
    @Test
    public void useAnnotationCaptorMany() {
        final List<String> mockList = mock(List.class);
        mockList.add("first");
        mockList.add("second");
        verify(mockList, times(2)).add(argumentCaptor.capture());
        Assertions.assertThat(argumentCaptor.getAllValues()).containsExactly("first", "second");
    }
}

