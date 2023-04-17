package split.mockmethods;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class FalseAnswerFixture implements Answer<Boolean> {

    @Override
    public Boolean answer(InvocationOnMock invocation) throws Throwable {
        return false;
    }
}
