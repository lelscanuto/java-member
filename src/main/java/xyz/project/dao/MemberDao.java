package xyz.project.dao;

import xyz.project.models.dto.CreateMember;
import xyz.project.models.dto.MemberLite;

import java.util.Optional;

public interface MemberDao extends BaseDao<Long, CreateMember, CreateMember, MemberLite>{

    Optional<MemberLite> findByEmail(String email);
}
