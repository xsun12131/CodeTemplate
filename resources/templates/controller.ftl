package ${parentPackageName}.controller;

import ${packageName}.${entityName};
import ${parentPackageName}.service.${entityName}Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${entityName?lower_case}")
public class ${entityName?cap_first}Controller {

    @Resource
    private ${entityName?cap_first}Service ${entityName?uncap_first}Service;

    @PostMapping()
    @ApiOperation("新增")
    public ${entityName} save(@RequestBody ${entityName} ${entityName?uncap_first}) {
        return ${entityName?uncap_first}Service.save(${entityName?uncap_first});
    }

    @GetMapping
    @ApiOperation("查询")
    public Page<${entityName}> findAll(Integer pageNum, Integer pageSize) {
        Page<${entityName}> all = ${entityName?uncap_first}Service.findAll(pageNum, pageSize);
        return all;
    }

    @DeleteMapping("batch")
    @ApiOperation("批量删除")
    public Map deleteBatch(@RequestBody ${idType}[] ids) {
        Map<String, Object> map = new HashMap();
        map.put("成功删除的id", ${entityName?uncap_first}Service.delete${entityName?cap_first}Batch(ids));
        return map;
    }
}
