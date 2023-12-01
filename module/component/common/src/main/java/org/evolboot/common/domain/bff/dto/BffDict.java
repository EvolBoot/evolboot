package org.evolboot.common.domain.bff.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author evol
 */
@Getter
@Setter
@AllArgsConstructor
public class BffDict implements Serializable {


    @Schema(title = "字典Key Id")
    private Long id;

    @Schema(title = "显示名称")
    private String displayName;

    @Schema(title = "字典Key")
    private String key;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "备注")
    private String remark;

    private List<BffDictValue> values;


    @Setter
    @Getter
    @AllArgsConstructor
    public static class BffDictValue implements Serializable {

        @Schema(title = "字典值ID")
        private Long id;

        @Schema(title = "字典KeyId")
        private Long dictKeyId;

        @Schema(title = "显示名称")
        private String displayName;

        @Schema(title = "字典值")
        private String value;

        @Schema(title = "排序")
        private Integer sort;

        @Schema(title = "备注")
        private String remark;

    }

}
