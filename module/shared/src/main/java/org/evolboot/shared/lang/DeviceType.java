package org.evolboot.shared.lang;

import lombok.Getter;

/**
 * @author corona
 * 
 */
@Getter
public enum DeviceType {

    IOS(1),
    ANDROID(2),
    WINDOWS(3),
    OSX(4),
    WEB(5),
    MINI_PROGRAM(6),
    LINUX(7),
    UNKNOWN(999);

    private int platform;

    DeviceType(int platform) {
        this.platform = platform;
    }
}
