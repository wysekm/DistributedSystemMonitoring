package pl.edu.agh.dsm.common.security;

import java.util.UUID;

public class MockAutorizationContext implements AutorizationContext {

    class MockRequestUser implements ApplicationUser
    {
        UUID id = UUID.randomUUID();
        String name = "mockUser";

        public MockRequestUser() {
        }

        public UUID getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MockRequestUser)) return false;

            MockRequestUser that = (MockRequestUser) o;

            if (!id.equals(that.id)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    MockRequestUser mockRequestUser = new MockRequestUser();

    @Override
    public ApplicationUser getActiveUser() {
        return mockRequestUser;
    }
}
