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


    @Schema(description = "字典Key Id")
    private Long id;

    @Schema(description = "显示名称")
    private String displayName;

    @Schema(description = "字典Key")
    private String key;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;

    private List<BffDictValue> values;


    @Setter
    @Getter
    @AllArgsConstructor
    public static class BffDictValue implements Serializable {

        @Schema(description = "字典值ID")
        private Long id;

        @Schema(description = "字典KeyId")
        private Long dictKeyId;

        @Schema(description = "显示名称")
        private String displayName;

        @Schema(description = "字典值")
        private String value;

        @Schema(description = "排序")
        private Integer sort;

        @Schema(description = "备注")
        private String remark;

    }

}
