package ${parentPackageName}.controller;

import ${packageName}.${entityName};
import ${parentPackageName}.service.${entityName}Service;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${entityName?lower_case}")
public class ${entityName?cap_first}Controller {

    @Resource
    private ${entityName?cap_first}Service ${entityName?uncap_first}Service;

    @PostMapping()
    @ApiOperation("新增或修改")
    public ${entityName} save(@RequestBody ${entityName} ${entityName?uncap_first}) {
        return ${entityName?uncap_first}Service.save(${entityName?uncap_first});
    }

    @GetMapping("page")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "页码", example = "1"),
        @ApiImplicitParam(name = "pageSize", value = "每页数量", example = "10"),
    })
    public Page<${entityName}> findAll(Integer pageNum, Integer pageSize) {
        Page<${entityName}> all = ${entityName?uncap_first}Service.findAll(pageNum - 1, pageSize);
        return all;
    }
    @GetMapping()
    @ApiOperation("查询")
    public List<${entityName}> findAll() {
        List<${entityName}> all = ${entityName?uncap_first}Service.findAll();
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
