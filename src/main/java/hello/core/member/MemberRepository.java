package hello.core.member;

public interface MemberRepository {

    void save(Member member); // 회원을 저장하는 기능

    Member findById(Long memberId); // 회원 아이디를 사용하여 조회하는 기능
}
