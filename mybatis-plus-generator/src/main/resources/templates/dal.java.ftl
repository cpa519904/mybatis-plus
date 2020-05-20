package ${package.Dal};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superDalClass};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.dalName} : ${superDalName}<${table.mapperName}, ${entity}>(){

}
<#else>
public class ${table.dalName} extends ${superDalName}<${table.mapperName}, ${entity}> {

}
</#if>
