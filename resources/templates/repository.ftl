package ${parentPackageName}.repository;

import ${packageName}.${entityName};
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ${entityName}Repository extends JpaRepository<${entityName}, ${idType}>, JpaSpecificationExecutor {

    /**
     * 批量删除User及更新删除标识
     * @param idList idList
     */
    @Transactional(rollbackOn = Exception.class)
    void deleteByIdIn(List<${idType}> idList);

    /**
     * 根据idList和删除标识查询${entityName}
     * @param idList 要查询的id列表
     * @return ${entityName}List
     */
    List<${entityName}> findByIdIn(List<${idType}> idList);
}