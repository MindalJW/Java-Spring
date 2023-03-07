package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);//JdbcTemplate을 이용하여 SimpleJdbcInsert 객체를 생성합니다.
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        // member 테이블에 INSERT 쿼리를 실행하고 자동 생성된 id 값을 반환하는 설정을 추가합니다.
        Map<String, Object> parameters = new HashMap<>();//INSERT 쿼리에 필요한 파라미터를 저장할 Map 객체를 생성합니다.
        parameters.put("name", member.getName());//파라미터 Map 객체에 Member 객체의 이름(name) 값을 추가합니다.

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));//생성한 SimpleJdbcInsert 객체를 사용하여 INSERT 쿼리를 실행하고, 자동 생성된 id 값을 key 변수에 저장합니다.
        member.setId(key.longValue());//Member 객체에 자동 생성된 id 값을 설정합니다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {//집합의 열을 Member 개체의 속성에 매핑하는 RowMapper 개체를 만듭니다
        return (rs, rowNum) -> { //ResultSet 개체 rs와 행 번호 rowNum의 두 매개변수를 사용하는 람다 식입니다.
            Member member = new Member();
            member.setId(rs.getLong("id"));//Member 개체의 id 속성을 결과 집합의 "id" 열 값으로 설정합니다.
            member.setName(rs.getString("name"));//Member 개체의 name 속성을 결과 집합의 "name" 열 값으로 설정합니다.
            return member;//집합의 데이터로 채워진 Member 개체를 반환
        };
    }
}
