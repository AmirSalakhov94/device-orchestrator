package tech.itpark.deviceorchestrator.security.principal;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import tech.itpark.deviceorchestrator.exception.ExtractPrincipalException;

@RequiredArgsConstructor
public abstract class PrincipalExtractor<T> {

    protected static final String ID = "id";
    protected static final String NAME = "name";
    protected static final String CREATE_DATE = "createDate";

    private final TokenStore tokenStore;

    public T getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (OAuth2Authentication.class.isAssignableFrom(authentication.getClass())) {
            OAuth2Authentication oAuth2Authentication
                    = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails details =
                    (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();

            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(details.getTokenValue());
            return extractPrincipal(oAuth2AccessToken);
        }

        throw new ExtractPrincipalException("Fail extract principal");
    }

    protected abstract T extractPrincipal(OAuth2AccessToken oAuth2AccessToken);
}
