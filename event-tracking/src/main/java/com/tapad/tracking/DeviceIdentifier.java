package com.tapad.tracking;

import java.util.List;

import com.tapad.tracking.deviceidentification.TypedIdentifier;

/**
 * Locator for a the device identifier. Used to ensure opt-out
 * is honored immediately while avoiding static coupling.
 */
public interface DeviceIdentifier {
    String get();
    String getTypedIds();
    boolean isOptedOut();
}

