package xyz.project.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.project.common.exception.ResourceNotFoundException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate birthDate;
    private String name;
    private String street;
    private String city;
    private String state;
    private String postal;
    private int statusId;
    private int roleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Credential credential;

    public void withCredential(Credential credential) {
        credential.setMember(this);
        this.credential = credential;
    }

    @Getter
    public enum Role {
        CLIENT(1), ADMIN(2), MERCHANT(3);

        private final int id;

        Role(int id) {
            this.id = id;
        }

        public static Role getByVal(int roleId) {
            for(Role  role : Role.values()) {
                if(role.id == roleId) {
                    return role;
                }
            }

            throw new ResourceNotFoundException("Not A valid role");
        }

    }

    @Getter
    public enum Status {
        DEACTIVATED(1), BLACKLIST(1), SUSPENDED(3), ACTIVE(4);

        private final int id;

        Status(int id) {
            this.id = id;
        }

        public static Status getByVal(int statusId) {
            for(Status status : Status.values()) {
                if(status.id == statusId) {
                    return status;
                }
            }

            throw new ResourceNotFoundException("Not A valid status");
        }
    }

}
