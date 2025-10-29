package com.kimbh.core.exception

import com.kimbh.core.utils.AuthType

/**
 * The base exception for all errors originating from the Auth SDK.
 * This makes it easy to catch all SDK-specific errors with a single try-catch.
 *
 * @param platform The authentication platform where the error occurred (e.g., KAKAO, NAVER).
 * @param message A clear, descriptive error message.
 * @param cause The original exception (e.g., FacebookException, ClientError) for preserving the stack trace.
 */
open class AuthSdkException(
    val platform: AuthType? = null,
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)

/**
 * Thrown when an SDK initialization fails (e.g., AuthManager or specific platforms).
 */
class InitializationException(
    platform: AuthType? = null,
    message: String,
    cause: Throwable? = null
) : AuthSdkException(platform, message, cause)

/**
 * Thrown when a login attempt fails for any reason other than user cancellation.
 */
class LoginException(
    platform: AuthType,
    message: String,
    cause: Throwable? = null
) : AuthSdkException(platform, message, cause)

/**
 * A specific exception for when the user actively cancels the login flow.
 */
class OperationCancelledException(
    platform: AuthType,
    message: String = "User cancelled the operation on ${platform.name}."
) : AuthSdkException(platform, message)

/**
 * Thrown when fetching user info fails after a successful login.
 */
class UserInfoException(
    platform: AuthType,
    message: String,
    cause: Throwable? = null
) : AuthSdkException(platform, message, cause)

/**
 * Thrown when a required configuration is missing or invalid (e.g., wrong PlatformConfig).
 */
class ConfigurationException(
    platform: AuthType? = null,
    message: String
) : AuthSdkException(platform, message)

/**
 * Thrown for unsupported operations or credential types (e.g., Google Passkey).
 */
class UnsupportedOperationException(
    platform: AuthType,
    message: String
) : AuthSdkException(platform, message)

