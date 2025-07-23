package com.petdiary.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest

@Configuration
class OAuthClientConfig(
    private val properties: OAuth2ClientProperties
) {

    @Bean
    fun secretsManagerClient(): SecretsManagerClient {
        return SecretsManagerClient.builder()
            .region(Region.of("ap-northeast-2"))
            .build()
    }

    @Bean
    fun clientRegistrationRepository(secretsManagerClient: SecretsManagerClient): ClientRegistrationRepository {

        val registrations = properties.registration.map { (registrationId, props) ->
            val provider = properties.provider[registrationId]

            val clientId = getSecretFromASM(secretsManagerClient, "oauth-$registrationId-key")

            ClientRegistration.withRegistrationId(registrationId)
                .clientId(clientId)
                .clientSecret("")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(props.redirectUri)
                .scope(*props.scope.toTypedArray())
                .authorizationUri(provider?.authorizationUri ?: error("Missing provider URI"))
                .tokenUri(provider.tokenUri)
                .userInfoUri(provider.userInfoUri)
                .userNameAttributeName(provider.userNameAttribute ?: "id")
                .clientName(props.clientName ?: registrationId)
                .build()
        }

        return InMemoryClientRegistrationRepository(registrations)
    }

    private fun getSecretFromASM(secretsManagerClient: SecretsManagerClient, secretName: String): String {
        val request = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build();
        val response = secretsManagerClient.getSecretValue(request)
        return response.secretString()
    }
}