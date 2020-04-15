package uk.gov.hmcts.fees.register.api.componenttests.backdoors;

import org.springframework.security.core.context.SecurityContextHolder;
import uk.gov.hmcts.fees.register.api.repositories.IdamRepository;
import uk.gov.hmcts.fees2.register.util.SecurityUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SecurityUtilsMock extends SecurityUtils {

    private final IdamRepositoryMock idamRepositoryMock;
    private String userToken;
    private boolean isAuthenticated = true;

    public SecurityUtilsMock(final IdamRepository idamRepository) {
        super(idamRepository);
        idamRepositoryMock = (IdamRepositoryMock)idamRepository;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public String getUserToken() {
        return userToken;
    }

    public void registerToken(final String token, final String userId) {
        this.userToken = token;
        idamRepositoryMock.registerToken(userToken, userId);
        isAuthenticated = true;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
