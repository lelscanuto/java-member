package xyz.project.models.dto;

import xyz.project.common.util.TimeUtil;
import xyz.project.models.entities.Credential;
import xyz.project.models.entities.Member;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateMember(
        String birthDate,
        String name,
        String street,
        String city,
        String state,
        String postal,
        @Enumerated(EnumType.ORDINAL)
        Member.Status statusId,
        @Enumerated(EnumType.ORDINAL)
        Member.Role roleId,
        String email,
        String passwordHash
) {

    public LocalDate getBirthDateAsLocalDate() {
        return TimeUtil.parse(this.birthDate);
    }
    public Member createEntity() {
        Member member = new Member(null, getBirthDateAsLocalDate(), this.name, this.street, this.city, this.state, this.postal, this.statusId, this.roleId, LocalDateTime.now(), LocalDateTime.now(), null);
        member.withCredential(new Credential(null, this.email, this.passwordHash, null));
        return member;
    }
}