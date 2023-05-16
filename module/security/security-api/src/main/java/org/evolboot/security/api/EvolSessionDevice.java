package org.evolboot.security.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author evol
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EvolSessionDevice implements Serializable {

    private String loginIp;

    private String token;

}
