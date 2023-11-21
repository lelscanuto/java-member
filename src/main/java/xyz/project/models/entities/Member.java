package xyz.project.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Enumerated(EnumType.ORDINAL)
    private Status statusId;
    @Enumerated(EnumType.ORDINAL)
    private Role roleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Credential credential;

    public void withCredential(Credential credential) {
        credential.setMember(this);
        this.credential = credential;
    }

    public enum Role {
        CLIENT(1), ADMIN(2), MERCHANT(3);

        private final int ordinal;

        Role(int ordinal) {
            this.ordinal = ordinal;
        }

        public int getOrdinal() {
            return ordinal;
        }
    }

    public enum Status {
        DEACTIVATED(1), BLACKLIST(1), SUSPENDED(3), ACTIVE(4);

        private final int ordinal;

        Status(int ordinal) {
            this.ordinal = ordinal;
        }

        public int getOrdinal() {
            return ordinal;
        }
    }

}
