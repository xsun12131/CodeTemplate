package ${parentPackageName}.service.impl;

import ${packageName}.${entityName};
import ${parentPackageName}.repository.${entityName}Repository;
import ${parentPackageName}.service.${entityName}Service;
import com.github.wenhao.jpa.Specifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ${entityName}ServiceImpl implements ${entityName}Service {

    @Resource
    private ${entityName}Repository ${entityName?uncap_first}Repository;

    /**
     * 新增${entityName}
     *
     * @param ${entityName?uncap_first} ${entityName?uncap_first}
     * @return
     */
    @Override
    public ${entityName} save(${entityName} ${entityName?uncap_first}) {
        return ${entityName?uncap_first}Repository.save(${entityName?uncap_first});
    }

    /**
     * 根据查询条件查询所有${entityName}
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public Page<${entityName}> findAll(Integer pageNum, Integer pageSize) {
        Specification<${entityName}> specifications = Specifications.<${entityName}>and()
                .eq("delFlag", false).build();
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<${entityName}> all = ${entityName?uncap_first}Repository.findAll(specifications, pageable);
        return all;
    }

    /**
     * 根据查询条件查询所有${entityName}
     *
     * @return
     */
    public List<${entityName}> findAll() {
        Specification<${entityName}> specifications = Specifications.<${entityName}>and()
                .eq("delFlag", false).build();
        List<${entityName}> all = ${entityName?uncap_first}Repository.findAll(specifications);
        return all;
    }

    /**
     * 根据ids批量删除${entityName}
     *
     * @param ids 要删除的id数组
     * @return 删除的id
     */
    @Override
    public List<${idType}> delete${entityName?cap_first}Batch(String[] ids) {
        List<${idType}> idList = Arrays.asList(ids);
        List<${entityName}> ${entityName?uncap_first}List
                = ${entityName?uncap_first}Repository.findByIdInAndDelFlag(idList, false);
        ${entityName?uncap_first}Repository.updateDelFlagBatchByIds(idList);
        List<${idType}> hasIds
                = ${entityName?uncap_first}List.stream().map(${entityName}::get${idName?cap_first}).collect(Collectors.toList());
        return hasIds;
    }


}