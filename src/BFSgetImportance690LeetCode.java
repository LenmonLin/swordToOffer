import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * 给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。
 * 比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。那
 * 么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，员工3的数据结构
 * 是[3, 5, []]。注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有
 * 体现在员工1的数据结构中。
 * 现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
 * 示例 1:
 * 输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * 输出: 11
 * 解释:
 * 员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的
 * 总重要度是 5 + 3 + 3 = 11。
 * 注意:
 * 一个员工最多有一个直系领导，但是可以有多个直系下属
 * 员工数量不超过2000。
 * @author LemonLin
 * @Description :ArraygetImportance690LeetCode
 * @date 20.5.26-10:38
 * 思路：比较直白，注意理解直系员工的概念。本题其实不是单单求直系员工，应该用BFS
 * 处理，需要用HashMap对应存储下标。查找方便。
 * bug1:
 * [[101,3,[]],[2,5,[101]]]
 * 2
 * java.lang.IndexOutOfBoundsException: Index 1 out of bounds for length 1
 *   at line 64, java.base/jdk.internal.util.Preconditions.outOfBounds
 *   at line 70, java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex
 *   at line 248, java.base/jdk.internal.util.Preconditions.checkIndex
 *   at line 373, java.base/java.util.Objects.checkIndex
 *   at line 425, java.base/java.util.ArrayList.get
 *   at line 23, Solution.getImportance
 *   at line 156, __DriverSolution__.__helper__
 *   at line 205, __Driver__.main
 *   bug2:
 *   输入:
 * [[1,5,[2,3]],[2,3,[4]],[3,4,[]],[4,1,[]]]
 * 1
 * 输出
 * 12
 * 预期结果
 * 13
 * 解决bug2 ，需要用BFS解决。
 */
public class BFSgetImportance690LeetCode {
    /*
// Definition for Employee.
*/
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    public int getImportance(List<Employee> employees, int id) {
        //先用HashMap建立员工的id索引，方面后续查找
        HashMap<Integer,Employee> hashMap = new HashMap<>();
        for (int i =0;i<employees.size();i++){
            Employee employee = employees.get(i);
            hashMap.put(employee.id,employee);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        //通过遍历找到对应的第一个员工id
        for (int i =0;i<employees.size();i++){
            if (employees.get(i).id==id){
                Employee employee =employees.get(i);
                queue.addLast(employee.id);
                break;
            }
        }
        //BFS主体程序
        int result = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int k =0;k<size;k++){
                Integer temp = queue.removeFirst();
                Employee employee =hashMap.get(temp);
                result += employee.importance;
                if (employee.subordinates.size()!=0){
                    for (int m:employee.subordinates){
                        queue.addLast(m);
                    }
                }
            }

        }
        return result;
    }
}
