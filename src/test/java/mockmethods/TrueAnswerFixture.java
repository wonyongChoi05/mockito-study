package mockmethods;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TrueAnswerFixture implements Answer<Boolean> {

    @Override
    public Boolean answer(InvocationOnMock invocation) throws Throwable {
        return true;
    }
}
