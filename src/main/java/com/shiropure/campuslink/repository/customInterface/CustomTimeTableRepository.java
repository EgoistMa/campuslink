package com.shiropure.campuslink.repository.customInterface;

import com.shiropure.campuslink.entity.TimeTable;

import java.util.UUID;

public interface CustomTimeTableRepository {
    public TimeTable findByOwnerUUID(UUID uuid);
}
