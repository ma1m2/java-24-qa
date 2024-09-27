package ru.msl.mantis.model.devmail;

import java.util.List;

public record GetIdsResponse(Boolean success, Object errors, List<String> result) {
}
