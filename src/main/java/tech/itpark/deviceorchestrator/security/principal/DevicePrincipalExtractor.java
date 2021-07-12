package tech.itpark.deviceorchestrator.security.principal;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.DeviceDto;
import tech.itpark.deviceorchestrator.dto.enums.Role;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Component
public class DevicePrincipalExtractor extends PrincipalExtractor<DeviceDto> {

    private static final String SERIAL_NUMBER = "serialNumber";
    private static final String TYPE_DEVICE = "typeDevice";

    public DevicePrincipalExtractor(TokenStore tokenStore) {
        super(tokenStore);
    }

    public DeviceDto extractPrincipal(OAuth2AccessToken oAuth2AccessToken) {
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
        UUID id = UUID.fromString(additionalInformation.get(ID).toString());
        String serialNumber = (String) additionalInformation.get(SERIAL_NUMBER);
        TypeDevice typeDevice = TypeDevice.valueOf(additionalInformation.get(TYPE_DEVICE).toString());
        String name = (String) additionalInformation.get(NAME);
        Instant createDate = Instant.ofEpochMilli((Long) additionalInformation.get(CREATE_DATE));

        return DeviceDto.builder()
                .id(id)
                .name(name)
                .role(Role.DEVICE)
                .serialNumber(serialNumber)
                .type(typeDevice)
                .createDate(createDate)
                .build();
    }
}
