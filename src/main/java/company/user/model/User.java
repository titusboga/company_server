package company.user.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String username;

    @Column(length = 512, nullable = false)
    private String email;

    @Column(length = 256, nullable = false)
    private String password;

}
