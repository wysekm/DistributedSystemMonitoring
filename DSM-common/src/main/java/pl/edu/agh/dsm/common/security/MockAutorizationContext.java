package pl.edu.agh.dsm.common.security;

import lombok.Data;
import java.util.UUID;

public class MockAutorizationContext implements AutorizationContext {

    @Data
    class MockRequestUser implements ApplicationUser
    {
        UUID id = UUID.randomUUID();
        String name = "mockUser";
    }

    MockRequestUser mockRequestUser = new MockRequestUser();

    @Override
    public ApplicationUser getActiveUser() {
        return mockRequestUser;
    }
}
