package com.bitter.frontend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Beet {

    @NotEmpty @Positive
    private long id;
    @NotEmpty @Max(140)
    private String message;
    @NotEmpty
    private LocalDateTime createdAt;
    @NotEmpty @Max(30)
    private String createdByUsername;

}