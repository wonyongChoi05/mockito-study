package gettingstart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import domain.Car;
import domain.Name;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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

        assertThat(result).isNotEqualTo("name");
    }

    @DisplayName("목 객체는 스터빙 없이는 정상 테스트가 불가하다. ( Mockito.mock() 사용 )")
    @Test
    public void mockObjectCanNotTestWithoutStubbing() {
        final MockFixture mock = Mockito.mock(MockFixture.class);
        final String result = mock.getName();

        assertThat(result).isNotEqualTo("name");
    }

    @DisplayName("목 객체를 스터빙하여 테스트한다. ( @Mock 사용 )")
    @Test
    public void testMockWithStubbingWithAnnotation() {
        // Mock 객체에 대한 기대 설정
        when(mockFixture.getName()).thenReturn("stubbing");
        // 테스트 코드 실행
        final String nameWithMockAnnotationNameResult = mockFixture.getName();
        // 결과 검증
        assertThat("stubbing").isEqualTo(nameWithMockAnnotationNameResult);
    }

    @DisplayName("목 객체를 스터빙하여 테스트한다. ( Mockito.mock() 사용 )")
    @Test
    public void testMockWithStubbing() {
        // Mock 객체 생성
        MockFixture mock = Mockito.mock(MockFixture.class);
        // Mock 객체에 대한 기대 설정
        when(mock.getName()).thenReturn("stubbing");
        // 테스트 코드 실행
        String mockResult = mock.getName();
        // 결과 검증
        assertThat("stubbing").isEqualTo(mockResult);
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
    @DisplayName("stubbing을 한 값은 false 하지 않은 값도 false")
    @Test
    void non_stubbing_test() {
        // given
        List<String> mockedList = mock(List.class);

        // when
        final boolean stubbingResult = mockedList.add("a");
        final boolean nonStubbingResult = mockedList.add("b");

        // then
        assertAll(
                () -> assertThat(stubbingResult).isFalse(),
                () -> assertThat(nonStubbingResult).isFalse()
        );
    }

    @DisplayName("stubbing을 한 값은 true 하지 않은 값은 false")
    @Test
    void mocked_list_test() {
        // given
        List<String> mockedList = mock(List.class);
        when(mockedList.add("a")).thenReturn(true);

        // when
        final boolean stubbingResult = mockedList.add("a");
        final boolean nonStubbingResult = mockedList.add("b");

        // then
        assertAll(
                () -> assertThat(stubbingResult).isTrue(),
                () -> assertThat(nonStubbingResult).isFalse()
        );
    }

    @DisplayName("반환 타입이 객체인 값을 stubbing 할 수 있다.")
    @Test
    void returnType_object_value_stubbing() {
        // given
        Car car = Mockito.mock(Car.class);

        // when
        when(car.getName()).thenReturn(new Name("bebe"));

        // then
        assertThat(car.getName()).isEqualTo(new Name("bebe"));
    }
}

