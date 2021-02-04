package ${parentPackageName}.service;

import ${packageName}.${entityName};
import org.springframework.data.domain.Page;

import java.util.List;

public interface ${entityName}Service {

    /**
     * 新增或修改${entityName}
     *
     * @param ${entityName?uncap_first} ${entityName?uncap_first}
     * @return ${entityName}
     */
    ${entityName} save(${entityName} ${entityName?uncap_first});

    /**
     * 根据查询条件查询所有${entityName}
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 分页查询结果
     */
    Page<${entityName}> findAll(Integer pageNum, Integer pageSize);

    /**
     * 根据查询条件查询所有${entityName}
     *
     * @return 所有结果
     */
    List<${entityName}> findAll();

    /**
     * 根据ids批量删除${entityName}
     *
     * @param ids 要删除的id数组
     * @return 删除的id
     */
    List<${idType}> delete${entityName?cap_first}Batch(String[] ids);

}