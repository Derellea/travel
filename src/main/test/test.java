import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class test {
    public static void main(String[] args) {
//        System.out.println(
//                new JdbcTemplate(JDBCUtils.getDataSource()).queryForMap("select * from tab_user")
//        );
        new JdbcTemplate(JDBCUtils.getDataSource()).update("insert into tab_user(username,password,sex)value('z','2b','男')");
    }
}
