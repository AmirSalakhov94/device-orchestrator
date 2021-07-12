package tech.itpark.deviceorchestrator.security.principal;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.ProfileDto;
import tech.itpark.deviceorchestrator.dto.enums.Role;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Component
public class ProfilePrincipalExtractor extends PrincipalExtractor<ProfileDto> {

    private static final String EMAIL = "email";
    private static final String PHONE = "phone";

    public ProfilePrincipalExtractor(TokenStore tokenStore) {
        super(tokenStore);
    }

    public ProfileDto extractPrincipal(OAuth2AccessToken oAuth2AccessToken) {
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        UUID id = UUID.fromString(additionalInformation.get(ID).toString());
        String name = (String) additionalInformation.get(NAME);
        String email = (String) additionalInformation.get(EMAIL);
        String phone = (String) additionalInformation.get(PHONE);
        Instant createDate = Instant.ofEpochMilli((Long) additionalInformation.get(CREATE_DATE));

        return ProfileDto.builder()
                .id(id)
                .name(name)
                .role(Role.PROFILE)
                .email(email)
                .phone(phone)
                .createDate(createDate)
                .build();
    }
}
