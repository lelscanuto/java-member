package xyz.project.models.dto;

import xyz.project.models.entities.Member;

public record MemberLite(
        Long id,
        String email,
        String name,
        String street,
        String city,
        String state,
        String postal,
        String birthDate,
        int roleId,
        int  statusId
) {


    public static MemberLite create(Member member, String email) {
        return new MemberLite(member.getId(), email, member.getName(), member.getStreet(), member.getCity(), member.getState(),  member.getPostal(), member.getBirthDate().toString(), member.getRoleId(), member.getStatusId());
    }
}