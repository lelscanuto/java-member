package xyz.project.services;

import xyz.project.models.dto.CreateMember;
import xyz.project.models.dto.MemberLite;

public interface MemberService {

    MemberLite create(CreateMember createMember);
}
