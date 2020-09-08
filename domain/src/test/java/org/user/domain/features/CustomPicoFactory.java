package org.user.domain.features;

import cucumber.runtime.java.picocontainer.PicoFactory;
import org.user.domain.features.stubs.InMemoryRepositoryStub;
import org.user.domain.features.stubs.TestContext;

public class CustomPicoFactory extends PicoFactory {
    public CustomPicoFactory(){
        super();
        addClass(InMemoryRepositoryStub.class);
        addClass(TestContext.class);
    }

}
