package xyz.project.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import xyz.project.common.template.FacadeTemplate;
import xyz.project.dao.MemberDao;
import xyz.project.models.dto.CreateMember;
import xyz.project.models.dto.MemberLite;
import xyz.project.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LogManager.getLogger(MemberServiceImpl.class);

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao  = memberDao;
    }

    private final MemberDao memberDao;

    @Override
    public MemberLite create(CreateMember createMember) {
        return new FacadeTemplate.api<CreateMember, MemberLite>(logger).supply(() -> memberDao.save(createMember));
    }
}
