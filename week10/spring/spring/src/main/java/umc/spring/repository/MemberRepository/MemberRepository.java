package umc.spring.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, umc.spring.repository.MemberRepository.MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}