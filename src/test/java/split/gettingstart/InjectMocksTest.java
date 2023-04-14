package split.gettingstart;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("InjectMocks 테스트.")

@ExtendWith(MockitoExtension.class)
public class InjectMocksTest {

    @Mock
    Map<String, String> mockMap;
    @InjectMocks
    MockInjectFixture mockInjectFixture;

    @DisplayName("Mock 과 같은 타입의 필드에 Mock 이 주입되는지 hashCode 로 확인한다")
    @Test
    public void injectedToSameType() {
        final int mockHash = mockMap.hashCode();
        final int injectedMapFirst = mockInjectFixture.getFirstMap().hashCode();

        Assertions.assertThat(mockHash).isEqualTo(injectedMapFirst);
    }

    //우선 처음에 존재하는 필드에 주입되는 거로 확인되지만 테스트 추가 예정
    @DisplayName("Mock 과 같은 타입의 필드가 두개 있을시 이 주입되는지 hashCode 로 확인한다")
    @Test
    public void injectedToSameTypeWhenExistMany() {
        final int mockHash = mockMap.hashCode();
        final int injectedMapFirst = mockInjectFixture.getFirstMap().hashCode();
        final int injectedMapSecond = mockInjectFixture.getSecondMap().hashCode();

        Assertions.assertThat(mockHash).isEqualTo(injectedMapFirst);
        Assertions.assertThat(mockHash).isNotEqualTo(injectedMapSecond);
    }

    @DisplayName("Mock 과 다른 타입의 필드에는 Mock 이 주입되지 않는다")
    @Test
    public void isInjectedToDifferentType() {
        final int mockHash = mockMap.hashCode();
        final int mockInjectedList = mockInjectFixture.getList().hashCode();

        Assertions.assertThat(mockHash).isNotEqualTo(mockInjectedList);
    }

    @DisplayName("Mock 의 스터빙을 InjectMocks 에서 검증한다.")
    @Test
    public void test() {
        final String putKey = "one";
        final String putValue = "oneValue";
        when(mockMap.get(putKey)).thenReturn(putValue);
        final String mockResult = mockMap.get(putKey);

        verify(mockMap).get(putKey);
        verify(mockInjectFixture.getFirstMap()).get(putKey);

        Assertions.assertThat(mockResult).isEqualTo(putValue);
        Assertions.assertThat(mockInjectFixture.getFromFirst(putKey)).isEqualTo(mockResult);
    }

    @DisplayName("Spy 를 injection 에 사용할 때는 생성자 주입을 이용한다.")
    @Test
    public void spyInjection() {
        final String putKey = "one";
        final String putValue = "oneValue";
        final MockInjectFixture spyMockInjectFixture = spy(new MockInjectFixture(mockMap));

        when(mockMap.get(putKey)).thenReturn(putValue);
        final String mockResult = mockMap.get(putKey);

        verify(spyMockInjectFixture.getFirstMap()).get(putKey);

        Assertions.assertThat(mockResult).isEqualTo(putValue);
        Assertions.assertThat(spyMockInjectFixture.getFromFirst(putKey)).isEqualTo(mockResult);
    }
}
