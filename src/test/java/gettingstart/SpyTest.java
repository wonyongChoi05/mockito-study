package gettingstart;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Spy 테스트")
@ExtendWith(MockitoExtension.class)
public class SpyTest {

    @Spy
    private MockFixture spyName = new MockFixture();

    @DisplayName("스파이 객체는 스터빙 없이도 테스트가 가능하다. ( Mockito.spy() 를 이용 )")
    @Test
    public void testSpyWithoutStubbingWithoutAnnotation() {
        final MockFixture mockFixture = Mockito.spy(MockFixture.class);

        Assertions.assertThat(mockFixture.getName()).isEqualTo("name");
    }

    @DisplayName("스파이 객체를 스터빙으로 테스트가 가능하다. ( Mockito.spy() 를 이용 ) ")
    @Test
    public void testSpyWithStubbingWithoutAnnotation() {
        final MockFixture mockFixture = Mockito.spy(MockFixture.class);
        final String beforeStubbing = mockFixture.getName();
        when(mockFixture.getName()).thenReturn("stubbing");
        final String afterStubbing = mockFixture.getName();

        Assertions.assertThat(beforeStubbing).isEqualTo("name");
        Assertions.assertThat(afterStubbing).isEqualTo("stubbing");
    }

    @DisplayName("스파이 객체는 스터빙 없이도 테스트가 가능하다. ( @Spy를 이용 )")
    @Test
    public void testSpyWithoutStubbing() {
        Assertions.assertThat(spyName.getName()).isEqualTo("name");
    }

    @DisplayName("스파이 객체를 스터빙으로 테스트가 가능하다. ( @Spy를 이용 )")
    @Test
    public void testSpyWithStubbing() {
        final String beforeStubbing = spyName.getName();
        when(spyName.getName()).thenReturn("stubbing");
        final String afterStubbing = spyName.getName();

        Assertions.assertThat(beforeStubbing).isEqualTo("name");
        Assertions.assertThat(afterStubbing).isEqualTo("stubbing");
    }
}

