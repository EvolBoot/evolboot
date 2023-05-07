package org.evolboot.shared.event.dict;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.evolboot.shared.event.Event;

/**
 * @author evol
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DictKeyDeleteEvent implements Event<Long> {

    private Long id;

    @Override
    public Long getSource() {
        return id;
    }
}
