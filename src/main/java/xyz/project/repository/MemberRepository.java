package xyz.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.project.models.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
