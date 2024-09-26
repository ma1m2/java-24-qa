package ru.msl.mantis.model.devmail;

import ru.msl.mantis.model.DevMailUser;

public record AddUserResponse(Boolean success, Object errors, DevMailUser result) {

}
