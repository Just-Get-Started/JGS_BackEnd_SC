package com.example.notificationservice.API.TeamInviteNotification.Controller;

import com.example.notificationservice.API.TeamInviteNotification.DTO.Request.CreateTeamInviteDTO;
import com.example.notificationservice.API.TeamInviteNotification.DTO.Request.JoinTeamDTO;
import com.example.notificationservice.API.TeamInviteNotification.DTO.TeamInviteListDTO;
import com.example.notificationservice.API.TeamInviteNotification.Service.APITeamInviteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team-invite")
@RequiredArgsConstructor
@Validated
public class APITeamInviteController {

    private final APITeamInviteService apiTeamInviteService;

    @GetMapping
    public ResponseEntity<TeamInviteListDTO> getTeamInvite(@RequestHeader(value = "member_id") String memberId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                apiTeamInviteService.getTeamInvite(Long.valueOf(memberId)));
    }

    @PostMapping
    public ResponseEntity<Void> createTeamInvite(@RequestHeader(value = "member_id") String memberId,
                                                 @Valid @RequestBody CreateTeamInviteDTO dto){
        apiTeamInviteService.createTeamInvite(Long.valueOf(memberId), dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{inviteId}")
    public ResponseEntity<Void> readTeamInvite(@NotNull @Min(value=1, message="초대 ID는 1 이상이어야 됩니다.")
                                               @PathVariable(name = "inviteId") Long inviteId,
                                               @RequestHeader(value = "member_id") String memberId){
        apiTeamInviteService.readTeamInvite(inviteId, Long.valueOf(memberId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping
    public ResponseEntity<Void> readAllTeamInvite(@RequestHeader(value = "member_id") String memberId){
        apiTeamInviteService.readAllTeamInvite(Long.valueOf(memberId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTeamInvite(@RequestHeader(value = "member_id") String memberId,
                                                 @Valid @RequestBody JoinTeamDTO joinTeamDTO){
        apiTeamInviteService.deleteTeamInvite(Long.valueOf(memberId), joinTeamDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
