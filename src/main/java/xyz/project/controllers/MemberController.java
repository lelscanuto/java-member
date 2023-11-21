package xyz.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.project.models.dto.CreateMember;
import xyz.project.models.dto.MemberLite;
import xyz.project.services.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberLite create(@RequestBody CreateMember createMember) {
        return memberService.create(createMember);
    }
}