package split;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Mock 테스트")
@ExtendWith(MockitoExtension.class)
public class MockTest {

    @Mock
    private MockFixture nameWithMockAnnotation;

    @DisplayName("목 객체는 스터빙 없이는 정상 테스트가 불가하다.( @Mock 사용 )")
    @Test
    public void mockObjectCanNotTestWithoutStubbingMockAnnotation() {
        final String result = nameWithMockAnnotation.getName();
        Assertions.assertThat(result).isNotEqualTo("name");
    }

    @DisplayName("목 객체는 스터빙 없이는 정상 테스트가 불가하다. ( Mockito.mock() 사용 )")
    @Test
    public void mockObjectCanNotTestWithoutStubbing() {
        final MockFixture mock = mock(MockFixture.class);
        final String result = mock.getName();
        Assertions.assertThat(result).isNotEqualTo("name");
    }

    @DisplayName("목 객체를 스터빙하여 테스트한다. ( @Mock 사용 )")
    @Test
    public void testMockWithStubbingWithAnnotation() {
        // Mock 객체에 대한 기대 설정
        when(nameWithMockAnnotation.getName()).thenReturn("stubbing");
        // 테스트 코드 실행
        final String nameWithMockAnnotationNameResult = nameWithMockAnnotation.getName();
        // 결과 검증
        Assertions.assertThat("stubbing").isEqualTo(nameWithMockAnnotationNameResult);
    }

    @DisplayName("목 객체를 스터빙하여 테스트한다. ( Mockito.mock() 사용 )")
    @Test
    public void testMockWithStubbing() {
        // Mock 객체 생성
        MockFixture mock = mock(MockFixture.class);
        // Mock 객체에 대한 기대 설정
        when(mock.getName()).thenReturn("stubbing");
        // 테스트 코드 실행
        String mockResult = mock.getName();
        // 결과 검증
        Assertions.assertThat("stubbing").isEqualTo(mockResult);
    }

    @DisplayName("메서드 호출 횟수를 검증한다 ( 한번 )")
    @Test
    public void verifyMethodOnce() {
        nameWithMockAnnotation.getName();
        verify(nameWithMockAnnotation).getName();
    }

    @DisplayName("메서드 호출 횟수를 검증한다 ( 여러번 )")
    @Test
    public void verifyMethodManyTimes() {
        nameWithMockAnnotation.getName();
        nameWithMockAnnotation.getName();
        nameWithMockAnnotation.getName();
        nameWithMockAnnotation.getName();
        verify(nameWithMockAnnotation, times(4)).getName();
    }
}

