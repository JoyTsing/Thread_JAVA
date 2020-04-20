package immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @description：一个属性是对象，但是整体不可改变，其他类无法修改set里面的数据
 */
public class ImmutableDemo {
    private final Set<String> students=new HashSet<>();

    public ImmutableDemo() {
        students.add("222");
        students.add("111");
        students.add("333");
    }

    public boolean isStudent(String name){
        return students.contains(name);
    }
}
