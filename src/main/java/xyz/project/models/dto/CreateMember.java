package xyz.project.models.dto;

import xyz.project.common.util.TimeUtil;
import xyz.project.models.entities.Credential;
import xyz.project.models.entities.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateMember(
        String birthDate,
        String name,
        String street,
        String city,
        String state,
        String postal,

        int statusId,

        int roleId,
        String email,
        String passwordHash
) {

    public LocalDate getBirthDateAsLocalDate() {
        return TimeUtil.parse(this.birthDate);
    }
    public Member createEntity() {
        Member member = new Member(null, getBirthDateAsLocalDate(), this.name, this.street, this.city, this.state, this.postal, this.statusId, this.statusId, LocalDateTime.now(), LocalDateTime.now(), null);
        member.withCredential(new Credential(null, this.email, this.passwordHash, null));
        return member;
    }
}