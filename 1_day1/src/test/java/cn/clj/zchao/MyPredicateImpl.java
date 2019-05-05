package cn.clj.zchao;

/**
 * 〈〉
 *
 * @author zc
 * @create 2018/12/7
 */
public class MyPredicateImpl implements MyPredicate<User> {
    @Override
    public boolean myCompare(User user) {
        return user.getAge()>30;
    }
}
