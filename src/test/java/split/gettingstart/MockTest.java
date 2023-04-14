package split.gettingstart;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
    private MockFixture mockFixture;

    @DisplayName("목 객체는 스터빙 없이는 정상 테스트가 불가하다.( @Mock 사용 )")
    @Test
    public void mockObjectCanNotTestWithoutStubbingMockAnnotation() {
        final String result = mockFixture.getName();
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
        when(mockFixture.getName()).thenReturn("stubbing");
        // 테스트 코드 실행
        final String nameWithMockAnnotationNameResult = mockFixture.getName();
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
        mockFixture.getName();
        verify(mockFixture).getName();
    }

    @DisplayName("메서드 호출 횟수를 검증한다 ( 여러번 )")
    @Test
    public void verifyMethodManyTimes() {
        mockFixture.getName();
        mockFixture.getName();
        mockFixture.getName();
        mockFixture.getName();
        verify(mockFixture, times(4)).getName();
    }

    @Mock
    List<String> mockList;

    @DisplayName("불 필요한 Stubbing 이 존재할 경우 테스트가 실패한다 ( 내용 참조 )")
    @Test
    public void whenMockitoAnnotationsUninitialized_thenNPEThrown() {
        //아래의 코드의 주석을 풀고 실행할 경우 테스트가 실패한다
//        Mockito.when(mockList.size()).thenReturn(1);
    }

    /*
     --ErrorMessage--

     org.mockito.exceptions.misusing.UnnecessaryStubbingException:
     Unnecessary stubbings detected.
     Clean & maintainable test code requires zero unnecessary code.
     Following stubbings are unnecessary (click to navigate to relevant line of code):
     1. -> at split.gettingstart.MockTest.whenMockitoAnnotationsUninitialized_thenNPEThrown(MockTest.java:85)
     Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
    */
}

