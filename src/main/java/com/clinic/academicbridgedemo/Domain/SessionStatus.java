package com.clinic.academicbridgedemo.Domain;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum SessionStatus {
    @Enumerated(EnumType.STRING)
    REQUESTED,ACCEPTED,HELD,DECLINED
}
