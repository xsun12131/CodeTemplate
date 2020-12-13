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
     * 查找未删除的所有${entityName}
     * @return
     */
    @Override
    @Query("select ${entityNameFirstAlphabet} from ${entityName} ${entityNameFirstAlphabet} where ${entityNameFirstAlphabet}.delFlag = false")
    Page<${entityName}> findAll(Pageable pageable);

    /**
     * 批量删除User及更新删除标识
     * @param idList idList
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query("update ${entityName} ${entityNameFirstAlphabet} set ${entityNameFirstAlphabet}.delFlag = true where ${entityNameFirstAlphabet}.${idName} in (?1)")
    void updateDelFlagBatchByIds(List<${idType}> idList);

    /**
     * 根据idList和删除标识查询${entityName}
     * @param idList 要查询的id列表
     * @param flag 删除标识
     * @return
     */
    List<${entityName}> findByIdInAndDelFlag(List<${idType}> idList, boolean flag);
}