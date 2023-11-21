package xyz.project.dao.impl;

import org.springframework.stereotype.Component;
import xyz.project.common.exception.ResourceAlreadyExistsException;
import xyz.project.common.exception.ResourceNotFoundException;
import xyz.project.dao.MemberDao;
import xyz.project.models.dto.CreateMember;
import xyz.project.models.dto.MemberLite;
import xyz.project.models.entities.Credential;
import xyz.project.models.entities.Member;
import xyz.project.repository.CredentialRepository;
import xyz.project.repository.MemberRepository;

import java.util.Optional;

@Component
public class MemberDaoImpl implements MemberDao {

    public MemberDaoImpl(MemberRepository memberRepository, CredentialRepository credentialRepository) {
        this.memberRepository = memberRepository;
        this.credentialRepository = credentialRepository;
    }

    private final MemberRepository memberRepository;
    private final CredentialRepository credentialRepository;
    @Override
    public MemberLite save(CreateMember request) {

        Optional<MemberLite> memberLiteOptional = findByEmail(request.email());
        if(memberLiteOptional.isPresent()) {
            throw new ResourceAlreadyExistsException("Member already exists");
        }

        Member member = memberRepository.save(request.createEntity());

        return MemberLite.create(member, request.email());
    }

    @Override
    public MemberLite update(CreateMember request) {
        return null;
    }

    @Override
    public MemberLite findById(Long identifier) {

        Optional<Member> memberOptional = memberRepository.findById(identifier);
        if(memberOptional.isEmpty()) {
            throw new ResourceNotFoundException("Member not found");
        }

        return MemberLite.create(memberOptional.get(), memberOptional.get().getCredential().getEmail());
    }

    @Override
    public Optional<MemberLite> findByEmail(String email) {

        Optional<Credential> credentialOptional = credentialRepository.findByEmail(email);
        if(credentialOptional.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(MemberLite.create(credentialOptional.get().getMember(), credentialOptional.get().getEmail()));
    }
}
