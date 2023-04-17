package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarTest {

    @Mock
    private Name name;

    @InjectMocks
    private Car car;

    @DisplayName("InjectMocks를 사용하지 않고 Mock 의존성을 주입받는 방법")
    @Test
    void non_inject_mocks() {
        // given
        final Car car = new Car(name);

        // when
        Mockito.when(car.checkCar("hyundai")).thenReturn(true);

        // then
        Assertions.assertThat(car.checkCar("hyundai")).isTrue();
        Assertions.assertThat(car.checkCar("kia")).isFalse();
    }

    @DisplayName("InjectMocks를 사용해서 Mock 의존성을 주입받는 방법")
    @Test
    void inject_mocks() {
        // when
        Mockito.when(car.checkCar("hyundai")).thenReturn(true);

        // then
        Assertions.assertThat(car.checkCar("hyundai")).isTrue();
        Assertions.assertThat(car.checkCar("kia")).isFalse();
    }

    @DisplayName("ArgumentCaptor 를 사용하여 메서드 호출에 사용된 인자를 저장하여 검증한다.(호출을 한번 했을 때 )")
    @Test
    public void useArgumentCaptorOnce() {
        final List<String> mockList = mock(List.class);
        final ArgumentCaptor<String> args = ArgumentCaptor.forClass(String.class);

        mockList.add("first");

        verify(mockList).add(args.capture());

        Assertions.assertThat(args.getValue()).isEqualTo("first");
    }

}
