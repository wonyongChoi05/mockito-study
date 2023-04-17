package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockTest {

    @DisplayName("stubbing을 한 값은 false 하지 않은 값도 false")
    @Test
    void non_stubbing_test() {
        // given
        List<String> mockedList = mock(List.class);

        // when
        final boolean stubbingResult = mockedList.add("a");
        final boolean nonStubbingResult = mockedList.add("b");

        // then
        Assertions.assertAll(
                () -> assertThat(stubbingResult).isFalse(),
                () -> assertThat(nonStubbingResult).isFalse()
        );
    }

    @DisplayName("stubbing을 한 값은 true 하지 않은 값은 false")
    @Test
    void mocked_list_test() {
        // given
        List<String> mockedList = mock(List.class);
        Mockito.when(mockedList.add("a")).thenReturn(true);

        // when
        final boolean stubbingResult = mockedList.add("a");
        final boolean nonStubbingResult = mockedList.add("b");

        // then
        Assertions.assertAll(
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
