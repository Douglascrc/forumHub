package com.forumhub.domain.topic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public record TopicRegistrationData(
        @NotBlank
        String title,

        @NotBlank
        String message,

        @NotNull
        Long idAutor,

        @NotNull
        Long idCurso
) {
}
