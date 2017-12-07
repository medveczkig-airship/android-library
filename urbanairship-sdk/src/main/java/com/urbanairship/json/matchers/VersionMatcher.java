/* Copyright 2017 Urban Airship and Contributors */

package com.urbanairship.json.matchers;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;
import com.urbanairship.util.IvyVersionMatcher;

/**
 * Version matcher.
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class VersionMatcher extends ValueMatcher {

    public static final String VERSION_KEY = "version";

    private IvyVersionMatcher versionMatcher;

    /**
     * Default constructor.
     * @param matcher The version matcher.
     */
    public VersionMatcher(@NonNull IvyVersionMatcher matcher) {
        this.versionMatcher = matcher;
    }

    @Override
    public JsonValue toJsonValue() {
        return JsonMap.newBuilder()
                      .putOpt(VERSION_KEY, versionMatcher)
                      .build()
                      .toJsonValue();
    }

    @Override
    public boolean apply(JsonSerializable jsonSerializable) {
        JsonValue value = jsonSerializable == null ? JsonValue.NULL : jsonSerializable.toJsonValue();
        if (value == null) {
            value = JsonValue.NULL;
        }

        return value.isString() && versionMatcher.apply(value.getString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VersionMatcher that = (VersionMatcher) o;

        return versionMatcher != null ? versionMatcher.equals(that.versionMatcher) : that.versionMatcher == null;
    }

    @Override
    public int hashCode() {
        return versionMatcher != null ? versionMatcher.hashCode() : 0;
    }
}
