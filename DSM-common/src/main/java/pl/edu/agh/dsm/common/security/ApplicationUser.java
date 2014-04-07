package pl.edu.agh.dsm.common.security;

import java.util.UUID;

public interface ApplicationUser {
    UUID getId();
    String getName();
}
