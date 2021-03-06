/* Copyright Airship and Contributors */

package com.urbanairship.config;

import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/**
 * Airship runtime config.
 *
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class AirshipRuntimeConfig {

    private final AirshipUrlConfigProvider urlConfigProvider;
    private final AirshipConfigOptions configOptions;
    @UAirship.Platform
    private final int platform;

    /**
     * Default constructor.
     *
     * @param platform The platform.
     * @param configOptions The config options.
     * @param urlConfigProvider The URL config provider.
     */
    public AirshipRuntimeConfig(@UAirship.Platform int platform,
                                @NonNull AirshipConfigOptions configOptions,
                                @NonNull AirshipUrlConfigProvider urlConfigProvider) {
        this.platform = platform;
        this.configOptions = configOptions;
        this.urlConfigProvider = urlConfigProvider;
    }

    /**
     * Gets the platform.
     *
     * @return The platform.
     */
    @UAirship.Platform
    public int getPlatform() {
        return platform;
    }

    /**
     * Gets the URL config.
     *
     * @return The URL config.
     */
    @NonNull
    public AirshipUrlConfig getUrlConfig() {
        return urlConfigProvider.getConfig();
    }

    /**
     * Gets the Airship config options.
     *
     * @return The config options.
     */
    @NonNull
    public AirshipConfigOptions getConfigOptions() {
        return configOptions;
    }

}
