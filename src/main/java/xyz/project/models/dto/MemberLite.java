package xyz.project.models.dto;

import xyz.project.models.entities.Member;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public record MemberLite(
        Long id,
        String email,
        String name,
        String street,
        String city,
        String state,
        String postal,
        String birthDate,
        @Enumerated(EnumType.ORDINAL) Member.Role roleId,
        @Enumerated(EnumType.ORDINAL) Member.Status statusId
) {


    public static MemberLite create(Member member, String email) {
        return new MemberLite(member.getId(), email, member.getName(), member.getStreet(), member.getCity(), member.getState(),  member.getPostal(), member.getBirthDate().toString(), member.getRoleId(), member.getStatusId());
    }
}