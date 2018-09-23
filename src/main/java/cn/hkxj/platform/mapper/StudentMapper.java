package cn.hkxj.platform.mapper;


import cn.hkxj.platform.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author junrong.chen
 */
@Mapper
public interface StudentMapper {
	/**
	 * 通过学号查找学生信息
	 * 这个字段上建立了唯一索引
	 * 考虑到比较常用单独构建成一个方法
	 * @param account
	 * @return
	 */
	Student selectByAccount(Integer account);

	int insertByStudent(Student student);

	int updateByStudent(Student student);
}
