package com.tapad.tracking.deviceidentification;

import android.content.Context;
import android.os.Build;
import com.tapad.util.DigestUtil;
import com.tapad.util.Logging;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class knows how to fetch and encode android.os.Build.SERIAL
 * This constant is only available as of 2.3 (API level 9).
 * <p/>
 * Gets the build serial hashed with MD5 and formatted as a 32 byte hexadecimal number.
 * Gets the build serial hashed with SHA1 and formatted as a 40 byte hexadecimal number.
 */
public class BuildSerial implements IdentifierSource {
    @Override
    public List<TypedIdentifier> get(Context context) {
        // this is available in API 9 and above
        String buildSerial = (Build.VERSION.SDK_INT >= 9) ? Build.SERIAL : null;
        List<TypedIdentifier> ids = new ArrayList<TypedIdentifier>();
        if (buildSerial != null) {
            try {
                ids.add(new TypedIdentifier(TypedIdentifier.TYPE_BUILD_SERIAL_MD5, DigestUtil.md5Hash(buildSerial)));
            } catch (NoSuchAlgorithmException nsae) {
                Logging.error("Tracking", "Error hashing Build.SERIAL - MD5 not supported");
            }
            try {
                ids.add(new TypedIdentifier(TypedIdentifier.TYPE_BUILD_SERIAL_SHA1, DigestUtil.sha1Hash(buildSerial)));
            } catch (NoSuchAlgorithmException nsae) {
                Logging.error("Tracking", "Error hashing Build.SERIAL - SHA1 not supported");
            }
        } else {
            Logging.warn("Tracking", "Error retrieving Build.SERIAL.");
        }
        return (ids);
    }
}
